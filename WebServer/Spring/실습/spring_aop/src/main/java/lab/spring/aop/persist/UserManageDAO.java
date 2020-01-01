package lab.spring.aop.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;




@Repository("dao")
public class UserManageDAO {
	private JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template=template;
	}
	
	public List<UserVO> loginProc(String uid, String upwd) {
		List<UserVO> lists=null;
		Object args[] = new String[] { uid, upwd };
		String sql="select * from userinfo where userid=? and userpwd=?";
		lists = template.query(sql, args, new UserRowMapper());
		
		return lists;
		
	}
	
	public int joinProc(final UserVO user) {
		int rows=0;
		final StringBuffer sql = new StringBuffer();
		sql.append("insert into userinfo(userid, userpwd, username, phone, email, address, job)");
		sql.append("values(?,?,?,?,?,?,?)");
		rows = template.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement pstat=con.prepareStatement(sql.toString());
			pstat.setString(1, user.getUserid());
			pstat.setString(2, user.getUserpwd());
			pstat.setString(3, user.getUsername());
			pstat.setString(4, user.getPhone());
			pstat.setString(5, user.getEmail());
			pstat.setString(6, user.getAddress());
			pstat.setString(7, user.getJob());
			
			return pstat;
			}
		});
		return rows;
	}	
	
	public List<UserVO> findUserList(){
		List<UserVO> userList=null;
		String sql = "select * from userinfo ";
		userList = template.query(sql, new UserRowMapper());
		return userList.isEmpty()? null: userList;
	}
	
	public UserVO findUser(String userid) {
		Object vo = null;
		String sql = "select * from userinfo where userid = ? ";
		vo = template.queryForObject(sql, new Object[] {userid}, new UserRowMapper());
				return (UserVO)vo;
	}
	
	public int updateUser(final UserVO user) {
		int rows=0;
		final StringBuffer sql = new StringBuffer();
		sql.append("update userinfo set email=?, phone=?, address=?, job=? ");
		sql.append("where userid=? ");
		rows = template.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement pstat=con.prepareStatement(sql.toString());
			pstat.setString(1, user.getEmail());
			pstat.setString(2, user.getPhone());
			pstat.setString(3, user.getAddress());	
			pstat.setString(4, user.getJob());
			pstat.setString(5, user.getUserid());
			
			return pstat;
			}
		});
		return rows;
	}	
	
	public int removeUser(final String userid) {
		int rows=0;
		final String sql = "delete userinfo where userid= ? ";
		rows = template.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			
			PreparedStatement pstat=con.prepareStatement(sql);
			pstat.setString(1, userid);

			return pstat;
			}
		});
		return rows;
}
}
