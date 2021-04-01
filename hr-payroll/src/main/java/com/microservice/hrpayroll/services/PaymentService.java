package com.microservice.hrpayroll.services;

import com.microservice.hrpayroll.entities.Payment;
import com.microservice.hrpayroll.feignClients.WorkerFeignClient;
import com.microservice.hrpayroll.services.vo.WorkerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(Long workerId, int days) {
        WorkerVo workerVo = workerFeignClient.findById(workerId).getBody();
        return new Payment(workerVo.getName(), workerVo.getDailyIncome(), days);
    }
}
