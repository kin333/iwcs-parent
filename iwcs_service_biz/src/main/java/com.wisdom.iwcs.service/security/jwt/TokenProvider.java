package com.wisdom.iwcs.service.security.jwt;

import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.GsonUtil;
import com.wisdom.iwcs.domain.system.SUser;
import com.wisdom.iwcs.service.security.TokenUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";
    private static final String TOKEN_USER_KEY = "token_user";
    private static final String TOKEN_USER_ID_KEY = "token_user_id";

    private static final String CURRENT_COMPANY_ID = "current_company_id";

    private static final String CURRENT_AREA_CODE = "current_area_code";

    private static final String EMPLOYEE_ID = "employee_id";

    private static final String SUPER_ADMIN = "super_admin";

    private String secretKey;

    private long tokenValidityInSeconds;

    private long tokenValidityInSecondsForRememberMe;

    @Autowired
    private ApplicationProperties properties;

    @PostConstruct
    public void init() {
        this.secretKey =
                properties.getSecurity().getAuthentication().getJwt().getSecret();
        this.tokenValidityInSeconds =
                1000 * properties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInSecondsForRememberMe =
                1000 * properties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication, Boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now);
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInSecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInSeconds);
        }

        TokenUser tokenUser = (TokenUser) authentication.getPrincipal();
        String json = GsonUtil.toJSON(tokenUser.getUser());
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(TOKEN_USER_KEY, json)
                .claim(TOKEN_USER_ID_KEY, tokenUser.getUserId())
                .claim(CURRENT_AREA_CODE, tokenUser.getAreaCode())
                .claim(CURRENT_COMPANY_ID, tokenUser.getCurrentCompanyId())
                .claim(SUPER_ADMIN, tokenUser.getSuperAdmin())
                .claim(EMPLOYEE_ID, tokenUser.getEmployeeId())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

//        String principal = claims.getSubject();
        String json = claims.get(TOKEN_USER_KEY).toString();

        //TODO 后面修改
        Collection<? extends GrantedAuthority> authorities =
                Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
                        .map(authority -> new SimpleGrantedAuthority(authority))
                        .collect(Collectors.toList());

//        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

        TokenUser principal = new TokenUser(claims.getSubject(), "", authorities);
        principal.setUser(GsonUtil.getJSON(json, SUser.class));
        principal.setUserId((Integer) (claims.get(TOKEN_USER_ID_KEY)));
        if(claims.get(CURRENT_COMPANY_ID)!=null){
            principal.setCurrentCompanyId((Integer)(claims.get(CURRENT_COMPANY_ID)));
        }
        if (claims.get(CURRENT_AREA_CODE) != null) {
            principal.setAreaCode((String) (claims.get(CURRENT_AREA_CODE)));
        }
        if (claims.get(SUPER_ADMIN) != null) {
            principal.setSuperAdmin((Boolean) (claims.get(SUPER_ADMIN)));
        }
        if (claims.get(EMPLOYEE_ID) != null) {
            principal.setEmployeeId((Integer) (claims.get(EMPLOYEE_ID)));
        }


        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT signature: " + e.getMessage());
            return false;
        }
    }
}
