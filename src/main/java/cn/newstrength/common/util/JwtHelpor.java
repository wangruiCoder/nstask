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

    private SignatureAlgorithm signatureAlgorithm;

    private JwtHelpor(){}

    private static class JwtHelporInstance {
        private static final JwtHelpor INSTANCE = new JwtHelpor();
    }

    public static JwtHelpor getInstance(){
        return JwtHelporInstance.INSTANCE;
    }

    /**
     * 生成token
     * @param payload 存入token的信息模块，信息按照json格式传入
     * @return token字符串
     */
    public String createToken(String payload, SignatureAlgorithm signatureAlgorithm){
        initSignatureAlgorithm(signatureAlgorithm);

        String token = Jwts.builder().setHeader(this.getHeader())
                .setPayload(payload)
                .signWith(this.signatureAlgorithm, this.getKey()).compact();

        return token;
    }

    public String createToken(Map<String, Object> claimMaps, SignatureAlgorithm signatureAlgorithm, Date issuedAt){
        initSignatureAlgorithm(signatureAlgorithm);

        String token = Jwts.builder().setHeader(this.getHeader())
                .setClaims(claimMaps)
                .setIssuedAt(issuedAt)
                .setExpiration(this.getExpiresAtTime())
                .signWith(this.signatureAlgorithm, this.getKey()).compact();

        return token;
    }

    public boolean verifyToken(String token, SignatureAlgorithm signatureAlgorithm){
        initSignatureAlgorithm(signatureAlgorithm);

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();
        System.out.println(body.get("typ"));
        return true;
    }

    private Key getKey(){

        return new SecretKeySpec(SECRET.getBytes(),
                this.signatureAlgorithm.getJcaName());
    }

    /**
     * 获取 token 头部
     * @return 返回head
     */
    private Map<String,Object> getHeader(){
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ","JWT");
        header.put("alg",this.signatureAlgorithm.getValue());

        return header;
    }

    /**
     * 获取到期时间
     * @return 返回计算完成的到期时间
     */
    private Date getExpiresAtTime(){
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresAtTime = nowTime.getTime();

        return expiresAtTime;
    }

    /**
     * 获取 signatureAlgorithm 数据签名算法，主要检测是否为 <i>SignatureAlgorithm.NONE<i/>，如果为 <i>SignatureAlgorithm.NONE<i/>，默认使用 <i>SignatureAlgorithm.HS256<i/>
     */
    private void initSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm){
        if(signatureAlgorithm == SignatureAlgorithm.NONE){
            this.signatureAlgorithm = SignatureAlgorithm.HS256;
        }else{
            this.signatureAlgorithm = signatureAlgorithm;
        }
    }

}
