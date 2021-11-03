package com.senla.electric.scooter.rental.security;

import com.senla.electric.scooter.rental.security.filter.CustomAuthenticationFilter;
import com.senla.electric.scooter.rental.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/admins").permitAll();

        http.authorizeRequests().antMatchers("/users/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers("/rental-points/users/**,")
                .hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers("/rentals/users/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers("/scooters/users/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers("/scooter-prices/users/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers("/accounts/**").hasAnyAuthority("USER");

        http.authorizeRequests().antMatchers("/admins/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/rentals/admins/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/rental-points/admins/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/scooters/admins/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers("scooter-prices/admins/**").hasAnyAuthority("ADMIN");

        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
