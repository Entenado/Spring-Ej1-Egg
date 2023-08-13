
package com.egg.Ej1_Spring;

import com.egg.Ej1_Spring.servicio.usuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb {
    
    @Autowired
    public usuarioServicio usuarioServicio;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
    
        auth.userDetailsService(usuarioServicio)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .requestMatchers("/adminn/*").hasRole("ADMIN")
            .requestMatchers("/css/*","/js/*","/img/*","/**")
            .permitAll()
            .and().formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/logincheck")
            .usernameParameter("nombreUsuario")
            .passwordParameter("password")
            .defaultSuccessUrl("/admin/lista")
            .permitAll()
            .and().logout()  
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
            .and().csrf()
                 .disable();                
         return http.build();
    }
    
}
