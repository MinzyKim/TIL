package lab.spring.aop.service;

import java.util.List;

import lab.spring.aop.persist.UserVO;

public interface UserService {
	public int registerMember(UserVO user);
	public int updateUser(UserVO user);
	public int removeUser(String uid);
	public UserVO findUser(String uid);
	public List<UserVO> login(String uid, String upwd);
	public List<UserVO> findUserList();
}
