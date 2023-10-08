package com.rizom.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();


        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/potentiaagendicon7272","/yazarlar/**",
                        "/role",
                        "/duyurular/**","/contact",
                        "/categories/**",
                        "/addAnnouncement",
                        "/ceviri",
                        "/admin/messages",

                        "/search/**",
                        "/addAnnouncementForm",
                        "/anasayfa", "/resources/**",
                        "/css/**", "/fonts/**",
                        "/news/**","/download/**",
                        "/img/**").permitAll()
                .antMatchers("/potentiaagendicon7273").permitAll()
                .antMatchers("/users/addNew").permitAll()
//                .antMatchers("/admin-dashboard").hasRole("Admin") // Yetkilendirme düzgün ayarlanmalıdır
                .and()
                .formLogin()
                .loginPage("/potentiaagendicon7272").permitAll()
                .successHandler(successHandler()) // Özelleştirilmiş başarı işleyiciyi kullanın

                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/potentiaagendicon7272").permitAll();
    }


    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl("/anasayfa"); // Giriş başarılı olduktan sonra yönlendirilecek sayfa
        return handler;
    }


}