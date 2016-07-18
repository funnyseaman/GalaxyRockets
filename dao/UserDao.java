package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.User;


public class UserDao {
	
    /**
     * 个人信息修改
     * @param user
     * @return 成功返回1
     */
	public int modifyInfo(User user){
		Connection conn=DBHelper.getConn();
		String sql="update user set user_name=?,user_email=?,user_sex=?, user_school=? ,user_sign=? ,user_major=? where user_id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setInt(3, user.getUserSex());
			ps.setString(4, user.getUserSchool());
			ps.setString(5, user.getUserSign());
			ps.setString(6, user.getUserMajor());
			ps.setLong(7, user.getUserId());
			ps.executeUpdate();
			DBHelper.closeConn(conn, ps, null);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
		
	}

}
