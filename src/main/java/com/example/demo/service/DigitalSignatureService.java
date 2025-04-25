package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.security.*;
import java.util.Base64;

@Service
public class DigitalSignatureService {
    private final KeyPair keyPair;

    public DigitalSignatureService() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        this.keyPair = keyGen.generateKeyPair();
    }

    public String signMessage(String message) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(keyPair.getPrivate());
        privateSignature.update(message.getBytes());
        byte[] signature = privateSignature.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public boolean verifySignature(String message, String signatureStr) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(keyPair.getPublic());
        publicSignature.update(message.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);
        return publicSignature.verify(signatureBytes);
    }
}
