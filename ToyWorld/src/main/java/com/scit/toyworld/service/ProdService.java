package com.scit.toyworld.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scit.toyworld.util.FileService;
import com.scit.toyworld.dao.ProdDAO;
import com.scit.toyworld.vo.ProdVO;

@Service
public class ProdService {
	
	@Autowired
	private ProdDAO dao;
	
	@Autowired
	private HttpSession ss;
	
	@Autowired
	private HttpServletResponse response;
	
	private static final String uploadPath = "/prodUpload";
	
	public String insertProd(ProdVO prod, MultipartFile upload) {
		String loginId = (String)ss.getAttribute("loginId");
		prod.setEmpId(loginId);
		String path = "";
		
		if (!upload.isEmpty()) {
			String originalFileName = upload.getOriginalFilename();
			String savedFileName = FileService.saveFile(upload, uploadPath);
			prod.setProdOriginalFileName(originalFileName);
			prod.setProdSavedFileName(savedFileName);
		}
		
		int cnt = dao.insertProd(prod);
		if (cnt > 0) {
			path = "redirect:/prod/listForm";
		} else {
			path = "redirect:/prod/insertForm";
		}
		return path;
	}
	
	public ArrayList<ProdVO> allList() {
		return dao.allList();
	}
}
