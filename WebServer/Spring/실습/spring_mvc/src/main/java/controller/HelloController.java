package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(Model model, @RequestParam(value="name", required=false)String name) {
		model.addAttribute("greeting", "안녕하세요"+name);//요청 처리 결과를 뷰에 전달
		return "hello";//뷰이름
	}

}
