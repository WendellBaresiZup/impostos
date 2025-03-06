package br.com.zup.impostos.infra;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {
    private String SECRET_KEY = "secreta";

    public String geradorToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    public interface ClaimsResolver<T>{
        T resolve(Claims claims);
    }

    public Claims extractAllClaims(String token){
        JwtParser parser = Jwts.parser().setSigningKey(SECRET_KEY).build();
        return parser.parseClaimsJws(token).getBody();
    }

    public String getUserNameFromToken(String token){
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
