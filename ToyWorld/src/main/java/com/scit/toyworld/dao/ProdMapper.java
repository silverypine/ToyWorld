package com.scit.toyworld.dao;

import java.util.ArrayList;

import com.scit.toyworld.vo.ProdVO;

public interface ProdMapper {
	
	public int insertProd(ProdVO prod);
	public ArrayList<ProdVO> allList();
}
