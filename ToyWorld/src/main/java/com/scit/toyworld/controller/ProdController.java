package com.scit.toyworld.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scit.toyworld.service.ProdService;
import com.scit.toyworld.vo.PositionVO;
import com.scit.toyworld.vo.ProdVO;

@Controller
public class ProdController {
	
	@Autowired
	private ProdService sv;
	
	private List<String> SaveProdNumList;
	private List<Integer> SaveStockList;
	private List<Integer> SavePosInfoNumList;

	@RequestMapping(value = "/prod/posListForm", method = RequestMethod.GET)
	public String listForm(Model model, PositionVO posNum) {
		ArrayList<HashMap<String, Object>> posList = sv.allPosList(posNum);
		model.addAttribute("posList", posList);
		return "prod/posListForm";
	}
	
	@RequestMapping(value = "/prod/wareHouse", method = RequestMethod.GET)
	public String warehouseForm(Model model) {
		ArrayList<PositionVO> posNumList = sv.allPositionNum();
		model.addAttribute("posList", posNumList);

		return "prod/warehouse";
	}
	
	@RequestMapping(value = "/prod/warehouseListForm", method = RequestMethod.GET)
	public String warehouseListForm(Model model, PositionVO posNum) {
		ArrayList<HashMap<String, Object>> posList = sv.allPosList(posNum);
		model.addAttribute("posList", posList);
		return "prod/warehouseListForm";
	}
	
	@RequestMapping(value = "/prod/listForm", method = RequestMethod.GET)
	public String listForm(Model model) {
		ArrayList<ProdVO> list = sv.allProdList();
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
	
	@ResponseBody
	@RequestMapping(value = "/prod/SaveProdNum", method = RequestMethod.GET)
	public boolean SaveProdNum(@RequestParam(value = "prodNumList[]", defaultValue = "") List<String> prodNumList
			,@RequestParam(value = "inputStockList[]", defaultValue = "") List<Integer> inputStockList) {
		boolean check = false;
		if (prodNumList.isEmpty() || inputStockList.isEmpty()) {
			return false;
		} else {
			check = sv.checkStock(prodNumList, inputStockList);
			if (check == true) {
				SaveProdNumList = prodNumList;
				SaveStockList = inputStockList;
				return true;
			} else {
				return false;
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/prod/SavePosInfo", method = RequestMethod.GET)
	public boolean SavePosInfo(@RequestParam(value = "prodNumList[]", defaultValue = "") List<String> prodNumList
			,@RequestParam(value = "inputStockList[]", defaultValue = "") List<Integer> inputStockList
			,@RequestParam(value = "posInfoNumList[]", defaultValue = "") List<Integer> posInfoNumList) {
		boolean check = false;
		if (prodNumList.isEmpty() || inputStockList.isEmpty() || posInfoNumList.isEmpty()) {
			return false;
		} else {
			check = sv.checkPosStock(posInfoNumList, inputStockList);
			if (check == true) {
				SaveProdNumList = prodNumList;
				SaveStockList = inputStockList;
				SavePosInfoNumList = posInfoNumList;
				return true;
			} else {
				return false;
			}
		}
	}
	
	@RequestMapping(value = "/prod/goToMap", method = RequestMethod.GET)
	public String goToMap(Model model) {
		ArrayList<PositionVO> posNumList = sv.allPositionNum();
		model.addAttribute("posList", posNumList);
		return "prod/insertMap";
	}
	
	@RequestMapping(value = "/prod/goToWarehouseMap", method = RequestMethod.GET)
	public String goToWarehouseMap(Model model) {
		ArrayList<PositionVO> posNumList = sv.allPositionNum();
		model.addAttribute("posList", posNumList);
		return "prod/insertWarehouseMap";
	}
	
	@RequestMapping(value = "/prod/StoreToWarehouseMap", method = RequestMethod.GET)
	public String StoretoWarehouseMap(Model model) {
		ArrayList<PositionVO> posNumList = sv.allPositionNum();
		model.addAttribute("posList", posNumList);
		return "prod/StoreToWarehouseMap";
	}
	
	@RequestMapping(value = "/prod/WarehouseToStoreMap", method = RequestMethod.GET)
	public String WarehouseToStoreMap(Model model) {
		ArrayList<PositionVO> posNumList = sv.allPositionNum();
		model.addAttribute("posList", posNumList);
		return "prod/WarehouseToStoreMap";
	}
	
	@RequestMapping(value = "/prod/RegInfo", method = RequestMethod.GET)
	public String RegInfo(int positionNum) {
		return sv.RegInfo(positionNum, SaveProdNumList, SaveStockList);
	}
	
	@RequestMapping(value = "/prod/warehouseReg", method = RequestMethod.GET)
	public String warehouseReg(int positionNum) {
		return sv.warehouseReg(positionNum, SaveProdNumList, SaveStockList);
	}
	
	@RequestMapping(value = "/prod/storeToWarehouseReg", method = RequestMethod.GET)
	public String storeToWarehouseReg(int positionNum) {
		return sv.storeToWarehouseReg(positionNum, SaveProdNumList, SaveStockList, SavePosInfoNumList);
	}
	
	@RequestMapping(value = "/prod/warehouseToStoreReg", method = RequestMethod.GET)
	public String warehouseToStoreReg(int positionNum) {
		return sv.warehouseToStoreReg(positionNum, SaveProdNumList, SaveStockList, SavePosInfoNumList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/prod/search", method = RequestMethod.GET)
	public ArrayList<PositionVO> search(
			@RequestParam(value = "searchText", defaultValue = "") String searchText) {
		System.out.println(searchText);
		ArrayList<PositionVO> searchNum = sv.search(searchText);
		return searchNum;
	}
	
}
