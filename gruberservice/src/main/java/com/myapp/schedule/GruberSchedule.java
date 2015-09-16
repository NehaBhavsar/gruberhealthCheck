package com.myapp.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myapp.service.GruberHealthCheckService;

@Component
public class GruberSchedule implements Runnable {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void gruberHealthCheck() {
		System.out.println("The time is now " + dateFormat.format(new Date()));
		Thread thread = new Thread(this);
		thread.start();

	}

	public void run() {
		System.out.println("Calling Gruber Health check service");
		try {
			GruberHealthCheckService.healthCheckService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
