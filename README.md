tRPC Spring HTTP Interface
=============================
 
tRPC HTTP Interface for Spring Framework 6 with [HTTP Interface Feature](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#rest-http-interface)
              
# Requirements

* Spring Framework 6
* Java 17+ 

# Get Started

* Add dependency in your pom.xml

```xml

<dependency>
    <groupId>org.mvnsearch</groupId>
    <artifactId>trpc-http-interface</artifactId>
    <version>0.1.0</version>
</dependency>
```

* Create tRPC HTTP interface

```java
import org.mvnsearch.spring.trpc.exchange.*;

@TrpcExchange("http://localhost:8080")
public interface HelloService {
    @QueryExchange("/greeting.hello")
    TrpcResponse<String> greeting(@TrpcInput Hello name);

    @MutateExchange("/poster.createPost")
    TrpcResponse<Post> createPost(@TrpcInput Post post);

    record Hello(String name) {
    }

    record Post(String id, String title, String text) {
    }
}
```

* Build tRPC HTTP interface stub and call remote services

```java
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mvnsearch.spring.trpc.exchange.TrpcArgumentResolver;
import org.mvnsearch.spring.trpc.exchange.TrpcResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class TrpcClientTest {
    private static HelloService helloService;

    @BeforeAll
    public static void setUp() {
        WebClient webClient = WebClient.builder().build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder()
                .clientAdapter(WebClientAdapter.forClient(webClient))
                // @TrpcInput argument resolver
                .customArgumentResolver(new TrpcArgumentResolver())
                .build();
        helloService = httpServiceProxyFactory.createClient(HelloService.class);
    }

    @Test
    public void testQuery() {
        final TrpcResponse<String> trpcResponse = helloService.greeting(new HelloService.Hello("World"));
        System.out.println(trpcResponse.getResult().getData());
    }

    @Test
    public void testMutate() {
        final TrpcResponse<HelloService.Post> trpcResponse = helloService.createPost(new HelloService.Post("1", "Hello", "Hello World"));
        System.out.println(trpcResponse.getResult().getData());
    }
}
```

# FAQ

### Can I use Spring HTTP interface annotations with tRPC? 

Yes, you can. tRPC HTTP interface is based with Spring HTTP interface annotations, 
and you can use HTTP interface annotations for method parameter, for example `@RequestHeader`, `@PathVariable`.

### How to build tRPC backend services with Spring Boot?

Please refer [Spring Boot Starter tRPC](https://github.com/linux-china/trpc-spring-boot-starter)

# References

* tRPC: https://trpc.io/
* Spring HTTP Interface: https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#rest-http-interface

