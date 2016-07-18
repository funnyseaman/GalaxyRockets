package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBHelper{
	/** 
     * 获得一个数据库连接 
     *  
     * @return 
     */  
    public static Connection getConn() {
    	Connection conn=null;
    	try {
			Class.forName("com.mysql.jdbc.Driver");		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		String url="jdbc:mysql://localhost:3306/linkedin?user=root&password=112208";
		//String user="root";
		//String password="112208";
		try {
			conn=DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("连接成功");
	    return conn;
    }
    /** 
     * 关闭数据库连接资源 
     *  
     * @param conn 
     * @param ps 
     * @param rs 
     */  
    public static void closeConn(Connection conn, Statement ps, ResultSet rs) {  
        try {  
            if (rs != null) {  
                rs.close();  
                rs = null;  
            }  
            if (ps != null) {  
                ps.close();  
                ps = null;  
            }  
            if (conn != null) {  
                conn.close();  
                System.out.println("数库关闭");
                conn = null;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}

