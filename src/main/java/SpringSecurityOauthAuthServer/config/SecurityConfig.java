package test1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import test1.entity.UserAuthDTO;
import test1.repository.UserRepository;
import test1.service.CustomFilter;
import test1.service.CustomPermissionEvaluator;
import test1.service.ShowCSRFFilter;

import java.util.Collection;
import java.util.List;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
  //  private final AuthenticationProvider authenticationProvider;
    private final CustomPermissionEvaluator evaluator;
 //   private final CustomFilter customFilter;
 //   private final ShowCSRFFilter showCSRFFilter;
/*
    @Bean
    SecurityFilterChain configure(HttpSecurity http) {
        return http
                 .addFilterAt(customFilter, BasicAuthenticationFilter.class)
             //       .httpBasic(Customizer.withDefaults())
                //.addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class)
            //    .addFilterBefore(customFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll())
                .build();
    }
*/

    @Bean
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        var expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(evaluator);
        return expressionHandler;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) {


    return http
            .httpBasic(Customizer.withDefaults())
       //     .authenticationProvider(authenticationProvider)
        //    .authorizeHttpRequests(c -> c
        //            .requestMatchers("/").hasAuthority("USER") // точка hello только юзерам с привой ADMIN
         //           .anyRequest().denyAll() // все остальные без ограничений)
          //  )

          //  .addFilterAfter(showCSRFFilter, CsrfFilter.class)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(c-> c.anyRequest().authenticated())
            .build();
}

/*
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
*/
}
