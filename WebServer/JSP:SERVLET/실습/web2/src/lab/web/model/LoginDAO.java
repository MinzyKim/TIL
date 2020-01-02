package lab.web.model;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class LoginDAO {
	public Connection dbCon() {
		Connection con=null;
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("C:/workspace2/web2/WebContent/WEB-INF/db.properties"));
			Class.forName(prop.getProperty("driver"));
			con = DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("user"), prop.getProperty("pwd"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public void dbClose(Connection con, Statement stat, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stat != null) stat.close();
			if(con != null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean loginProc(String uid, String upwd) {
		boolean success = false;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "select * from userinfo where userid=? and userpwd=?";
		ResultSet rs=null;
		try {
			con=dbCon();
			stat = con.prepareStatement(sql);
			stat.setString(1, uid);
			stat.setString(2, upwd);
			rs= stat.executeQuery();
			if(rs.next()) {
				success=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(con, stat, rs);
		}
		return success;
	}
	
	public int joinProc(UserVO user) { //멤버변수랑 컬럼명 동일해야 함
		int rows=0;
		Connection con = null;
		PreparedStatement stat = null;
		String sql ="insert into userinfo (userid, userpwd, username, phone, email, address, job) values(?,?,?,?,?,?,?)";
		ResultSet rs = null;
		
		try {
			con=dbCon();
			stat=con.prepareStatement(sql);
			stat.setString(1, user.getUserid() );
			stat.setString(2,user.getUserpwd());
			stat.setString(3,user.getUsername());
			stat.setString(4,user.getPhone());
			stat.setString(5,user.getEmail());
			stat.setString(6,user.getAddress());
			stat.setString(7,user.getJob());
			rows=stat.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(con, stat, rs);
		}
		return rows;
	}
}
