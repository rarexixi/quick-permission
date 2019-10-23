package org.xi.quick.sys.service;

import java.awt.image.BufferedImage;

/**
 * 系统验证码
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface CaptchaService {

    /**
     * 获取验证码
     *
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证
     *
     * @param uuid
     * @param code
     * @return
     */
    boolean validateCaptcha(String uuid, String code);
}
