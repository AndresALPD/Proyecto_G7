package com.Proyecto;

import static ch.qos.logback.classic.spi.LoggingEventVO.build;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer{
    //Los siguientes 5 metodos son para trabajar la parte de intenacionalización
    
    @Bean
    public  LocaleResolver localeResolver(){
        var slr = new SessionLocaleResolver();
        
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");    
        slr.setTimeZoneAttributeName("session.currente.timezone");
        
        return slr;
    }
    
    @Bean
    public  LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();       
        lci.setParamName("lang");       
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/errores/**",
                        "/carrito/**", "/pruebas/**", "/reportes/**",
                        "/registro/**", "/js/**", "/webjars/**" , "/contacto/**")
                .permitAll()
                .requestMatchers(
                        "/producto/nuevo", "/producto/guardar",
                        "/producto/modificar/**", "/producto/eliminar/**",
                        "/pedidos/nuevo", "/pedidos/guardar",
                        "/pedidos/modificar/**", "/pedidos/eliminar/**",
                        "/resenas/nuevo", "/resenas/guardar",
                        "/resenas/modificar/**", "/resenas/eliminar/**",                       
                        "/usuario/nuevo", "/usuario/guardar",
                        "/usuario/modificar/**", "/usuario/eliminar/**",
                        "/reportes/**"
                ).hasRole("ADMIN")
                .requestMatchers(
                        "/producto/listado",
                        "/pedidos/listado",
                        "/resenas/listado",
                        "/usuario/listado"
                ).hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers("/facturar/carrito")
                .hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
    
    @Autowired
    private UserDetailsService userDetailsService;
    
   @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) 
            throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(
                new BCryptPasswordEncoder());        
    }

}