package com.jgos.hotelbooker.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/api/register")
                .permitAll()
                .and()
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest()
                .hasAuthority(Authorities.ROLE_USER.name())
                .anyRequest()
                .authenticated();
    }
}
