package com.kongzi.https.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HttpsController {
	
	@RequestMapping("/test")
	public List<String> test(){
		List<String> list = new ArrayList<String>();
		list.add("窗前明月光");
		list.add("窗前明月光");
		list.add("窗前明月光");
		list.add("窗前明月光");
		return list;
	}
}
