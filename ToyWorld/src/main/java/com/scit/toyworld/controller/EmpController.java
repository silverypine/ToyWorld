package com.scit.toyworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scit.toyworld.service.EmpService;
import com.scit.toyworld.vo.EmpVO;

@Controller
public class EmpController {

	@Autowired
	private EmpService sv;
	
	@RequestMapping(value = "/emp/joinForm", method = RequestMethod.GET)
	public String joinForm() {
		return "emp/joinForm";
	}
	
	@RequestMapping(value = "/emp/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "emp/loginForm";
	}
	
	@RequestMapping(value = "/emp/join", method = RequestMethod.POST)
	public String join(EmpVO emp) {
		return sv.join(emp);
	}
}
