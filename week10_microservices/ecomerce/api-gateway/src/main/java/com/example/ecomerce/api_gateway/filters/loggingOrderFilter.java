package com.example.ecomerce.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class loggingOrderFilter extends AbstractGatewayFilterFactory<loggingOrderFilter.Config> {

    public loggingOrderFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                log.info("logging from order filter : {} \n {} ",exchange.getRequest().getURI(),exchange.getRequest().getSslInfo());
                return chain.filter(exchange).then(Mono.fromRunnable(
                        new Runnable() {
                            @Override
                            public void run() {
                                log.info("logging from order filter post : {}",exchange.getResponse().getStatusCode());
                            }
                        }
                ));
            }
        };
    }


    public static class Config {

    }
}
