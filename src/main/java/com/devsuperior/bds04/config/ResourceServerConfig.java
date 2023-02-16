package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer //processa para que a classe implemente a funcionalidade do ResourceServe
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment env; //a partir dele dá pra acessar diversas variáveis

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String[] PUBLIC_AUTH = {"/oauth/token", "/h2-console/**"};

    private static final String[] PUBLIC = {"/cities/**", "/events/**"};

    private static final String[] ADMIN = {"/events/**"};

    // private static final String[] ADMIN = {"/events/**", "/cities/**"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception { //irá decodificar o token e ver se está tudo certo ou não, valida o token
       //config do TOKEN STRORE
        resources.tokenStore(tokenStore); //irá receber o bean tokenStore
    }

    @Override
    public void configure(HttpSecurity http) throws Exception { //configuração das rotas e definir as configurações de acesso

        //para testar os profiles de execução que estou rodando (H2)
        //vou converter isso para uma lista
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) { //se nos PROFILES ativos eu tenho um perfil de test
            http.headers().frameOptions().disable(); //interface do h2 requer que desabilite essas questões de proteger os frames para que ela pegue
        }

        http.authorizeRequests()
                .antMatchers(PUBLIC_AUTH).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC).permitAll() //verificar se está certo???
                .antMatchers(HttpMethod.POST, ADMIN).hasAnyRole("ADMIN", "OPERATOR")
                .anyRequest().hasAnyRole("ADMIN");

    }
}
