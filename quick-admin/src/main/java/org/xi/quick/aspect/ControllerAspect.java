package org.xi.quick.aspect;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.xi.quick.common.constant.OperationConstants;
import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.model.KeyValueModel;
import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.common.model.UserModel;
import org.xi.quick.common.utils.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Component
@Aspect
public class ControllerAspect {

    private final LogUtils logger = LogUtils.build(ControllerAspect.class);

    @Autowired
    HttpServletRequest request;

    @Autowired
    @Qualifier("methodsNeedToUpdateUser")
    Map<String, List<KeyValueModel<Integer, Boolean>>> methodsNeedToUpdateUser;

    /**
     * 设置标识
     */
    @Pointcut("execution(public org.xi.quick.common.model.ResponseVo org.xi.quick.*.controller.*.*(..))")
    public void invoke() {
    }


    /**
     * 环绕方法执行，proceedingJoinPoint.proceed()是执行环绕的方法
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("invoke()")
    public Object PlayAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Signature signature = proceedingJoinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return proceedingJoinPoint.proceed();
        }

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = request.getRemoteHost() + method.getDeclaringClass().getName() + "." + method.getName();

        try {
            Object[] args = proceedingJoinPoint.getArgs();
            List<String> messages = getErrorMessage(args);
            if (!messages.isEmpty()) {
                logger.error(methodName, "参数验证失败", messages);
                return new ResponseVo<>(OperationConstants.PARAMETER_VALIDATION_FAILED, messages);
            }

            setUser(method, args);
            Object result = proceedingJoinPoint.proceed();
            return result;
        } catch (ConstraintViolationException e) {
            logger.error(methodName, "参数验证失败", e);
            List<String> messages = getErrorMessage(e);
            return new ResponseVo<>(OperationConstants.PARAMETER_VALIDATION_FAILED, messages);
        } catch (ValidationException e) {
            logger.error(methodName, "参数验证失败", e);
            return new ResponseVo<>(OperationConstants.PARAMETER_VALIDATION_FAILED, e.getMessage());
        } catch (DuplicateKeyException e) {
            logger.error(methodName, "数据唯一索引冲突", e);
            return new ResponseVo<>(OperationConstants.DUPLICATE_KEY);
        } catch (Exception e) {
            logger.error(methodName, "服务出现异常", e);
        }

        return new ResponseVo<>(OperationConstants.SYSTEM_ERROR);
    }

    /**
     * 获取非Errors类型参数，否则转json报错
     *
     * @param args
     * @return
     */
    private List<Object> getParameters(Object[] args) {
        List<Object> parameters = new LinkedList<>();
        for (Object arg : args) {
            if (arg instanceof Errors) continue;
            parameters.add(arg);
        }
        return parameters;
    }

    private void setUser(Method method, Object[] args) {

        UserModel user = (UserModel) SecurityUtils.getSubject().getPrincipal();
        if (user == null) return;
        List<KeyValueModel<Integer, Boolean>> indexes;
        if ((indexes = methodsNeedToUpdateUser.getOrDefault(method.toString(), null)) == null) return;
        for (KeyValueModel<Integer, Boolean> kv : indexes) {
            Object arg = args[kv.getKey()];
            if (!(arg instanceof BaseEntity)) continue;
            BaseEntity baseEntity = (BaseEntity) arg;
            baseEntity.setUpdateUser(user.getUserId());
            if (kv.getValue()) {
                baseEntity.setCreateUser(user.getUserId());
            }
        }
    }

    /**
     * 获取拦截的验证失败信息
     *
     * @param exception
     * @return
     */
    private List<String> getErrorMessage(ConstraintViolationException exception) {
        List<String> messages = new LinkedList<>();
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation violation : violations) {
            messages.add(violation.getMessage());
        }
        return messages;
    }

    /**
     * 获取拦截的Errors信息
     *
     * @param args
     * @return
     */
    private List<String> getErrorMessage(Object[] args) {
        List<String> messages = new LinkedList<>();
        for (Object arg : args) {
            if (arg instanceof Errors && ((Errors) arg).hasErrors()) {
                for (ObjectError objectError : ((Errors) arg).getAllErrors()) {
                    messages.add(objectError.getDefaultMessage());
                }
            }
        }
        return messages;
    }
}
