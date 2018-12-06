package com.projects.callcenter.worker;

import com.projects.callcenter.services.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;

public class Receiver {
    public static final String RECEIVE_METHOD_NAME = "doWork";

    @Autowired
    DispatcherService dispatcherService;
    public void doWork(String message) {
        System.out.println("[Receiver] ha recibido el mensaje \"" + message + '"');
        dispatcherService.assignCall();
    }
}
