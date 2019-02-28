package cn.newstrength.common.util;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt 工具类
 * @author 王瑞
 */
public class JwtHelpor {

    private static final String SECRET = "dddd";
    private static final int calendarField = Calendar.MINUTE;
    private static final int calendarInterval = 30;
    private static Key KEYs = new SecretKeySpec(SECRET.getBytes(),
            SignatureAlgorithm.HS256.getJcaName());
    public static String createToken(String payload){

        Date issuedAtTime = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresAtTime = nowTime.getTime();


        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ","JWT");
        header.put("alg","HS256");

        String token = Jwts.builder().setHeader(header)
                .setClaims(header)
                .setExpiration(expiresAtTime)
                .signWith(SignatureAlgorithm.HS256,KEYs).compact();

        return token;
    }

    public static boolean verifyToken(String token){

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEYs).parseClaimsJws(token);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();
        System.out.println(body.get("typ"));
        return true;
    }
}
