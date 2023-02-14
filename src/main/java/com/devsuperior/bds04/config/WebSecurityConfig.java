package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; //injetando apenas ele pq ele já está na classe AppConfig

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/**"); //configuração copia e cola para ignorar todos os endpoints para que não tenham a autenticação de primeiro momento
        web.ignoring().antMatchers("/actuator/**"); //liberando todo mundo mas agora passando pela lib que o spring oAuth usa para passar nas requisições(actuator)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //aqui o spring security saberá na hora de autenticar saberá como buscar por email e como analisar a senha criptografada
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder); //configuramos o userDetail e password através do método userDetailsService e passwordEncoder
        super.configure(auth);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception { //para que também seja um componente disponível no sistema, e para isso usamos o BEAN, pois iremos precisar dele na aplicação depois
        return super.authenticationManager();
    }
}
