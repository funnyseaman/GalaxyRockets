package com.WRan.dao;


import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

import com.WRan.model.User;
import com.WRan.util.ReflectUtil;

/**
 * Created by lazier on 2015/10/17 0017.
 */
public class Dao {
    public static String dbName = "linkedin";

    public static <T>  List<T> select(String where,Class<T> t){
        List<T> res = new ArrayList<>();
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            String query = "select * from `" + getTableName(t) + "` " + where;
//            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                T rec = t.newInstance();
                String[] cols = getColumns(rs);
                for (String col : cols) {
                    Field f = ReflectUtil.getFieldByName(rec, col);
                    if(rs.getObject(col)==null){
                        String typeName = rec.getClass().getDeclaredField(col).getType().getName();
                       // System.out.println(typeName);
                        if(typeName=="int")
                            f.set(rec, 0);
                        else if(typeName=="java.lang.String")
                            f.set(rec, "");
                    }else
                    f.set(rec, rs.getObject(col));
                }
                res.add(rec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static <T>  T selectById(int id,Class<T> t){
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            Set<String> pks = getPrimaryKeys(t);
            System.out.println(pks.toArray(new String[0])[0]);
            String query = "select * from `" + getTableName(t) + "` " + "where "+pks.toArray(new String[0])[0]+"="+id;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                T rec = t.newInstance();
                String[] cols = getColumns(rs);
                for (String col : cols) {
                    Field f = ReflectUtil.getFieldByName(rec, col);
                    if(rs.getObject(col)==null){
                        String typeName = rec.getClass().getDeclaredField(col).getType().getName();
                        System.out.println(typeName);
                        if(typeName=="int")
                            f.set(rec, 0);
                        else if(typeName=="java.lang.String")
                            f.set(rec, "");
                    }else
                        f.set(rec, rs.getObject(col));
                }
                return rec;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static<T>  int count(Class<T> t,String where){
        int x = -1;
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            String query = "select count(*) as cnt from `" + getTableName(t) + "` " + where;
//            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                x = rs.getInt("cnt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
    public static <T> boolean delete(Class<T> t,String where){
        String sql = String.format("delete from `%s` ", getTableName(t)) + where;
        boolean result=true;
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
        catch(Exception e){
            result=false;
            e.printStackTrace();
        }
        finally {
            return result;
        }

    }

    /**
     * ����ʱ�Ȳ�ѯ����. ���⽫���������null
     * @param
     * @param <T>
     * @return
     */

    public static <T> int insert(T entity) {
       // Set<String> pks = getPrimaryKeys(entity);
        StringBuilder insert = new StringBuilder(), values = new StringBuilder(), keys = new StringBuilder();
        insert.append(String.format("insert into `%s` ", getTableName(entity)));
        values.append(" values(");
        keys.append("( ");
        try {
            for (Field f : ReflectUtil.getNotNullFields(entity)) {
//                System.out.println(f.get(entity));
                //if (pks.contains(f.getName())) {
//                    keys.append(String.format("`%s`,", f.getName()));
//                    values.append(String.format("'%s',", "56"));
                  //  continue;
               // } else
                keys.append(String.format("`%s`,", f.getName()));
                if(f.get(entity).toString()=="false"||f.get(entity).toString()=="true"){
                    values.append(String.format("%s,", f.get(entity).toString()));
                }else
                values.append(String.format("'%s',", f.get(entity).toString()));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int t1 = keys.lastIndexOf(","), t2 = values.lastIndexOf(",");
        keys.replace(t1, t1 + 1, ") ");
        values.replace(t2, t2 + 1, ");");
        String sql = insert.append(keys).append(values).toString();
        System.out.println(sql);
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            //stmt.execute(sql);
            ResultSet rs=stmt.getGeneratedKeys();//���ز��������
            int num=0;
            if(rs.next()) {
                num = rs.getInt(1);
            }

            return num;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static <T> void update(T entity) {
        Set<String> pks = getPrimaryKeys(entity);
        StringBuilder update = new StringBuilder(), set = new StringBuilder(), where = new StringBuilder();
        update.append(String.format("update `%s` ", getTableName(entity)));
        set.append("set ");
        where.append("where ");
        for (Field f : ReflectUtil.getClassFields(entity)) {
            try {
                if (pks.contains(f.getName())) {
                    where.append(String.format("`%s`='%s' and ", f.getName(), f.get(entity).toString()));
                } else if (f.get(entity) != null) {
                    if(f.get(entity).toString()=="false"||f.get(entity).toString()=="true"){
                        set.append(String.format("`%s`=%s, ", f.getName(), f.get(entity).toString()));
                    }else
                    set.append(String.format("`%s`='%s', ", f.getName(), f.get(entity)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int t1 = where.lastIndexOf("and"), t2 = set.lastIndexOf(",");
        where.replace(t1, t1 + 3, ";");
        set.replace(t2, t2 + 1, "");
        String sql = update.append(set).append(where).toString();
        System.out.println(sql);
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> void update(T entity, String where) {
        Set<String> pks = getPrimaryKeys(entity);
        StringBuilder sb = new StringBuilder(String.format("update `%s` set ", getTableName(entity)));
        for (Field f : ReflectUtil.getClassFields(entity)) {
            try {
                if (f.get(entity) != null) {
                    sb.append(String.format("`%s`='%s', ", f.getName(), f.get(entity)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int t = sb.lastIndexOf(",");
        sb.replace(t, t + 1, " ");
        sb.append(where);
        System.out.println(sb);
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



public static <T> ArrayList<Integer> getbigestNumbers(Class<T> t,String where,String columnname){
   ArrayList<Integer> numbers=new ArrayList<>();
    int i=0;
    try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {

        String sql="select "+columnname+" from "+getTableName(t)+" " + where;
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
//            T rec = t.newInstance();
//            Set<String> pks = getPrimaryKeys(t);
//            String idName = pks.toArray(new String[0])[0];
//            int num = (int)rec.getClass().getDeclaredField(idName).get(rec);
//            String[] cols = getColumns(rs);
//            for (String col : cols) {
//                Field f = ReflectUtil.getFieldByName(rec, col);
//                f.set(rec, rs.getObject(col));
//            }
            if(i<=5){
                numbers.add(rs.getInt(1));
            }
            i++;
        }
    }
    catch(Exception e){

        e.printStackTrace();
    }
    finally {

        return numbers;
    }
}




    public static <T> Set<String> getPrimaryKeys(T t) {
        String tableName = getTableName(t);
        Set<String> res = new HashSet<>();
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet _pk = conn.getMetaData().getPrimaryKeys(dbName, null, tableName);
            while (_pk.next()) {
                // ����λ(��1��ʼ) ����������
                res.add(_pk.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static <T> String getTableName(T t) {
    	//System.out.println(t);
        String tableName = ReflectUtil.getClassName(t);
       // System.out.println(tableName+"-------");
        return tableName.replace(".class","").substring(tableName.lastIndexOf('.') + 1, tableName.length());
    }

    static <T> String getTableName(Class<T> t) {
        String tableName = t.getName();
        System.out.println(tableName.replace(".class","").substring(tableName.lastIndexOf('.') + 1, tableName.length()));
        return tableName.replace(".class","").substring(tableName.lastIndexOf('.') + 1, tableName.length());
    }

    public static String[] getColumns(ResultSet rs) throws SQLException {
        ResultSetMetaData sm = rs.getMetaData();
        String[] cols = new String[sm.getColumnCount()];
        for (int i = 1; i <= sm.getColumnCount(); ++i) {
            cols[i - 1] = sm.getColumnName(i);
        }
        return cols;
    }
    

}
