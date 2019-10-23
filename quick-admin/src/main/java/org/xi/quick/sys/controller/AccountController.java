package org.xi.quick.sys.controller;

import org.xi.quick.common.constant.OperationConstants;
import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.common.model.UserModel;
import org.xi.quick.common.utils.security.CryptoUtils;
import org.xi.quick.configuration.properties.WebProperties;
import org.xi.quick.sys.service.CaptchaService;
import org.xi.quick.sys.service.UserService;
import org.xi.quick.sys.service.UserTokenService;
import org.xi.quick.sys.vm.LoginModel;
import org.xi.quick.sys.vm.ResetPasswordModel;
import org.xi.quick.sys.vm.detail.MenuDetailVm;
import org.xi.quick.sys.vm.detail.UserDetailVm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 账号管理
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/account")
@RestController
@Validated
@Slf4j
public class AccountController {

    private final UserService userService;
    private final CaptchaService captchaService;
    private final UserTokenService userTokenService;
    private WebProperties webProperties;

    @Autowired
    public AccountController(UserService userService, CaptchaService captchaService, UserTokenService userTokenService, WebProperties webProperties) {
        this.userService = userService;
        this.captchaService = captchaService;
        this.userTokenService = userTokenService;
        this.webProperties = webProperties;
    }

    @PostMapping("/login")
    public ResponseVo login(@Validated @RequestBody LoginModel loginModel, Errors errors) throws Exception {

//        boolean captcha = captchaService.validateCaptcha(loginModel.getUuid(), loginModel.getCaptcha());
//        if (!captcha) {
//            return new ResponseVo<>(OperationConstants.CAPTCHA_ERROR);
//        }

        //用户信息
        UserDetailVm user = userService.getUserAccount(loginModel.getAccount());

        //账号不存在、密码错误
//        String password = CryptoUtils.getSHA256(loginModel.getPassword(), user.getSalt());
//        if (user == null || !user.getPassword().equals(password)) {
//            return new ResponseVo(OperationConstants.VALIDATE_FAILURE);
//        }

        String token = userTokenService.getToken(user.getUserId());
        return new ResponseVo(token);
    }

    @PostMapping("validate-permissions")
    public ResponseVo<Map<String, Boolean>> validatePermission(@RequestBody List<String> permissions) {
        UserModel user = getCurrentUser();
        Map<String, Boolean> result = new HashMap<>();

        Set<String> roles = userService.getUserRoles(user.getUserId());
        for (String role : roles) {
            // 如果是超级用户，赋予所有权限
            if (webProperties.getRootUserRoles().contains(role)) {
                for (String permission : permissions) {
                    result.put(permission, true);
                }
                return new ResponseVo<>(result);
            }
        }

        Set<String> permissionSet = userService.getUserPermissions(user.getUserId());
        for (String permission : permissions) {
            result.put(permission, permissionSet.contains(permission));
        }
        return new ResponseVo<>(result);
    }

    /**
     * 重置密码
     *
     * @return
     */
    @PostMapping("reset-password")
    private ResponseVo resetPassword(@Validated @RequestBody ResetPasswordModel resetPasswordModel, Errors errors) {
        UserModel currentUser = getCurrentUser();
        UserDetailVm user = userService.getDetail(currentUser.getUserId());

        //密码错误
        String password = CryptoUtils.getSHA256(resetPasswordModel.getPassword(), user.getSalt());
        if (user == null || !user.getPassword().equals(password)) {
            return new ResponseVo(OperationConstants.VALIDATE_FAILURE);
        }
        int result = userService.setPassword(currentUser.getUserId(), resetPasswordModel.getNewPassword());
        return new ResponseVo((result));
    }

    /**
     * 发送短信验证码
     *
     * @return
     */
    public ResponseVo sendSms() {
        return null;
    }

    /**
     * 忘记密码
     *
     * @return
     */
    public ResponseVo forgetPassword() {
        return null;
    }

    @GetMapping("/logout")
    public ResponseVo logout() {
        return null;
    }

    @GetMapping("/get-info")
    public ResponseVo getUserInfo() {
        UserModel user = getCurrentUser();
        return new ResponseVo(user);
    }

    @GetMapping("/get-nav-menus")
    public ResponseVo<List> getNavMenus() {
        UserModel user = getCurrentUser();
        List<MenuDetailVm> menuDetailVms = userService.getUserNavMenus(user.getUserId());
        return new ResponseVo<>(menuDetailVms);
    }

    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response, @NotBlank(message = "uuid不能为空") String uuid) throws Exception {

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        BufferedImage image = captchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    private UserModel getCurrentUser() {
        return (UserModel) SecurityUtils.getSubject().getPrincipal();
    }
}
