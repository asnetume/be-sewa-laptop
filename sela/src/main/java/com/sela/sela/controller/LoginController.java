package com.sela.sela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sela.sela.dto.Response;
import com.sela.sela.model.User;
import com.sela.sela.service.Userservice;

@RestController
public class LoginController {
    @Autowired Userservice usr;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody User body){
        try {
            Response resp = Response.builder()
            .code("200")
            .data(usr.login(body))
            .message("login")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(null)
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }
    }
}
