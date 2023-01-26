package org.mvnsearch.spring.trpc.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.service.invoker.HttpRequestValues;
import org.springframework.web.service.invoker.HttpServiceArgumentResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TrpcArgumentResolver implements HttpServiceArgumentResolver {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean resolve(Object argument, MethodParameter parameter, HttpRequestValues.Builder requestValues) {
        boolean isTrpcInput = false;
        for (Annotation annotation : parameter.getParameterAnnotations()) {
            if (annotation.annotationType().getName().endsWith("TrpcInput")) {
                isTrpcInput = true;
            }
        }
        final Method requestMethod = parameter.getMethod();
        if (requestMethod != null && isTrpcInput)
            if (requestMethod.getAnnotation(QueryExchange.class) != null) {
                try {
                    requestValues.addRequestParameter("input", objectMapper.writeValueAsString(argument));
                    return true;
                } catch (Exception ignore) {

                }
            } else if (requestMethod.getAnnotation(MutateExchange.class) != null) {
                requestValues.setBodyValue(argument);
                return true;
            }
        return false;
    }
}
