package ${baseCommonPackage}.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateUser {

    boolean create() default false;
}
