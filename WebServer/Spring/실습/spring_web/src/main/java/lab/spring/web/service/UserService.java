package lab.spring.web.service;

import java.util.List;

import lab.spring.web.model.UserVO;



public interface UserService {
	public int addUser(UserVO user);
	public int updateUser(UserVO user);
	public int removeUser(String uid);
	public UserVO findUser(String uid);
	public List<UserVO> findUser(String condition, String keyword);
	public UserVO login(String uid, String upwd);
	public List<UserVO> findUserList();
}
