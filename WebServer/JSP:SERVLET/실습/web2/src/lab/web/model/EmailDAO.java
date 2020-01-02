package lab.web.model;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class EmailDAO {
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
	
	public ArrayList<EmailVO> getList(int page, int pageSize) {
	ArrayList<EmailVO> lists = new ArrayList();
		Connection con = null;
		PreparedStatement stat = null;
		int start = (page-1)*pageSize; //4페이지의 글목록
		int end = page * pageSize;
		String sql = "select * from emaillist where no > "
				+start+" and no<= "+end+" order by no desc";
		ResultSet rs=null;
		try {
			con=dbCon();
			stat = con.prepareStatement(sql);
			rs= stat.executeQuery();
			while(rs.next()) {
				EmailVO vo = new EmailVO(rs.getString("username")
											, rs.getString("email")
											, rs.getInt("no"));
				lists.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(con, stat, rs);
		}
		return lists;
	}
	
	public int getTotalCount() {
		int total=0;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "select count(*) from emaillist ";
		ResultSet rs=null;
		try {
			con=dbCon();
			stat = con.prepareStatement(sql);
			rs= stat.executeQuery();
			if(rs.next()) {
				total=rs.getInt(1);//첫번째 인덱스에 해당하는 값 저장
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(con, stat, null);
		}
		return total; //토탈리턴	
	}
}
