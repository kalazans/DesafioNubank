package br.com.desafio_nubank.Nubank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ContatoCadastroDTO(@NotNull(message = "nao pode esta nulo")
                                 @NotBlank(message = "nao poder estar em branco")
                                 @Email String email,
                                 @NotNull(message = "nao pode esta nulo")
                                 @NotBlank(message = "nao poder estar em branco")
                                 @Pattern(regexp = "([0-9])+",message = "somente numeros, sem pontos ou traços") String telefone) {
}
