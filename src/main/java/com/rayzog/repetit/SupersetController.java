package com.rayzog.repetit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Base64;

@Controller
public class SupersetController {

    @GetMapping("/dashboard")
    public String getDashboardData(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        System.out.println(token);
        String supersetUrl = "http://localhost:8088/api/v1/dashboard/13";
        supersetUrl = "http://localhost:8088/api/v1/security/guest_token/";
        supersetUrl = "https://ya.ru/";


//        String username = "admin";
//        String password = "admin";
//
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpGet request = new HttpGet(supersetUrl);
//        request.addHeader("Authorization", "Bearer " + token);
//        request.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));


//            HttpResponse response = httpClient.execute(request);
//            System.out.println(response);
//            String responseBody = EntityUtils.toString(response.getEntity());
//            System.out.println(responseBody);
            System.out.println("adfasdfadfadfadfadfadf");
            return "dashboard";
    }
}