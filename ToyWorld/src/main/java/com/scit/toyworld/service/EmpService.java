package com.scit.toyworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scit.toyworld.dao.EmpDAO;

@Service
public class EmpService {

	@Autowired
	private EmpDAO dao;
}
