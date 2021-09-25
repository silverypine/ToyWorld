package com.scit.toyworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scit.toyworld.dao.EmpDAO;
import com.scit.toyworld.vo.EmpVO;

@Service
public class EmpService {

	@Autowired
	private EmpDAO dao;
	
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
}
