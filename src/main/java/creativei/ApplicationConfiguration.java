package creativei;


import creativei.dao.UserDao;
import creativei.entity.User;
import creativei.enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class ApplicationConfiguration{

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);
    public static void main(String[] args) {

        SpringApplication.run(ApplicationConfiguration.class, args);
    }
    /*@Bean
    CommandLineRunner init(final UserDao userDao) {

        return new CommandLineRunner() {

            @Override
            public void run(String... arg0) throws Exception {

                userDao.save(new User("admin", "password", UserRole.ADMIN, "Admin"));
                userDao.save(new User("kitchen", "password", UserRole.KITCHEN, "Kitchen"));
                userDao.save(new User("service", "password", UserRole.SERVICE, "Waiter 1"));
            }

        };

    }*/
}
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserDao userDao;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User account = userDao.findByUserId(username);
                if(account != null) {
                    return new org.springframework.security.core.userdetails.User(account.getUserId(), account.getPassword(), true, true, true, true,
                            AuthorityUtils.createAuthorityList(account.getUserRole().toString()));
                } else {
                    throw new UsernameNotFoundException("could not find the user '"
                            + username + "'");
                }
            }

        };
    }
}

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/index.html", "/login.html", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().exceptionHandling().accessDeniedPage("/");;
        /*http.authorizeRequests().antMatchers("*//**").authenticated().anyRequest().permitAll().and().
                formLogin().and().
                httpBasic().and().
                csrf().disable();*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/commons/**","/modules/**", "/application.js");
    }
}