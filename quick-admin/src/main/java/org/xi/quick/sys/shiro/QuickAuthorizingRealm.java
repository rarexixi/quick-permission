package org.xi.quick.sys.shiro;

import org.xi.quick.common.model.UserModel;
import org.xi.quick.configuration.properties.WebProperties;
import org.xi.quick.sys.service.UserService;
import org.xi.quick.sys.service.UserTokenService;
import org.xi.quick.sys.vm.detail.UserDetailVm;
import org.xi.quick.sys.vm.detail.UserTokenDetailVm;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class QuickAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private WebProperties webProperties;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof QuickAuthenticationToken;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        UserModel user = (UserModel) principals.getPrimaryPrincipal();
        Set<String> roles = userService.getUserRoles(user.getUserId());

        //用户权限列表
        Set<String> permissions = new HashSet<>(1);
        boolean isRoot = false;
        for (String role : roles) {
            // 如果是超级用户，赋予所有权限
            if (webProperties.getRootUserRoles().contains(role)) {
                permissions.add("*");
                isRoot = true;
                break;
            }
        }
        if (!isRoot) {
            permissions = userService.getUserPermissions(user.getUserId());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据token，查询用户信息
        UserTokenDetailVm userToken = userTokenService.getDetail(accessToken);
        //token失效
        if (userToken == null || userToken.getExpireAt().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        UserDetailVm u = userService.getDetail(userToken.getUserId());
        UserModel user = new UserModel(u.getUserId(), u.getUsername(), u.getEmail(), u.getMobile(), u.getRealName(), u.getAvatar());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
