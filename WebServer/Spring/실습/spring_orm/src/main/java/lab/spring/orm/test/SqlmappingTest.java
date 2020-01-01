package lab.spring.orm.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



import lab.spring.orm.dao.UserManageDAO;
import lab.spring.orm.model.UserVO;

public class SqlmappingTest {
	   public static void main(String[] args) throws IOException{
	      String resource ="mybatis-config.xml";//MyBatis 설정파일
	      InputStream inputStream =Resources.getResourceAsStream(resource);
	      SqlSessionFactory sqlSessionFactory
	      =new SqlSessionFactoryBuilder().build(inputStream);
	      UserManageDAO userDao=new UserManageDAO();
	      userDao.setSqlSessionFactory(sqlSessionFactory);
	
		System.out.println("########전체 목록#########");
		List<UserVO> lists=userDao.findUserList();
		Iterator<UserVO> iter = lists.iterator();
		while(iter.hasNext()) {
			UserVO u = iter.next();
			System.out.println(u);
		}
		
		 UserVO vo = new UserVO();
		 vo.setUserid("s3");
		 vo.setUsername("서울3");
		 vo.setUserpwd("1234");
		 vo.setEmail("seoul13@korea.or.kr");
		 vo.setPhone("02-319");
		 vo.setAddress("서울");
		 vo.setJob("IT개발");
		 System.out.println("insert rows = >" + userDao.addUser(vo));
		 System.out.println("########s3 아이디 한행 검색#########");
		 System.out.println(userDao.findUser("s3"));
		 
		 vo.setUserid("s3");
		 vo.setEmail("s3@gmail.kr.com");
		 vo.setPhone("02-319-1234");
		 vo.setAddress("부산");
		 vo.setJob("데이터 분석");
		 System.out.println("update: s3 = >" + userDao.updateUser(vo));
		 System.out.println(userDao.findUser("s3"));
		 System.out.println("delete: s3 = >" + userDao.removeUser("s3"));
		 System.out.println("########전체 목록#########");
		 lists=userDao.findUserList();
		 iter=lists.iterator();
			while(iter.hasNext()) {
				UserVO u = iter.next();
				System.out.println(u);
			}
	}
	
}
