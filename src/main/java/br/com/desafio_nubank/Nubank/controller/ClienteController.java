package br.com.desafio_nubank.Nubank.controller;

import br.com.desafio_nubank.Nubank.dto.ClienteCadastroDTO;
import br.com.desafio_nubank.Nubank.dto.ClienteDTO;
import br.com.desafio_nubank.Nubank.dto.ContatoCadastroDTO;
import br.com.desafio_nubank.Nubank.model.Cliente;
import br.com.desafio_nubank.Nubank.service.ClienteService;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {
    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente/adicionar")
    public Cliente addCliente(@RequestBody @Valid ClienteCadastroDTO clienteCadastroDTO){
        return this.clienteService.salvarNoBanco(clienteCadastroDTO);
    }

    @GetMapping("/cliente/lista")
    public ResponseEntity<List<Cliente>> listaClientes(){
        return ResponseEntity.ok(this.clienteService.listaClientesDB());
    }

    @GetMapping("/cliente/{id}/contatos")
    public ResponseEntity<ClienteDTO> contatoClientePorId(@PathVariable("id") Long id) throws BadRequestException {
        Optional<ClienteDTO> clienteDTO = this.clienteService.contatoClientePorId(id);
        if(clienteDTO.isEmpty()){
           throw new BadRequestException("id do cliente nao consta no banco de dados");
        }
        return ResponseEntity.ok(clienteDTO.get());
    }

    @PutMapping("/contatos/{idcliente}")
    public ResponseEntity<Cliente> addContatoPorIdCliente(@RequestBody @Valid List<ContatoCadastroDTO> contatoCadastroDTOS,
                                                          @PathVariable("idcliente") long idCliente){
        return  ResponseEntity.accepted().body(this.clienteService.addContatoAoClientePorId(idCliente,contatoCadastroDTOS));

    }
}
