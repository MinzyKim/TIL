package lab.spring.orm.service;

import java.util.List;

import lab.spring.orm.model.UserVO;

public interface UserService {
	public int addUser(UserVO user);
	public int updateUser(UserVO user);
	public int removeUser(String uid);
	public UserVO findUser(String uid);
	public UserVO login(String uid, String upwd);
	public List<UserVO> findUserList();
}
