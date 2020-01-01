package lab.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lab.spring.web.model.SearchVO;
import lab.spring.web.model.UserVO;
import lab.spring.web.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	
	@RequestMapping(value="/add.do",method=RequestMethod.GET)
	public String form() {
		return "joinForm";
	}
	
	@RequestMapping(value="/add.do", method=RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user")UserVO user
								, BindingResult rs) {
		ModelAndView mav = new ModelAndView();
	if(rs.hasErrors()) {
		mav.setViewName("joinForm");
	}else if(service.addUser(user) > 0) {
		
			mav.setViewName("redirect:list.do");
		}else {
			mav.setViewName("redirect:login.do");
		}
		return mav;
		
	}
	
	@ModelAttribute("searchConditionList")
	public ArrayList<SearchVO> makeSearchConditionList(){
		ArrayList<SearchVO> searchConditionList = new ArrayList<SearchVO>();
		searchConditionList.add(new SearchVO("userid", "아이디"));
		searchConditionList.add(new SearchVO("username", "이름"));
		searchConditionList.add(new SearchVO("email", "이메일"));
		return searchConditionList;
	}

	
//	@RequestMapping("/list.do")
//	public ModelAndView listUser() {
//		ModelAndView mav = new ModelAndView();
//		List<UserVO> userList = null;
//		userList = service.findUserList();
//		mav.addObject("users", userList);
//		mav.setViewName("listForm");
//		
//		return mav;
//	}
	
	@RequestMapping(value="/search.do", method=RequestMethod.GET)
	public ModelAndView search(@RequestParam("searchCondition")String condition,
							   @RequestParam("searchKeyword")String keyword) {
		ModelAndView mav = new ModelAndView();
		List<UserVO> userList = null;
		userList = service.findUser(condition, keyword);
		mav.addObject("users", userList);
		mav.setViewName("listForm");
		return mav;
	}
	
	@RequestMapping("/view.do")
	public ModelAndView modifyUser(@RequestParam("userid")String uid) {
		ModelAndView mav = new ModelAndView();
		UserVO vo = null;
		vo=service.findUser(uid);
		mav.addObject("user", vo);
		mav.setViewName("user_modify");
		return mav;
		
	}
	
	@RequestMapping("/update.do")
	public ModelAndView editUser(@ModelAttribute("user")UserVO user) {
		ModelAndView mav = new ModelAndView();
		int vo =0;
		vo=service.updateUser(user);
		if(vo > 0) {
			mav.setViewName("redirect:list.do");
		}else {
			mav.setViewName("redirect:login.do");
		}
		return mav;
	}
	@RequestMapping("/remove.do")
	public ModelAndView dropUser(@RequestParam("userid")String uid) {
		ModelAndView mav = new ModelAndView();
		if(service.removeUser(uid) > 0) {
			mav.setViewName("redirect:list.do");
		}else {
			mav.setViewName("redirect:login.do");
		}
		return mav;
	}
	  
	@RequestMapping("/listJson.do")
	   public ArrayList<UserVO> listUser() {

	      ArrayList<UserVO> userList = null;
	      userList = (ArrayList)service.findUserList();
	   
	      return userList;
	   }
	
}
