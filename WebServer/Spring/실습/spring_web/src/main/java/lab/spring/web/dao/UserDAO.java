package lab.spring.web.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.spring.web.model.UserVO;



@Repository
public class UserDAO {
	
	@Autowired
	SqlSession sqlSession;


	public UserVO login(String uid, String upwd) {

		Object vo = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("uid", uid);
		hm.put("upwd", upwd);
		vo=sqlSession.selectOne("lab.mybatis.user.UserMapper.login",hm);
		return (UserVO)vo;
		
	}
	public int addUser( UserVO user) {

		return sqlSession.insert("lab.mybatis.user.UserMapper.addUser",user);
	}
	
	public List<UserVO >findUserList() {

		return sqlSession.selectList("lab.mybatis.user.UserMapper.getUserList");
	}
	
	public int updateUser( UserVO user) {

		return sqlSession.update("lab.mybatis.user.UserMapper.modifyUser",user);
	}
	
	public int removeUser( String uid) {

		return sqlSession.delete("lab.mybatis.user.UserMapper.removeUser",uid);
	}
	
	public UserVO findUser(String uid) {

		return sqlSession.selectOne("lab.mybatis.user.UserMapper.getUser", uid);
	}
	
	public List<UserVO> findUser(String condition, String keyword){
		List<UserVO> userList = null;
		HashMap<String, String> hashmap =
				new HashMap<String, String>();
		hashmap.put(condition, "%"+keyword+"%");
		userList = sqlSession.selectList("lab.mybatis.user.UserMapper.findUser", hashmap);
		return userList;
	}
}