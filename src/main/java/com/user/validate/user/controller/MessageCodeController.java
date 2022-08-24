package com.user.validate.user.controller;

import com.user.validate.user.exception.InvalidMessageCodeException;
import com.user.validate.user.model.MessageCode;
import com.user.validate.user.service.MessageCodeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageCodeController {

    @Autowired
    MessageCodeService messageCodeService;

    @CrossOrigin("http://localhost:4200/")
    @GetMapping("messageCodeDetails/{messageCode}")
    public ResponseEntity<MessageCode> getMessageCodeDetails(@PathVariable("messageCode") String messageCode) throws InvalidMessageCodeException {

        MessageCode messageCode1 = messageCodeService.getMessageCodeDetails(messageCode);
        return new ResponseEntity<>(messageCode1, HttpStatus.OK);

    }

}
