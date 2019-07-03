package com.wisdom.controller;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.dto.LoginDTO;
import com.wisdom.iwcs.service.base.IBaseWhAreaService;
import com.wisdom.iwcs.service.base.baseImpl.BaseWhAreaService;
import com.wisdom.iwcs.service.security.TokenUser;
import com.wisdom.iwcs.service.security.jwt.JWTConfigurer;
import com.wisdom.iwcs.service.security.jwt.TokenProvider;
import com.wisdom.socket.utils.TokenUtil;
import com.wisdom.socket.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserJWTController {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private IBaseWhAreaService iBaseWhAreaService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authorize(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername() + "," + loginDTO.getAreaCode(), loginDTO.getPassword());

        //如果选择了库区，判断是否存在库区是否存在用户
        if(!Strings.isNullOrEmpty(loginDTO.getAreaCode())){
            iBaseWhAreaService.checkWhAreaAndUser(loginDTO);
        }

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginDTO.isRememberMe() == null) ? false : loginDTO.isRememberMe();
            String jwt = "Bearer " + tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, jwt);
            response.addHeader(JWTConfigurer.ACCESS_CONTROL_EXPOSE_HEAEDERS, "Authorization");

            TokenUser tokenUser = (TokenUser) authentication.getPrincipal();
            User user = new User();
            user.setCompanyCode(String.valueOf(tokenUser.getCurrentCompanyId()));
            user.setUserId(String.valueOf(tokenUser.getUserId()));
            String socketToken = tokenUtil.createToken(user, rememberMe);

            return ResponseEntity.ok(new Result(new JWTToken(jwt, socketToken)));
        } catch (AuthenticationException exception) {
            exception.printStackTrace();
//        	return new ResponseEntity<>("{\"name\":\"wang\"}", HttpStatus.UNAUTHORIZED);
//            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
