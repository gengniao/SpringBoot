package com.zhiyue.log.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
	
	@RequestMapping("hello")
	public String hello() {
		return "hello world!";
	}
	
	@Value("${gaox}")
	private String gaox;
	
	private Logger log = Logger.getLogger(LogController.class);
	
	@RequestMapping("log")
	public String log() {
		return gaox;
	}
}
