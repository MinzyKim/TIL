package lab.spring.web.service;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import lab.spring.web.dao.UserDAO;
import lab.spring.web.model.UserVO;


@Service("userService") 
public class UserServiceImpl implements UserService {
	
	@Autowired		
	private UserDAO dao;

	public int addUser(UserVO user) {
		// TODO Auto-generated method stub
		return dao.addUser(user);
	}

	public int updateUser(UserVO user) {
		// TODO Auto-generated method stub
		return dao.updateUser(user);
	}

	public int removeUser(String uid) {
		// TODO Auto-generated method stub
		return dao.removeUser(uid);
	}

	public UserVO findUser(String uid) {
		// TODO Auto-generated method stub
		return dao.findUser(uid);
	}

	public UserVO login(String uid, String upwd) {
		// TODO Auto-generated method stub
		return dao.login(uid, upwd);
	}

	public List<UserVO> findUserList() {
		// TODO Auto-generated method stub
		return dao.findUserList();
	}

	public List<UserVO> findUser(String condition, String keyword) {
		// TODO Auto-generated method stub
		return dao.findUser(condition, keyword);
	}
		
		
		

}
