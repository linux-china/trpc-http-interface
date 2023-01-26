package org.mvnsearch.spring.trpc.exchange;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.service.annotation.HttpExchange;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
@HttpExchange
public @interface TrpcExchange {

    @AliasFor(annotation = HttpExchange.class)
    String value() default "";

    @AliasFor(annotation = HttpExchange.class)
    String contentType() default "application/json";

    @AliasFor(annotation = HttpExchange.class)
    String[] accept() default {"application/json"};
}
