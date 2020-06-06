package sda.spring.rest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/index/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/adduser/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/users/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login-process")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/index")
                .and()
                .logout().logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .and()
                .withUser("superuser")
                .password(passwordEncoder.encode("password"))
                .roles("USER", "ADMIN");
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.name, u.password, 1 from user u where u.name=?")
                .authoritiesByUsernameQuery("SELECT u.name, u.role, 1, from user u where u.name=?")
                .dataSource(jdbcTemplate.getDataSource())
                .passwordEncoder(passwordEncoder);
    }
}
