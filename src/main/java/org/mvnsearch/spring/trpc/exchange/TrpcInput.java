package org.mvnsearch.spring.trpc.exchange;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrpcInput {
}
