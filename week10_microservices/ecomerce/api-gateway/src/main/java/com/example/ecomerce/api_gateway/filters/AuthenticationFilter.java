package com.example.ecomerce.api_gateway.filters;

import com.example.ecomerce.api_gateway.dtos.ValidUserResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestClient restClient;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                try {
                    log.info("logging from auth filter : {} \n {} ", exchange.getRequest().getURI(), exchange.getRequest().getSslInfo());
                    HttpHeaders authenticationHeaders = exchange.getRequest().getHeaders();
                    ServiceInstance instance = discoveryClient.getInstances("auth-service").getFirst();
                    String token = authenticationHeaders.getFirst("Authorization");
                    if (token == null || token.isBlank()) {
                        return unauthorizedResponse(exchange);
                    }
                    URI authUri = instance.getUri();
                    log.info(authUri.getPath());
                    ValidUserResponseDto userDto =  restClient.post()
                            .uri(instance.getUri() + "/auth/valid")
                            .header("Authorization",token)
                            .retrieve()
                            .body(ValidUserResponseDto.class);

                    if(userDto == null)
                        return unauthorizedResponse(exchange);

                    String userJson = new ObjectMapper().writeValueAsString(userDto);
                    ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                            .header("CurrentUser", userJson)
                            .build();
                    ServerWebExchange mutatedExchange = exchange.mutate()
                            .request(mutatedRequest)
                            .build();
                    return chain.filter(mutatedExchange);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
        byte[] bytes = "{\"error\":\"Unauthorized access\"}".getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    public static class Config {
    }
}
