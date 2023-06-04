package com.salon.app.service;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class LoginService {
	
	public String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(10000);
        return String.format("%04d", code);
	}

}
