package com.scit.toyworld.dao;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public ArrayList<HashMap<String, Object>> allPosList(PositionVO positionNum) {
		ArrayList<HashMap<String, Object>> list = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			list = mapper.allPosList(positionNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<ProdVO> allProdList() {
		ArrayList<ProdVO> list = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			list = mapper.allProdList();
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
	
	public int StoreStockUpdate(List<InfoVO> StoreStockList) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.StoreStockUpdate(StoreStockList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int warehouseStockUpdate(List<InfoVO> warehouseStockList) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.warehouseStockUpdate(warehouseStockList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public ArrayList<PositionVO> search(String searchText) {
		ArrayList<PositionVO> searchNum = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			searchNum = mapper.search(searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchNum;
	}
	
	public ArrayList<ProdVO> checkStock(List<ProdVO> prodNumList) {
		ArrayList<ProdVO> list = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			list = mapper.checkStock(prodNumList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<InfoVO> checkPosStock(List<InfoVO> posInfoList) {
		ArrayList<InfoVO> list = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			list = mapper.checkPosStock(posInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<InfoVO> checkPosNumAndProdNum(int positionNum) {
		ArrayList<InfoVO> list = null;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			list = mapper.checkPosNumAndProdNum(positionNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int posStockUpdate(List<InfoVO> posInfoList) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.posStockUpdate(posInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int StoreAndWarehouseStockUpdate(List<InfoVO> posInfoList) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.StoreAndWarehouseStockUpdate(posInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int WarehouseToStoreStockUpdate(List<InfoVO> posInfoList) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.WarehouseToStoreStockUpdate(posInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int OnePosStockUpdate(InfoVO info) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.OnePosStockUpdate(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int OneStoreAndWarehouseStockUpdate(InfoVO info) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.OneStoreAndWarehouseStockUpdate(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int OnePosStockUpdate2(InfoVO info) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.OnePosStockUpdate2(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int OneWarehouseToStoreStockUpdate(InfoVO info) {
		int cnt = 0;
		try {
			ProdMapper mapper = ss.getMapper(ProdMapper.class);
			cnt = mapper.OneStoreAndWarehouseStockUpdate(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
