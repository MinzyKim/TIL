package lab.spring.aop.persist;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class UserRowMapper implements RowMapper<UserVO>{
	
	public UserVO mapRow(ResultSet rs, int num) throws SQLException {
		UserVO user=new UserVO();
		user.setUserid(rs.getString("userid"));
		user.setUserpwd(rs.getString("userpwd"));
		user.setUsername(rs.getString("username"));
		user.setPhone(rs.getString("phone"));
		user.setEmail(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		user.setJob(rs.getString("job"));
		return user;
	}

}
