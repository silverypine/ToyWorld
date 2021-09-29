package com.scit.toyworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scit.toyworld.service.ProdService;
import com.scit.toyworld.vo.ProdVO;

@Controller
public class ProdController {
	
	@Autowired
	private ProdService sv;

	@RequestMapping(value = "/prod/listForm", method = RequestMethod.GET)
	public String listForm() {
		return "prod/listForm";
	}

	@RequestMapping(value = "/prod/insertForm", method = RequestMethod.GET)
	public String insertForm() {
		return "prod/insertForm";
	}
	
	@RequestMapping(value = "/prod/insert", method = RequestMethod.POST)
	public String insertProd(ProdVO prod) {
		return sv.insertProd(prod);
	}
}
