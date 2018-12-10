package com.projects.callcenter.controller;

import com.projects.callcenter.services.CallCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/callcenter")
public class CallCenterController {

    @Autowired
    private CallCenterService callCenterService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity call() {
        callCenterService.receiveCall();
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
