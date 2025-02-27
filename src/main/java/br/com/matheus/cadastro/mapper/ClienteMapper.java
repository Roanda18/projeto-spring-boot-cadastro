package br.com.matheus.cadastro.mapper;

import br.com.matheus.cadastro.model.Cliente;
import br.com.matheus.cadastro.vo.ClienteVO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {

    private static ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(Cliente.class, ClienteVO.class)
                .addMapping(Cliente::getId, ClienteVO::setKey);
        mapper.createTypeMap(ClienteVO.class, Cliente.class)
                .addMapping(ClienteVO::getKey, Cliente::setId);
    }

    public static <O, D> D parseObject(O origin, Class<D> destination ) {

        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination ) {

        List<D> destinationObjects = new ArrayList<D>();
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}
