package lab.spring.di.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.spring.di.util.JdbcUtil;


@Repository("dao")
public class UserManageDAO {
	private JdbcUtil dbUtil;
	
	@Autowired
	public void setDbUtil(JdbcUtil dbUtil) {
		this.dbUtil=dbUtil;
	}
	
	public UserVO loginProc(String uid, String upwd) {
		UserVO user=null;
		Connection con=null;
		PreparedStatement stat=null;
		String sql="select * from userinfo where userid=? and userpwd=?";
		ResultSet rs=null;
		try {
			con=dbUtil.dbCon();
			stat=con.prepareStatement(sql);
			stat.setString(1, uid);
			stat.setString(2, upwd);
			rs=stat.executeQuery();
			while(rs.next()) {
				user=new UserVO();
				user.setUserid(rs.getString("userid"));
				user.setUserpwd(rs.getString("userpwd"));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setJob(rs.getString("job"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbUtil.dbClose(con, stat, rs);
		}
		return user;
	}
}
