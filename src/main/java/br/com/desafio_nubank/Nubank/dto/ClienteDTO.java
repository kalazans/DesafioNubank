package br.com.desafio_nubank.Nubank.dto;

import java.util.List;

public record ClienteDTO(long id,  String nome, List<ContatoDTO> contato) {
}
