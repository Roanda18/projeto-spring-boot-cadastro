package br.com.matheus.cadastro.services;

import br.com.matheus.cadastro.controllers.ClienteController;
import br.com.matheus.cadastro.mapper.ClienteMapper;
import br.com.matheus.cadastro.model.Cliente;
import br.com.matheus.cadastro.repositories.ClienteRepository;
import br.com.matheus.cadastro.vo.ClienteVO;
import exceptions.RequiredObjectIsNullExeption;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ClienteServices {

    private Logger logger = Logger.getLogger(ClienteServices.class.getName());

    @Autowired
    ClienteRepository repository;
    @Autowired
    PagedResourcesAssembler<ClienteVO> assembler;
    @Autowired
    ClienteMapper mapper;

    public PagedModel<EntityModel<ClienteVO>> buscarTodos(Pageable pageable) {

        logger.info("Buscando todos os clientes");

        var clientePagina = repository.findAll(pageable);
        var clienteVoPagina = clientePagina.map(c -> ClienteMapper.parseObject(repository.save(c), ClienteVO.class));
        clienteVoPagina.map(c -> c.add(linkTo(methodOn(ClienteController.class).buscaPorId(c.getKey())).withSelfRel()));

        Link link = linkTo(methodOn(ClienteController.class).buscarTodos(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
        return assembler.toModel(clienteVoPagina, link);
    }

    public PagedModel<EntityModel<ClienteVO>> buscaClientePorNome(String nome, Pageable pageable) {

        logger.info("Buscando cliente pelo nome");

        var clientePagina = repository.buscarClientePorNome(nome, pageable);
        var clienteVoPagina = clientePagina.map(c -> ClienteMapper.parseObject(c, ClienteVO.class));
        clienteVoPagina.map(c -> c.add(linkTo(methodOn(ClienteController.class).buscaPorId(c.getKey())).withSelfRel()));

        Link link = linkTo(methodOn(ClienteController.class).buscarTodos(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
        return assembler.toModel(clienteVoPagina, link);
    }

    public ClienteVO buscarPorId(BigDecimal id) {

        logger.info("Buscar cliente por ID");

        var entity = repository.findById(id.longValue()).orElseThrow(() -> new ResourceNotFoundException("Não foi encotrado cliente com esse ID"));
        ClienteVO vo = ClienteMapper.parseObject(entity, ClienteVO.class);
        vo.add(linkTo(methodOn(ClienteController.class).buscaPorId(id)).withSelfRel());
        return vo;

    }

    public ClienteVO criar (ClienteVO cliente){

        if (cliente == null){
            throw new RequiredObjectIsNullExeption();
        }

        logger.info("Criar cliente");

        var entity = ClienteMapper.parseObject(cliente, Cliente.class);
        var vo = ClienteMapper.parseObject(repository.save(entity), ClienteVO.class);
        vo.add(linkTo(methodOn(ClienteController.class).buscaPorId(vo.getKey())).withSelfRel());
        return vo;

    }

    public ClienteVO atualizar(ClienteVO cliente){

        if (cliente == null){
            throw new RequiredObjectIsNullExeption();
        }

        logger.info("Cliente atualizado");

        var entity = repository.findById(cliente.getKey().longValue())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encotrado cliente com esse ID") );

        entity.setNome(cliente.getNome());
        entity.setData_aniversario(cliente.getData_aniversario());
        entity.setCpf(cliente.getCpf());
        entity.setEndereco(cliente.getEndereco());

        var vo = ClienteMapper.parseObject(repository.save(entity), ClienteVO.class);
        vo.add(linkTo(methodOn(ClienteController.class).buscaPorId(vo.getKey())).withSelfRel());
        return  vo;
    }

    public void deletar (Long id){
        logger.info("Deletar cliente");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encotrado cliente com esse ID"));

        repository.delete(entity);

    }

}
