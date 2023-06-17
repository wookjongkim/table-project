package com.reservation.tableproject.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;


public class JwtUtil {
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long validTime = 3600000L;

    public static String createToken(String username, String role){
        Date now = new Date();
        Date validity = new Date(now.getTime() + validTime);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", role)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey)
                .compact();
    }

    public static String getUsername(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public static boolean validate(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static String getClaim(String token, String claimName) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(claimName, String.class);
    }
}
