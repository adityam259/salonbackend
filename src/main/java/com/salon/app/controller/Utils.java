package com.salon.app.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class Utils {

    private final RestTemplate restTemplate;
    private final String apiUrl= "http://site.bulksmsnagpur.net/sendsms?uname=zade12&pwd=zade12&senderid=HSHAND&to=9131895660&";
    
    //@GetMapping("apiTest")
    public String callAPi() {
    	String var1="test1";
    	String var2="test9";
    	String var3="098787";
    	String var4="test4";
    	String var5="test5";
    	String var6="test6";
    	String var7="test10";
    	return getApiResponse(apiUrl, var1, var2, var3, var4, var5, var6,var7);
    }
    

    public Utils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public String getApiResponse(String apiUrl,String var1,String var2,String var3,String var4,String var5,String var6,String var7) {
        HttpHeaders headers = new HttpHeaders();
        StringBuilder sb = new StringBuilder();
        sb.append(apiUrl);
        String msg = "msg=HI"+var2+
        		"Confirmed ! Booking ID"+ var1 +
        		".Trip is "+var7+
        		"seater bus varient"+var3+
        		"scheduled to start at"+var4+
        		"from pickup location"+var5+
        		"contact details of customer is"+var6+
        		"please contact and share driver details and getting passanger list from customer. Check your trip details on bus hub app. "
        		+ "- Team Bus Hub. HSHAND&route=T&peid=1701167533595874370&tempid=1707167568662378832";
        sb.append(msg);
        //System.err.println(sb.toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
        		sb.toString(),
            HttpMethod.GET,
            entity,
            String.class
        );
        
        return response.getBody();
    }
    
}
