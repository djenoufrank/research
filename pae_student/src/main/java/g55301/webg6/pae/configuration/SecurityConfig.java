package g55301.webg6.pae.configuration;

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
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //             .withUser("prof")
    //             .password("{noop}prof") // noop = non chiffré
    //             .authorities("PROF") //
    //             .and()
    //             .withUser("etudiant")
    //             .password("{noop}etudiant")
    //             .authorities("USER")
    //             .and()
    //             .withUser("secretaire")
    //             .password("{noop}sec")
    //             .authorities("SECR");
    // }

    /*
     * @Override
     * protected void configure(HttpSecurity http) throws Exception {
     * http.authorizeRequests()
     * .antMatchers("/private").authenticated() // Nécessite d’être identifié
     * .antMatchers("/**").permitAll() // Toutes les autres sont publiques
     * .and()
     * .formLogin(); // Identification via la page de login par défaut
     * }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/private/**").authenticated() // hasAuthority("PROF") // Nécessite d’être identifié
                .antMatchers("welcome").permitAll() // Toutes les autres sont publiques
                .antMatchers("/h2-console/**").permitAll()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin()
                .and()
                .formLogin().loginPage("/login") // Identification via la page de login par défaut
                .and().exceptionHandling().accessDeniedPage("/")
                .and().logout().logoutSuccessUrl("/welcome");
    }



    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // auth.jdbcAuthentication()
    // .dataSource(dataSource)
    // .withDefaultSchema()
    // .withUser(
    //  User.withUsername("user")
    //  .password("{noop}user")
    //  .authorities("USER")
    //  );
    //  }
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
     .roles("USER"));
    } 

}