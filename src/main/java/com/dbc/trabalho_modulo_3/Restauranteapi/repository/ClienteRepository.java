package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ClienteRepository {

    private static List<ClienteEntity> listaClientes = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public ClienteRepository() {
        listaClientes.add(new ClienteEntity(COUNTER.incrementAndGet(),"01829548737","Camile Lopes","email@gmail.com"));
        listaClientes.add(new ClienteEntity(COUNTER.incrementAndGet(),"09839834758","Adriele Beatriz","email@gmail.com"));
        listaClientes.add(new ClienteEntity(COUNTER.incrementAndGet(),"45638736490","Deborah Silveira","deborah.regina@dbccompany.com.br"));
    }

    public ClienteEntity create(ClienteEntity clienteEntity) throws RegraDeNegocioException {
        clienteEntity.setIdCliente(COUNTER.incrementAndGet());
        listaClientes.add(clienteEntity);
        return clienteEntity;
    }

    public List<ClienteEntity> list() {
        return listaClientes;
    }


    public ClienteEntity update(Integer id,
                                ClienteEntity clienteEntityAtualizar) throws RegraDeNegocioException {
        ClienteEntity clienteEntityRecuperado = listaClientes.stream()
                .filter(clienteEntity -> clienteEntity.getIdCliente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não econtrado"));
        clienteEntityRecuperado.setCpf(clienteEntityAtualizar.getCpf());
        clienteEntityRecuperado.setNome(clienteEntityAtualizar.getNome());
        clienteEntityRecuperado.setEmail(clienteEntityAtualizar.getEmail());
        return clienteEntityRecuperado;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        ClienteEntity clienteEntityRecuperado = listaClientes.stream()
                .filter(clienteEntity -> clienteEntity.getIdCliente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não econtrado"));
        listaClientes.remove(clienteEntityRecuperado);
    }

    public ClienteEntity getById(Integer id) throws RegraDeNegocioException {
        ClienteEntity clienteEntityRecuperado = listaClientes.stream()
                .filter(clienteEntity -> clienteEntity.getIdCliente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não econtrado"));
        return clienteEntityRecuperado;
    }
}
