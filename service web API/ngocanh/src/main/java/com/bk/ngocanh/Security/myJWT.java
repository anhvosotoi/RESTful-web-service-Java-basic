package com.bk.ngocanh.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class myJWT {
    public String yeahmtp() {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }
}
