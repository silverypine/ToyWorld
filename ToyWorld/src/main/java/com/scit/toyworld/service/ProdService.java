package com.scit.toyworld.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scit.toyworld.util.FileService;
import com.scit.toyworld.dao.ProdDAO;
import com.scit.toyworld.vo.InfoVO;
import com.scit.toyworld.vo.PositionVO;
import com.scit.toyworld.vo.ProdVO;

@Service
public class ProdService {
	
	@Autowired
	private ProdDAO dao;
	
	@Autowired
	private HttpSession ss;
	
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
	
	public ArrayList<HashMap<String, Object>> allPosList(PositionVO positionNum) {
		return dao.allPosList(positionNum);
	}
	
	public ArrayList<ProdVO> allProdList() {
		return dao.allProdList();
	}
	
	public ProdVO oneProd(String prodNum) {
		return dao.oneProd(prodNum);
	}
	
	public ArrayList<PositionVO> allPositionNum() {
		return dao.allPositionNum();
	}
	
	public String RegInfo(int positionNum, List<String> SaveProdNumList, List<Integer> SaveStockList) {
		List<InfoVO> RegInfoList = new ArrayList<InfoVO>();
		
		for (int i = 0; i < SaveProdNumList.size(); i++) {
			InfoVO info = new InfoVO();
			info.setProdNum(SaveProdNumList.get(i));
			info.setPosNum(positionNum);
			info.setPosStock(SaveStockList.get(i));
			RegInfoList.add(info);
		}
		
		String path = "";
		int cnt = dao.RegInfo(RegInfoList);
		dao.StoreStockUpdate(RegInfoList);
		if (cnt > 0) {
			path = "redirect:/";
		} else {
			path = "redirect:/prod/goToMap";
		}
		return path;
	}
	
	public String warehouseReg(int positionNum, List<String> SaveProdNumList, List<Integer> SaveStockList) {
		List<InfoVO> RegInfoList = new ArrayList<InfoVO>();
		
		for (int i = 0; i < SaveProdNumList.size(); i++) {
			InfoVO info = new InfoVO();
			info.setProdNum(SaveProdNumList.get(i));
			info.setPosNum(positionNum);
			info.setPosStock(SaveStockList.get(i));
			RegInfoList.add(info);
		}
		
		String path = "";
		int cnt = dao.RegInfo(RegInfoList);
		dao.warehouseStockUpdate(RegInfoList);
		if (cnt > 0) {
			path = "redirect:/prod/wareHouse";
		} else {
			path = "redirect:/prod/goToWarehouseMap";
		}
		return path;
	}
	
	public ArrayList<PositionVO> search(String searchText) {
		return dao.search(searchText);
	}
	
	public boolean checkStock(List<String> prodNumList, List<Integer> inputStockList) {
		
		List<ProdVO> list = new ArrayList<ProdVO>();
		for (int i = 0; i < prodNumList.size(); i++) {
			ProdVO prod = new ProdVO();
			prod.setProdNum(prodNumList.get(i));
			prod.setProdStoreStock(inputStockList.get(i));
			list.add(prod);
		}
		
		boolean check = false;
		ArrayList<ProdVO> checkList = dao.checkStock(list);
		for (int i = 0; i < checkList.size(); i++) {
			if (checkList.get(i).getProdWarehouseStock() + checkList.get(i).getProdStoreStock()
					+ list.get(i).getProdStoreStock() > checkList.get(i).getProdStock()) {
				check = false;
				break;
			} else {
				check = true;
			}
		}
		return check;
	}
	
	public boolean checkStoreStock(List<String> prodNumList, List<Integer> inputStockList) {
		
		List<InfoVO> list = new ArrayList<InfoVO>();
		for (int i = 0; i < prodNumList.size(); i++) {
			InfoVO info = new InfoVO();
			info.setProdNum(prodNumList.get(i));
			info.setPosStock(inputStockList.get(i));
			list.add(info);
		}
		
		boolean check = false;
		ArrayList<InfoVO> checkList = dao.checkStoreStock(list);
		System.out.println(checkList);
		for (int i = 0; i < checkList.size(); i++) {
			if (checkList.get(i).getPosStock() < list.get(i).getPosStock()) {
				check = false;
				break;
			} else {
				check = true;
			}
		}
		return check;
	}
	
}
