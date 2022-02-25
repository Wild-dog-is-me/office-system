package com.gqy.server.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/02/24/8:11 PM
 * @Description: 要做耿沁园的男人
 */

@Component
public class JwtTokenUtil {

    //用户名的key
    private static final String CLAIM_KEY_USERNAME="sub";
    //jwt创建时间
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")//jwt 密钥
    private String secret;

    @Value("${jwt.expiration}")//失效时间
    private Long expiration;

    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    //用户信息通过Security中的 UserDetails 拿取
    public String generateToken(UserDetails userDetails){
        Map<String,Object> jwtToken = new HashMap<>();
        jwtToken.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        jwtToken.put(CLAIM_KEY_CREATED,new Date());
        //根据荷载生成jwt
        return generateToken(jwtToken);
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            //通过荷载 claims 就可以拿到用户名
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否可以被刷新
     * 如果过期了就可以被刷新，如果没过期就不能被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        //将创建时间改成当前时间，就相当于去刷新了
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        //判断token时间是否是当前时间的前面 .before
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        //从token里面获取荷载
        //因为token的过期时间有对应的数据,设置过的,荷载里面就有设置过的数据
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        //拿到荷载
        Claims claims = null ;
        try {
            claims = Jwts.parser()
                    //签名
                    .setSigningKey(secret)
                    //密钥
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 根据荷载生成 JWT TOKEN
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                //失效时间
                .setExpiration(generateExpirationDate())
                //签名
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成token失效时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}
