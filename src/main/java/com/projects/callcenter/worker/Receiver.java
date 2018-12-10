package com.projects.callcenter.worker;

import com.projects.callcenter.domain.Employee;
import com.projects.callcenter.services.DispatcherService;
import com.projects.callcenter.services.EmployeeService;
import com.projects.callcenter.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private static final int MIN_DURATION_VALUE = 5;
    private static final int MAX_DURATION_VALUE = 10;

    public static final String RECEIVE_METHOD_NAME = "doWork";


    @Autowired
    DispatcherService dispatcherService;
    @Autowired
    EmployeeService employeeService;

    public void doWork(String message) {
        logger.debug("[Receiver] ha recibido el mensaje: {}", message);
        Optional<Employee> employee = dispatcherService.dispatchCall();
        if (!employee.isPresent())
            throw new RuntimeException();

        Runnable runnable = () -> {
            try {
                int duration = NumberUtils.getRandomBetween(MIN_DURATION_VALUE,MAX_DURATION_VALUE);
                TimeUnit.SECONDS.sleep(duration);
                employeeService.freeEmployee(employee.get().getId());
                logger.info("Finish employee {} call after {} seconds and then change status to free.",employee.get().getId(),duration);
            } catch (Exception e) {
                logger.error("It was an error while trying to finish call for employee {}. Error: {}", employee.get().getId(),e.getMessage());
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
