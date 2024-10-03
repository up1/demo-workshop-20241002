package com.example.day1.demo;

import org.springframework.stereotype.Service;

@Service
public interface DemoService {
    void doSth();
}

@Service
class DemoServiceV1 implements DemoService {
    public void doSth(){
        System.out.println("V1");
    }
}

@Service
class DemoServiceV2 implements DemoService {
    public void doSth(){
        System.out.println("V2");
    }
}
