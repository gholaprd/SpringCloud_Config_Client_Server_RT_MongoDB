package com.spring.cloud.config.client;

import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RefreshScopeListener {

	@EventListener
	public void refresh(RefreshScopeRefreshedEvent e) {
		System.out.println("****** Refresh scope listener called ******");
	}

}
