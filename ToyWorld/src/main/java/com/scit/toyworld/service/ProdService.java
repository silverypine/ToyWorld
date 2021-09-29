package com.scit.toyworld.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scit.toyworld.dao.ProdDAO;
import com.scit.toyworld.vo.ProdVO;

@Service
public class ProdService {
	
	@Autowired
	private ProdDAO dao;
	
	@Autowired
	private HttpSession ss;
	
	public String insertProd(ProdVO prod) {
		String loginId = (String)ss.getAttribute("loginId");
		prod.setEmpId(loginId);
		String path = "";
		int cnt = dao.insertProd(prod);
		if (cnt > 0) {
			path = "redirect:/prod/listForm";
		} else {
			path = "redirect:/prod/insertForm";
		}
		return path;
	}
}
