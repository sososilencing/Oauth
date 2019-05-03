package com.roxi.oauth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.roxi.oauth.bean.User;
import com.roxi.oauth.utils.SetDate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roxi酱
 */
@Service
public class OauthTokenService {
    String sercet="RoxiNeverChanged";
    public String creatToken(User user,String auth){
        Algorithm algorithm=Algorithm.HMAC384(sercet);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("alg","HS384");
        map.put("typ","JWT");
        Date date=new Date();

        String token= JWT.create()
                .withHeader(map)
                .withIssuer("Roxi")
                .withSubject("Get message")

                .withClaim("name",user.getName())
                .withClaim("id",user.getId())
                .withClaim("auth",auth)
                .withClaim("timestamp",System.currentTimeMillis())

                .withAudience("user",user.getPower())
                .withIssuedAt(date)
                .withExpiresAt(SetDate.setDates(0,0,0,1,0))
                .sign(algorithm);
         return token;
    }

    public User verifyToken(String token){
        User user=new User();
        try {
            Algorithm algorithm=Algorithm.HMAC384(sercet);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer("Roxi").build();
            DecodedJWT jwt=verifier.verify(token);
            String sub=jwt.getSubject();

            Claim name=jwt.getClaim("name");
            Claim auth=jwt.getClaim("auth");
            Claim id=jwt.getClaim("id");
            user.setName(name.asString());
            user.setId(id.asInt());

            List<String> audience=jwt.getAudience();
            user.setPower(audience.get(1));

        }catch (JWTVerificationException exception){
            exception.printStackTrace();
        }

        return user;
    }
}
