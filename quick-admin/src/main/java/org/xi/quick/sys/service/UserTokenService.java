package org.xi.quick.sys.service;

import org.xi.quick.sys.vm.detail.UserTokenDetailVm;

/**
 * 系统用户Token
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface UserTokenService {

    /**
     * 根据token获取系统用户Token详情
     *
     * @param token
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    UserTokenDetailVm getDetail(String token);

    /**
     * 创建用户Token
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    String getToken(Integer userId) throws Exception;
}
