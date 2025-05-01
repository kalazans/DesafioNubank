package br.com.desafio_nubank.Nubank.service;

import br.com.desafio_nubank.Nubank.dto.ClienteCadastroDTO;
import br.com.desafio_nubank.Nubank.dto.ClienteDTO;
import br.com.desafio_nubank.Nubank.dto.ContatoCadastroDTO;
import br.com.desafio_nubank.Nubank.dto.ContatoDTO;
import br.com.desafio_nubank.Nubank.model.Cliente;
import br.com.desafio_nubank.Nubank.model.Contato;
import br.com.desafio_nubank.Nubank.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepositorySpring){
        this.clienteRepository = clienteRepositorySpring;
    }

    public Cliente salvarNoBanco(ClienteCadastroDTO clienteCadastroDTO){
        Cliente cliente = new Cliente(clienteCadastroDTO);
        return this.clienteRepository.save(cliente);
    }

    public List<Cliente> listaClientesDB(){
        return this.clienteRepository.findAll();
    }

    public Optional<ClienteDTO> contatoClientePorId(Long id){
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        Optional<ClienteDTO> clienteDTO = Optional.empty();
        if(cliente.isPresent()){
             clienteDTO = Optional.of(new ClienteDTO(cliente.get().getId(),
                    cliente.get().getNome(),
                    cliente.get().getContato()
                            .stream()
                            .map(contato->new ContatoDTO(contato.getEmail(), contato.getTelefone())).collect(Collectors.toList())));
            return clienteDTO;
        }

        return clienteDTO;

    }

    public Cliente addContatoAoClientePorId(long id, List<ContatoCadastroDTO> contatoCadastroDTOS){
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if(cliente.isPresent()){
            cliente.get().setContato(contatoCadastroDTOS.stream().map(dto->new Contato(dto)).collect(Collectors.toList()));
        }
        this.clienteRepository.save(cliente.get());
        return cliente.get();
    }
}
