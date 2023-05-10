package com.anywr.test.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final String SECRET_KEY="5266556A586E3272357538782F4125442A472D4B6150645367566B59703373367639792442264528482B4D6251655468576D5A7134743777217A25432A462D4A";

    public String extractUsername(String jwt) {
        return extractClaim(jwt,Claims::getSubject);
    }
    private Claims extractAllClaims(String token){

        return Jwts.parserBuilder()
                .setSigningKey(getsignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getsignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String,String> extractClaims,
                                UserDetails  user){
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new  Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getsignKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public  boolean isTokenValid(String token , UserDetails userDetails){
        final String username=extractUsername(token);
        return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return  extractTokenExpiration(token).before(new Date());
    }

    private Date extractTokenExpiration(String token) {
       return extractClaim(token,Claims::getExpiration);
    }
}
