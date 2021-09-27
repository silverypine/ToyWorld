package com.scit.toyworld.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scit.toyworld.dao.EmpDAO;
import com.scit.toyworld.vo.EmpVO;

@Service
public class EmpService {

	@Autowired
	private EmpDAO dao;
	
	@Autowired
	private HttpSession ss;
	
	public String join(EmpVO emp) {
		String path = "";
		int cnt = dao.join(emp);
		if (cnt > 0) {
			path = "redirect:/emp/loginForm";
		} else {
			path = "redirect:/emp/joinForm";
		}
		return path;
	}
	
	public String login(EmpVO inputData) {
		String path = "";
		EmpVO searchData = dao.login(inputData.getEmpid());
		if (searchData == null) {
			path = "redirect:/emp/loginForm";
		} else if (searchData.getEmppw().equals(inputData.getEmppw())) {
			ss.setAttribute("loginID", searchData.getEmpid());
			path = "redirect:/";
		} else {
			path = "redirect:/emp/loginForm";
		}
		return path;
	}
}
