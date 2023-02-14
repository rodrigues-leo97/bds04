package com.devsuperior.bds04.components;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {  //método para inserir mais informações ao meu token
        //irá entrar no ciclo de vida do token e na hora de gerar o token ele irá acrescentar objetos ou passar para ter um token com mais informações
        User user = userRepository.findByEmail(oAuth2Authentication.getName()); //essa informação já estará no token de autenticação portanto eu consigo pega-la

        //o tipo da chave é string e o tipo do valor é object pq pode ser qualquer tipo
        Map<String, Object> map = new HashMap<>();

        //inserindo informações ao token(informo oq inserir mas ainda não inseri)
        map.put("userFirstName", user.getUsername());
        map.put("userId", user.getId());

        //agora para inserir de fato ao token essas informações tenho que usar o objeto OAuth2AccessToken
        //mas preciso fazer um CAST para tipar ainda mais o objeto, pois está mais específico dentro de uma classe, acrescento a palavra Default na frente

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken; //tipo, pois um dos valores no Map é object
        token.setAdditionalInformation(map); //passo as informações do token, ou seja, tipo da chave String e o valor é DefaultOAuth2AccessToken

        return oAuth2AccessToken;
    }
}
