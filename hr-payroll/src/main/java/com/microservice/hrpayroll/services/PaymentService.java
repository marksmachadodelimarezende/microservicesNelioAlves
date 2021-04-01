package com.microservice.hrpayroll.services;

import com.microservice.hrpayroll.entities.Payment;
import com.microservice.hrpayroll.services.vo.WorkerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String hostWorker;

    private RestTemplate restTemplate;

    @Autowired
    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Payment getPayment(Long workerId, int days) {
        Map<String, String> uriVariable = new HashMap<>();
        uriVariable.put("id", workerId.toString());

        String endPoint = hostWorker.concat("/workers/{id}");

        WorkerVo workerVo = restTemplate.getForObject(endPoint, WorkerVo.class, uriVariable);
        return new Payment(workerVo.getName(), workerVo.getDailyIncome(), days);
    }
}
