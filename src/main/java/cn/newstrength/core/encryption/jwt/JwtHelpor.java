package cn.newstrength.core.encryption.jwt;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt 工具类,支持生成token,验证token。
 * 当前类线程安全。
 * @author 王瑞
 */
public class JwtHelpor {

    // 签名,建议使用数字字母组合。
    private static final String SECRET = "dddd";

    // 数据加密算法
    private SignatureAlgorithm signatureAlgorithm;

    private JwtHelpor(){}

    private static class JwtHelporInstance {
        private static final JwtHelpor INSTANCE = new JwtHelpor();
    }

    /**
     * 获取JwtHelpor实例
     * @return JwtHelpor实例
     */
    public static JwtHelpor getInstance(){
        return JwtHelporInstance.INSTANCE;
    }

    /**
     * 通过设置 payload 生成token,具体的payload信息请参考payload标准
     * @param payload 存入token的信息模块，信息按照json格式传入<br/>
     * payload 标准字段有
     * <ul>
     *   <li>iss:Issuer，jwt签发者</li>
     *   <li>sub:Subject，jwt所面向的用户</li>
     *   <li>aud:Audience，接收jwt的一方</li>
     *   <li>exp:ExpirationTime，过期时间</li>
     *   <li>iat:Issued at，发行时间</li>
     *   <li>nbf:定义在什么时间之前，该jwt都是不可用的.</li>
     *   <li>jti:唯一身份标识，主要用来作为一次性token,从而回避重放攻击</li>
     * </ul>
     * 除以上标准字段外还可以自己设置其他字段
     * @return token字符串
     */
    public String createToken(String payload, SignatureAlgorithm signatureAlgorithm){
        initSignatureAlgorithm(signatureAlgorithm);

        String token = Jwts.builder().setHeader(this.getHeader())
                .setPayload(payload)
                .signWith(this.signatureAlgorithm, this.getKey()).compact();

        return token;
    }

    /**
     * 通过设置claimMaps生成token,claimMaps中切勿存放敏感信息和过量信息,防止生成的token超过4KB,超过cookie可以支持的最大长度。
     * @param claimMaps 需要放入token中的信息
     * @param signatureAlgorithm 采用的数据加密算法，此算法设置完毕后会应用到签名key和token的生成，
     *                           如果这个值为<i>SignatureAlgorithm.NONE<i/>，系统会自动设定其为<i>SignatureAlgorithm.HS256<i/><br/>
     * @param issuedAt token的起始时间，请使用系统当前时间
     * @param calendarFieldEnum 生成token时可支持的时间单位:分钟,小时,天
     * @param calendarInterval token有效时间
     * @return token字符串
     * @throws RuntimeException
     * @throws IllegalArgumentException calendarInterval 参数设置错误,参数可设置范围请参考 {@link cn.newstrength.core.encryption.jwt.CalendarFieldEnum}
     */
    public String createToken(Map<String,Object> claimMaps, SignatureAlgorithm signatureAlgorithm,
                              Date issuedAt, CalendarFieldEnum calendarFieldEnum, int calendarInterval) throws RuntimeException{

        // 判定设置的token时间是否在当前枚举中要求的时间段范围之内
        if(calendarInterval < calendarFieldEnum.getMinValue()
                || calendarInterval > calendarFieldEnum.getMaxValue()){
            throw new IllegalArgumentException("过期时间设置异常，请输入介于"+calendarFieldEnum.getMinValue()+"~"+calendarFieldEnum.getMaxValue()+"正整数");
        }

        initSignatureAlgorithm(signatureAlgorithm);

        String token = Jwts.builder().setHeader(this.getHeader())
                .setClaims(claimMaps)
                .setIssuedAt(issuedAt)
                .setExpiration(this.getExpiresAtTime(issuedAt,calendarFieldEnum,calendarInterval))
                .signWith(this.signatureAlgorithm, this.getKey()).compact();

        return token;
    }

    /**
     * 验证token是否已经过期
     * @param token token
     * @param signatureAlgorithm token加密算法
     * @return 过期结果,true 过期,false 未过期
     */
    public boolean verifyTokenExpired(String token, SignatureAlgorithm signatureAlgorithm){
        boolean expired = false;

        long currentTime = new Date().getTime();
        initSignatureAlgorithm(signatureAlgorithm);

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        long expTime =  (long) body.get("exp");

        if(currentTime > expTime){
            expired = true;
        }

        return expired;
    }

    /**
     * 获取token的主体
     * @param token token
     * @param signatureAlgorithm token加密算法
     * @return token主体
     */
    public Map<String,Object> getTokenBody(String token, SignatureAlgorithm signatureAlgorithm){
        initSignatureAlgorithm(signatureAlgorithm);

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
        Claims body = claimsJws.getBody();

        return body;
    }

    /**
     * 签名加密
     * @return 加密后的签名串
     */
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
     * 获取token的到期时间
     * @return 返回计算完成的到期时间
     */
    private Date getExpiresAtTime(Date startTime, CalendarFieldEnum calendarFieldEnum,int calendarInterval){
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(startTime);
        nowTime.add(calendarFieldEnum.getCalendarField(), calendarInterval);
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
