package pl.kraft.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kraft.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import pl.kraft.security.jwt.JwtVerifierFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtVerifierFilter(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers(
                "/css/**",
                "/img/**",
                "/js/**",
                "/font/**",
                "/page/**",
                "/",
                "/sign-up",
                "/sign-in",
                "/faq",
                "/projects-offers",
                "/profiles",
                "/my-profile",
                "/project",
                "/edycja",
                "/addProject",
                "/youtube",
                "/api/projects/threeAdded/projects",
                "/api/subjects?type=unique",
                "/api/subjects?type=all",
                "/api/abilities").permitAll()
                .antMatchers(HttpMethod.POST, "/api/students").permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .loginPage("/sign-in")
                .defaultSuccessUrl("/", true)
                .permitAll();
        /*http.cors().disable().csrf().disable().authorizeRequests().anyRequest().permitAll();*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
