package com.horizon.spring.context;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class HnContextClosedListener implements ApplicationListener<ContextClosedEvent>{

	@Override
	public void onApplicationEvent(ContextClosedEvent arg0) {
		// TODO Auto-generated method stub

	}



}
