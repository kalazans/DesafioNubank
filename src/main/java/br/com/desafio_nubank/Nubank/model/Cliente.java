package br.com.desafio_nubank.Nubank.model;

import br.com.desafio_nubank.Nubank.dto.ClienteCadastroDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true,nullable = false)
    private String cpf;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Contato> contato;

    public Cliente(){}
    public Cliente(ClienteCadastroDTO clienteCadastroDTO){
        this.nome = clienteCadastroDTO.nome();
        this.cpf = clienteCadastroDTO.cpf();
        this.contato = clienteCadastroDTO.contatos().stream().map(dto-> new Contato(dto)).collect(Collectors.toList());
        this.contato.forEach(c->c.setCliente(this));
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Contato> getContato() {
        return contato;
    }

    public void setContato(List<Contato> contato) {
        contato.forEach(c->c.setCliente(this));
        this.contato.addAll(contato);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", contato=" + contato +
                '}';
    }
}
