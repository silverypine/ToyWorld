package com.scit.toyworld.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(value = "/prod/readForm", method = RequestMethod.GET)
	public String readForm(Model model, String prodNum) {
		ProdVO prod = sv.oneProd(prodNum);
		model.addAttribute("prod", prod);
		return "prod/readForm";
	}
	
	@RequestMapping(value = "/prod/loadImage", method = RequestMethod.GET)
	public String loadImage(String fileName, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpg");
	    ServletOutputStream bout = response.getOutputStream();
		String imagePath = "C:/prodUpload/" + fileName;
		FileInputStream f = new FileInputStream(imagePath);
		int length;
		byte[] buffer = new byte[10];
		while((length=f.read(buffer)) != -1){
			bout.write(buffer,0,length);
		}
		return null;
	}
}
