package webg6cine55301.cinema.security;



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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
        "select critic_name, password, enabled from critic where critic_name=?")
        .authoritiesByUsernameQuery(
        "select username, authority" + " from authority where username=? and authority='CRITIC'");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/private").hasAuthority("CRITIC")
        .antMatchers("/**").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin()
        .and()
        .formLogin().loginPage("/")
        .permitAll()
        .failureUrl("/?errors")
        .and()
        .exceptionHandling().accessDeniedPage("/")
        .and()
        .logout().logoutSuccessUrl("/");

    }
}
