package annotation.basic;

import annotation.mapping.SimpleMapping;
import util.MyLogger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // TYPE -> 클래스
public @interface AnnoElemnt {
    String value();
    int count() default 0;
    String[] tags() default {};

    // MyLogger data(); // 다른 타입은 적용x
    // 클래스 정보는 가능
    Class<? extends MyLogger> annoData() default MyLogger.class; // 클래스 정보는 가능
}
