package org.xi.quick.sys.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.xi.quick.configuration.properties.WebProperties;
import org.xi.quick.sys.mapper.CaptchaMapper;
import org.xi.quick.sys.models.entity.CaptchaEntity;
import org.xi.quick.sys.models.entity.extension.CaptchaEntityExtension;
import org.xi.quick.sys.service.CaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 系统验证码
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("captchaService")
@Transactional
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    public CaptchaServiceImpl(CaptchaMapper captchaMapper, DefaultKaptcha captchaProducer, WebProperties webProperties) {
        this.captchaMapper = captchaMapper;
        this.captchaProducer = captchaProducer;
        this.webProperties = webProperties;
    }

    private CaptchaMapper captchaMapper;
    private DefaultKaptcha captchaProducer;
    private WebProperties webProperties;

    /**
     * 获取验证码
     *
     * @param uuid
     * @return
     */
    @Override
    public BufferedImage getCaptcha(String uuid) {

        //生成文字验证码
        String code = captchaProducer.createText();
        CaptchaEntity entity = new CaptchaEntity();
        entity.setUuid(uuid);
        entity.setCode(code);
        entity.setExpireAt(new Date(new Date().getTime() + webProperties.getCaptchaExpirationTime() * 1000));
        captchaMapper.replaceInto(entity);

        BufferedImage image = captchaProducer.createImage(code);
        return image;
    }

    /**
     * 验证
     *
     * @param uuid
     * @param code
     * @return
     */
    @Override
    public boolean validateCaptcha(String uuid, String code) {
        CaptchaEntityExtension entity = captchaMapper.getByPk(uuid);
        return code.equals(entity.getCode()) && entity.getExpireAt().getTime() > new Date().getTime();
    }
}
