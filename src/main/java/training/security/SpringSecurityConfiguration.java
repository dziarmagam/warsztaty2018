package training.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
@EnableWebSecurity
class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService securityUserService;
    private final AuthenticationUserDetailsServiceImpl authenticationUserDetailsService;

    SpringSecurityConfiguration(UserDetailsService securityUserService,
                                AuthenticationUserDetailsServiceImpl authenticationUserDetailsService) {
        this.securityUserService = securityUserService;
        this.authenticationUserDetailsService = authenticationUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserService);

//                and()
//        .authenticationEventPublisher()authenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authenticationProvider(preauthAuthProvider())
                .authorizeRequests()
                .anyRequest().permitAll();
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
//                .antMatchers(HttpMethod.POST, "/users/").permitAll()
//                .anyRequest().hasAnyRole("ADMIN")
//                .and()
//                .httpBasic();
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preauthAuthProvider() {
        PreAuthenticatedAuthenticationProvider preauthAuthProvider =
                new PreAuthenticatedAuthenticationProvider();
        preauthAuthProvider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService);
        return preauthAuthProvider;
    }
}
