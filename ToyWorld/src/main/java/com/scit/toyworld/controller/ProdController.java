package com.scit.toyworld.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.scit.toyworld.service.ProdService;
import com.scit.toyworld.vo.ProdVO;

@Controller
public class ProdController {
	
	@Autowired
	private ProdService sv;

	@RequestMapping(value = "/prod/listForm", method = RequestMethod.GET)
	public String listForm(Model model) {
		ArrayList<ProdVO> list = sv.allList();
		model.addAttribute("list", list);
		return "prod/listForm";
	}

	@RequestMapping(value = "/prod/insertForm", method = RequestMethod.GET)
	public String insertForm() {
		return "prod/insertForm";
	}
	
	@RequestMapping(value = "/prod/insert", method = RequestMethod.POST)
	public String insertProd(ProdVO prod, MultipartFile upload) {
		return sv.insertProd(prod, upload);
	}
	
}
