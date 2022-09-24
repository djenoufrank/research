package web.g55301.scrum.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/**").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin()
        .and()
        .formLogin().loginPage("/login")
        .and()
        .exceptionHandling().accessDeniedPage("/")// renvoi vers la route /
        .and()
        .logout().logoutSuccessUrl("/");
    }

     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
        "select username, password, enabled from user where username=?")
        .authoritiesByUsernameQuery(
        "select username, authority" + " from authority where username=?");

    PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
    auth.jdbcAuthentication()
    .dataSource(dataSource).withDefaultSchema()
    .passwordEncoder(pwdEncoder)
    .withUser(
    User.withUsername("user")
    .password(pwdEncoder.encode("passwd"))
     .authorities("USER"));
    } 

}