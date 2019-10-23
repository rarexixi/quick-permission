package org.xi.quick.configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.xi.quick.common.annotation.UpdateUser;
import org.xi.quick.common.model.KeyValueModel;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Consumer;

@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "6");
        properties.put("kaptcha.textproducer.font.names", "Arial,Courier,cmr10,宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }



    /**
     * 获取需要更新用户的controller操作
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Bean(name = "methodsNeedToUpdateUser")
    public Map<String, List<KeyValueModel<Integer, Boolean>>> methodsNeedToUpdateUser() throws IOException, ClassNotFoundException {

        Map<String, List<KeyValueModel<Integer, Boolean>>> methods = new HashMap<>();

        Consumer<Method> consumerMethod = method -> {
            Parameter[] methodParameters = method.getParameters();
            List<KeyValueModel<Integer, Boolean>> parameterNeedToUpdateUserIndexes = new LinkedList<>();
            for (int i = 0; i < methodParameters.length; i++) {
                Parameter parameter = methodParameters[i];
                UpdateUser updateUser = parameter.getAnnotation(UpdateUser.class);
                if (updateUser == null) continue;
                parameterNeedToUpdateUserIndexes.add(new KeyValueModel<>(i, updateUser.create()));
            }
            if (!parameterNeedToUpdateUserIndexes.isEmpty()) {
                methods.put(method.toString(), parameterNeedToUpdateUserIndexes);
            }
        };

        setMethodNeedToUpdateUser("org.xi.quick.controller", consumerMethod);

        return methods;
    }


    private void setMethodNeedToUpdateUser(String packageName, Consumer<Method> consumerMethod) throws IOException, ClassNotFoundException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                packageName.replace('.', '/') + "/*.class";
        Resource[] resources = resolver.getResources(packageSearchPath);

        for (Resource resource : resources) {
            String path = resource.getURI().toString().replaceAll("/", "\\.");
            String className = path.substring(path.lastIndexOf(packageName)).replace(".class", "");
            Class<?> clazz = classLoader.loadClass(className);
            Method[] classMethods = clazz.getMethods();
            for (Method method : classMethods) {
                consumerMethod.accept(method);
            }
        }
    }
}
