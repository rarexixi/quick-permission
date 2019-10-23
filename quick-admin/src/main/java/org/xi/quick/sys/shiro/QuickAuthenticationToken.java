package org.xi.quick.sys.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class QuickAuthenticationToken implements AuthenticationToken {

    private String token;

    public QuickAuthenticationToken(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
