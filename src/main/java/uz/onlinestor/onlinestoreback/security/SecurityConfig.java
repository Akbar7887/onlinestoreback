package uz.onlinestor.onlinestoreback.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.onlinestor.onlinestoreback.filter.CustomAuthenticationFilter;
import uz.onlinestor.onlinestoreback.filter.CustomAuthorizationFilter;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter((authenticationManagerBean()));
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(
                "/api/token/refresh/**",
                "/login", "/login/**",
                "/online/doc/catalog/**",
                "/online/doc/product/**",
                "/online/doc/characteristic/**",
                "/online/doc/productImage/**",
                "/online/doc/exchangeRates/**",
                "/online/doc/rates/**",
                "/online/doc/comment/**",
                "/online/organization/**",
                "/online/doc/user/**"


        ).permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/users/**", "/login/**",
//                "/meneger/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/user/save/**", "/api/token/refresh/**",
//                "/login/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(PUT, "/api/user/save/**", "/api/token/refresh/**",
//                "/login/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
