package com.scit.toyworld.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
