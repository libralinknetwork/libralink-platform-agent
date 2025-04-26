package io.libralink.platform.agent.suite.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Value("${libralink.local.development.disable.csrf:false}")
    private boolean springCsrfDisable;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(a -> a
            .antMatchers("/", "/error",
                "/v2/api-docs", "/v2/api-docs/**",
                "/swagger-ui/**", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**",
                "/api/logout", "/protocol/**"
            ).permitAll()
        )
        .exceptionHandling(e -> e
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        );

        if (springCsrfDisable) {
            http.csrf().disable();
        }
    }
}