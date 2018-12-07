package com.projects.callcenter.worker;

import com.projects.callcenter.services.DispatcherService;
import com.projects.callcenter.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public static final String RECEIVE_METHOD_NAME = "doWork";

    @Autowired
    DispatcherService dispatcherService;

    public void doWork(String message) {
        logger.debug("[Receiver] ha recibido el mensaje: {}", message);
        if (!dispatcherService.assignCall())
            throw new RuntimeException();
    }
}
