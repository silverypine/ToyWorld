package com.scit.toyworld.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scit.toyworld.vo.InfoVO;
import com.scit.toyworld.vo.PositionVO;
import com.scit.toyworld.vo.ProdVO;

@Repository
public class ProdDAO {

	@Autowired
	private SqlSession ss;
	
	public int insertProd(ProdVO prod) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.insertProd(prod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public ArrayList<ProdVO> allList() {
		ArrayList<ProdVO> list = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			list = mapper.allList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ProdVO oneProd(String prodNum) {
		ProdVO prod = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			prod = mapper.oneProd(prodNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}
	
	public ArrayList<PositionVO> allPositionNum() {
		ArrayList<PositionVO> pos = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			pos = mapper.allPositionNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pos;
	}
	
	public int RegInfo(List<InfoVO> RegInfoList) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.RegInfo(RegInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
