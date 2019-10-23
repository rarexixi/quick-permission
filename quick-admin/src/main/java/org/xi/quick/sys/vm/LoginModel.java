package org.xi.quick.sys.vm;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginModel {

    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String captcha;
    @NotBlank(message = "uuid不能为空")
    private String uuid;
}
