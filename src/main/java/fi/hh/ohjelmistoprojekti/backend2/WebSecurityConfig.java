package fi.hh.ohjelmistoprojekti.backend2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.hh.ohjelmistoprojekti.backend2.web.UserDetailServiceImpl;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**", "/kysymykset/{nimi}", "/kysely", "/kysely/{id}", "/addKysymys", "/api/vastauses", "/kysymykset/{nimi}/vastaus", "addkysely").permitAll() // Enable css when logged out
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
      .formLogin()
          .defaultSuccessUrl("/index", true)
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
