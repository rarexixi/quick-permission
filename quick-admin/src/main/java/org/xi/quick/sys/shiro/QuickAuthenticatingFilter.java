package org.xi.quick.sys.shiro;

import com.alibaba.fastjson.JSON;
import org.xi.quick.common.constant.OperationConstants;
import org.xi.quick.common.model.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xi.quick.common.utils.RequestUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuickAuthenticatingFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = RequestUtils.getToken((HttpServletRequest) request);

        if (StringUtils.isBlank(token)) {
            return null;
        }

        return new QuickAuthenticationToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = RequestUtils.getToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", getOrigin((HttpServletRequest) request));

            String json = JSON.toJSONString(new ResponseVo<>(OperationConstants.NOT_LOGIN));
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().print(json);
            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", getOrigin((HttpServletRequest) request));
        try {
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            String json = JSON.toJSONString(new ResponseVo<>(OperationConstants.TOKEN_EXPIRED, throwable.getMessage()));
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().print(json);
        } catch (IOException ex) {

        }

        return false;
    }

    private String getOrigin(HttpServletRequest request) {
        return request.getHeader("Origin");
    }

}
