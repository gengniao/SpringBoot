package com.gengniao.springboot.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
	
	private final static SimpleDateFormat MAT= new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedDelayString = "${jobs.fixedDelay}")
	public void getTask1() {
		System.out.println("����1,��ǰʱ��:"+MAT.format(new Date()));
	}
	
	@Scheduled(cron = "${jobs.cron}")
	public void getTask2() {
		System.out.println("����2,��ǰʱ��:"+MAT.format(new Date()));
	}
}
