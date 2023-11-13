package com.marvel.api.marvelchallenge.persistence.integration.marvel;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelAPIConfig {

    private PasswordEncoder md5Encoder;

    @Autowired
    @Qualifier("md5Encoder")
    public void setMd5Encoder(PasswordEncoder md5Encoder) {
        this.md5Encoder = md5Encoder;
    }
    private final long timestamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;

    @Value("${integration.marvel.private-key}")
    private String privateKey;

    private String getHash() {
        String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
        return md5Encoder.encode(hashDecoded);
    }

    public Map<String, String> getAuthentificationQueryParams() {
        Map<String, String> securityQueryParams = new HashMap<>();
        securityQueryParams.put("ts", Long.toString(this.timestamp));
        securityQueryParams.put("apikey", this.publicKey);
        securityQueryParams.put("hash", this.getHash());

        return securityQueryParams;

    }
}
