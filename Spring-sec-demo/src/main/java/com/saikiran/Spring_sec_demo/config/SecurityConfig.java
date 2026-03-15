package com.saikiran.Spring_sec_demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public AuthenticationProvider authProvider(){

        DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);


        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return provider;

    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{




        http
                .csrf(customizer->customizer.disable())
        .authorizeHttpRequests(request->request.anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user= User
//                .withDefaultPasswordEncoder()
//                .username("saikiran")
//                .password("12345678")
//                .roles("USER")
//                .build();
//
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("archana")
//                .password("12345678")
//                .roles("ADMIN")
//                .build();
//            return new InMemoryUserDetailsManager(user,user1);
//    }
}
