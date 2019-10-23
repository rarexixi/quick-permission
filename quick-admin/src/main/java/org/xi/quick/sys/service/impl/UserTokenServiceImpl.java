package org.xi.quick.sys.service.impl;

import org.xi.quick.common.TokenGenerator;
import org.xi.quick.configuration.properties.WebProperties;
import org.xi.quick.sys.mapper.UserTokenMapper;
import org.xi.quick.sys.models.condition.UserTokenCondition;
import org.xi.quick.sys.models.entity.UserTokenEntity;
import org.xi.quick.sys.service.UserTokenService;
import org.xi.quick.sys.vm.detail.UserTokenDetailVm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 系统用户Token
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("userTokenService")
@Transactional
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    public UserTokenServiceImpl(UserTokenMapper userTokenMapper, WebProperties webProperties) {
        this.userTokenMapper = userTokenMapper;
        this.webProperties = webProperties;
    }

    private UserTokenMapper userTokenMapper;
    private WebProperties webProperties;

    /**
     * 根据主键获取系统用户Token详情
     *
     * @param token
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public UserTokenDetailVm getDetail(String token) {
        UserTokenCondition condition = new UserTokenCondition();
        condition.setToken(token);
        UserTokenEntity entity = userTokenMapper.getByCondition(condition);
        if (entity == null) return null;
        UserTokenDetailVm vm = new UserTokenDetailVm(entity);
        return vm;
    }

    /**
     * 创建用户Token
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public String getToken(Integer userId) throws Exception {
        String token = TokenGenerator.generateValue();
        UserTokenEntity entity = new UserTokenEntity();
        entity.setUserId(userId);
        entity.setToken(token);
        entity.setExpireAt(new Date(new Date().getTime() + webProperties.getTokenExpirationTime() * 60 * 1000));
        userTokenMapper.replaceInto(entity);
        return token;
    }
}
