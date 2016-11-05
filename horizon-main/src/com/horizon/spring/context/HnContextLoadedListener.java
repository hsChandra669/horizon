package com.horizon.spring.context;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class HnContextLoadedListener implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		System.out.println("context loaded*********");
		System.setProperty("log4j.configurationFile", "E:/i-have-to-finish/logs/log4j2.xml");
	}

}
