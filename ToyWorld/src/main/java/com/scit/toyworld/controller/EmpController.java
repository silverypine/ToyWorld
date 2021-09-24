package com.scit.toyworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scit.toyworld.service.EmpService;

@Controller
public class EmpController {

	@Autowired
	private EmpService sv;
}
