package lab.spring.orm.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import lab.spring.orm.model.UserVO;


public class UserManageDAO {
	

	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public UserVO login(String uid, String upwd) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		Object vo = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("uid", uid);
		hm.put("upwd", upwd);
		vo=sqlSession.selectOne("lab.mybatis.user.UserMapper.login",hm);
		return (UserVO)vo;
		
	}
	
	public int addUser( UserVO user) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		return sqlSession.insert("lab.mybatis.user.UserMapper.addUser",user);
	}
	
	public List<UserVO >findUserList() {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		return sqlSession.selectList("lab.mybatis.user.UserMapper.getUserList");
	}
	
	public int updateUser( UserVO user) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		return sqlSession.update("lab.mybatis.user.UserMapper.modifyUser",user);
	}
	
	public int removeUser( String uid) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		return sqlSession.delete("lab.mybatis.user.UserMapper.removeUser",uid);
	}
	
	public UserVO findUser(String uid) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		return sqlSession.selectOne("lab.mybatis.user.UserMapper.getUser", uid);
	}
	
	

}
