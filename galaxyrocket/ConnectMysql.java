package galaxyrocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectMysql 
{

	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String username = "funnyseaman";
	private String password = "fan19960408";
	private Connection con = null;
	
	public ConnectMysql()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("驱动加载成功！");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败！");
			e.printStackTrace();
		}
	}
	
	public Connection getCon()
	{
		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("数据库连接成功！");
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			con = null;
			e.printStackTrace();
		}
		return con;
	}
	
	public void inputUpdate(String sql)
	{
		con = getCon();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String inputSelect(String sql)
	{
		String str = "";
		con = getCon();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
						str += (rs.getString(i)+"  ");
				}
				str += "\n";
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public String inputSelectCount(String sql)
	{
		String str = null;
		con = getCon();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			str = rs.getString(1);
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	
}
