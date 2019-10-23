package org.xi.quick.advice;

import org.xi.quick.common.constant.OperationConstants;
import org.xi.quick.common.model.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public ResponseVo handleBaseException(AuthorizationException e) {
        return new ResponseVo(OperationConstants.HAS_NO_PERMISSION);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseBody
    public ResponseVo handleBaseException(IncorrectCredentialsException e) {
        return new ResponseVo(OperationConstants.TOKEN_EXPIRED);
    }
}
