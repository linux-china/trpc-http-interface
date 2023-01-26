package org.mvnsearch.spring.trpc.exchange;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.service.annotation.HttpExchange;

import java.lang.annotation.*;

/**
 * tRPC mutate exchange
 *
 * @author linux_china
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@HttpExchange(method = "POST")
public @interface MutateExchange {
    @AliasFor(annotation = HttpExchange.class)
    String value() default "";

    /**
     * Alias for {@link HttpExchange#url()}.
     */
    @AliasFor(annotation = HttpExchange.class)
    String url() default "";

    @AliasFor(annotation = HttpExchange.class)
    String contentType() default "application/json";
}
