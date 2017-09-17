package com.jgos.hotelbooker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Bos on 2017-08-15.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // api security is handled elsewhere (See ResourceServiceConfig)
                .antMatchers("/api/**")
                .permitAll()
                // end api security
                //.antMatchers("/").permitAll()
                .anyRequest().hasAuthority(Authorities.ROLE_WEB.name())
                //.anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/index", false)
                .permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/login?logout=true")
                .and().exceptionHandling().accessDeniedPage("/login?noAccess=true");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        // .inMemoryAuthentication()
        // .withUser("user").password("password").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**", "/bootstrap/**");

    }


}