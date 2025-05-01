package br.com.desafio_nubank.Nubank.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ClienteCadastroDTO(@NotNull(message = "nao pode ser nulo")
                                 @NotBlank(message = "Nao poder esta em branco")
                                 @Pattern(regexp = "([a-zA_z]\\s)+",message = "somente letras e espaço!") String nome,
                                 @NotNull (message = "nao pode ser nulo")
                                 @NotBlank (message = "Nao poder esta em branco")
                                 @Pattern(regexp = "(\\d{5})",message = "tamanho de 5 numero e só digitos") String cpf,
                                 @NotNull(message = "nao pode estar nulo")  @Valid List<ContatoCadastroDTO> contatos) {
}
