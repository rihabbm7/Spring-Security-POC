package com.anywr.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anywr.test.dao.dto.AuthenticationRequest;
import com.anywr.test.dao.dto.AuthenticationResponse;
import com.anywr.test.services.IAuthService;


@Controller
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private  IAuthService authenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
