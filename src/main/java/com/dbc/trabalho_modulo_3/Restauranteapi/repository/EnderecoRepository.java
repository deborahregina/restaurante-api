package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.EnderecoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoEndereco;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private static List<EnderecoEntity> listaEnderecoEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private AtomicInteger COUNTER2 = new AtomicInteger();
    public EnderecoRepository() {
       listaEnderecoEntities.add(new EnderecoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.RESIDENCIAL,"Rua Amazonas ",15 ,"Barra","417205140"));
        listaEnderecoEntities.add(new EnderecoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet() ,TipoEndereco.RESIDENCIAL,"Rua das Margaridas ",10 ,"Cabula","417205020"));
        listaEnderecoEntities.add(new EnderecoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.RESIDENCIAL,"Rua do sossego ",11 ,"Ondina","417205025"));
    }

    public List<EnderecoEntity> list() {
        return listaEnderecoEntities;
    }

    public List<EnderecoEntity> listByIdEndereco(Integer idEndereco) {
        return listaEnderecoEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .collect(Collectors.toList());
    }

    public List<EnderecoEntity> listByIdCliente(Integer idCliente) {
        return listaEnderecoEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdCliente().equals(idCliente))
                .collect(Collectors.toList());
    }

    public EnderecoEntity create(EnderecoEntity enderecoEntity) {
        enderecoEntity.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecoEntities.add(enderecoEntity);
        return enderecoEntity;
    }

    public EnderecoEntity update(Integer idEndereco, EnderecoEntity enderecoEntityAtual) throws Exception {
        EnderecoEntity enderecoEntitySearch = listaEnderecoEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereço não  foi encontrado"));
        enderecoEntitySearch.setIdCliente(enderecoEntityAtual.getIdCliente());
        enderecoEntitySearch.setTipo(enderecoEntityAtual.getTipo());
        enderecoEntitySearch.setLogradouro(enderecoEntityAtual.getLogradouro());
        enderecoEntitySearch.setNumero(enderecoEntityAtual.getNumero());
        enderecoEntitySearch.setBairro(enderecoEntityAtual.getBairro());
        enderecoEntitySearch.setCep(enderecoEntityAtual.getCep());

        return enderecoEntitySearch;
    }
    public void delete(Integer idEndereco) throws Exception {
        EnderecoEntity enderecobackup = listaEnderecoEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereço não localizado"));
        listaEnderecoEntities.remove(enderecobackup);
    }

}
