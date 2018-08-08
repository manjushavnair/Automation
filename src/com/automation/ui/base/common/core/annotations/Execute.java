package com.automation.ui.base.common.core.annotations;

import com.automation.ui.base.common.auth.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface Execute {

    User asUser() default User.ANONYMOUS;

    String onSite() default "";

    String disableFlash() default "";

    String mockAds() default "";

    String language() default "";

    boolean trackingOptIn() default true;

    boolean trackingOptOut() default false;
}
