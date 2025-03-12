package br.com.matheus.cadastro.services;

import br.com.matheus.cadastro.vo.EnderecoVO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoServices {

    private static final String URL_VIA_CEP = "https://viacep.com.br/ws/%s/json/";

    public EnderecoVO consultarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL_VIA_CEP, cep);
        return restTemplate.getForObject(url, EnderecoVO.class);
    }
}
