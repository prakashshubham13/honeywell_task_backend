package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.DigitalSignatureService;

@RestController
@RequestMapping("/api/signature")
@CrossOrigin(origins = "*")
public class SignatureController {

    @Autowired
    private DigitalSignatureService signatureService;

    @PostMapping("/sign")
    public String sign(@RequestBody String message) throws Exception {
        return signatureService.signMessage(message);
    }

    @PostMapping("/verify")
    public boolean verify(@RequestBody VerifyRequest request) throws Exception {
        return signatureService.verifySignature(request.getMessage(), request.getSignature());
    }

    static class VerifyRequest {
        private String message;
        private String signature;

        // Getters and setters
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getSignature() { return signature; }
        public void setSignature(String signature) { this.signature = signature; }
    }
}
