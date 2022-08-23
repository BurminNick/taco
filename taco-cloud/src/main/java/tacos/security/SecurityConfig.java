package tacos.security;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web
        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
        .configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
        .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
        .builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("userRepositoryUserDetailsService")
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http.csrf().disable().authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
                         .antMatchers("/admin/**", "/").access("hasRole('ROLE_USER')")
                .antMatchers("/").access("permitAll")
                .and()
                .formLogin()
                .loginPage("/login")
              //  .defaultSuccessUrl("/design")
                .and().logout()
                .logoutSuccessUrl("/login");
    }



    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                /*inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and()*/
                userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

   /* @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(User.builder().username("admin")
                .password("admin").roles("ADMIN").build());
    }*/

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "buzz", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        usersList.add(new User(
                "admin", encoder.encode("admin"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        return new InMemoryUserDetailsManager(usersList);
    }

}
