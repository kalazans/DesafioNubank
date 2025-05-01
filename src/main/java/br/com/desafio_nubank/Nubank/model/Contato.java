package br.com.desafio_nubank.Nubank.model;

import br.com.desafio_nubank.Nubank.dto.ContatoCadastroDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String telefone;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonBackReference
    private  Cliente cliente;

    public Contato(){}
    public Contato(ContatoCadastroDTO contatoCadastroDTO){
        this.email = contatoCadastroDTO.email();
        this.telefone = contatoCadastroDTO.telefone();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(email, contato.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cliente=" + cliente.getId() +
                '}';
    }
}
