
package com.Runner.Runner.config;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Tee
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, proxyTargetClass=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        
       
        // polkujen autentisoinnit, rekisteröinti ja pääsivu avoinna kaikille,
        // muuhun tarvitaan autentikointi.
        http.authorizeRequests()
                .antMatchers("h2-console/*").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/home").permitAll()
                .antMatchers(HttpMethod.GET, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/newaccount").permitAll()
                .antMatchers(HttpMethod.GET, "/success").permitAll()
                .antMatchers(HttpMethod.GET, "/userlogged").authenticated()
                .antMatchers(HttpMethod.GET, "/runs").authenticated()
                .antMatchers(HttpMethod.POST, "/run").authenticated()
                .anyRequest().authenticated();
                
        http.formLogin()
                .permitAll()
                .defaultSuccessUrl("/userlogged")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home");
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
