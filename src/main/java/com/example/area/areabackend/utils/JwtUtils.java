package com.example.area.areabackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;

public class JwtUtils {

//    private final static Key key = Keys.hmacShaKeyFor("123456".getBytes(StandardCharsets.UTF_8));

    public static String encode(Map<String, Object> claims, Key key) {
        return Jwts.builder().addClaims(claims).signWith(key).compact();
    }

    public static String encode(Claims claims, Key key) {
        return Jwts.builder().setClaims(claims).signWith(key).compact();
    }

    public static Jws<Claims> decode(String token, Key key){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
