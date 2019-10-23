package org.xi.quick.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "quick")
@Validated
@Data
public class WebProperties {

    /**
     * 验证码过期时长 (单位是秒，最短为10s)
     */
    @Min(10)
    private int captchaExpirationTime = 30;

    /**
     * token 过期时长 (单位是分钟，最短为10min)
     */
    @Min(10)
    private int tokenExpirationTime = 60;

    /**
     * 超级管理员用户类型
     */
    private Set<String> rootUserRoles;

    /**
     * 允许匿名的路径
     */
    private Set<String> anonymousUrls;
}
