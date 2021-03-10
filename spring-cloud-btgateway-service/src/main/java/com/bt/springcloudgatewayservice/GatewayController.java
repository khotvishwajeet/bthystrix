package com.bt.springcloudgatewayservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewayController {

    @RequestMapping("/firstservicefallback")
    public Mono<String> firstservicefallback() {
        return Mono.just("firstservice API is taking too long to respond or is down. Please try again later");
    }
    @RequestMapping("/secondservicefallback")
    public Mono<String> secondservicefallback() {
        return Mono.just("secondservice API is taking too long to respond or is down. Please try again later");
    }
}
