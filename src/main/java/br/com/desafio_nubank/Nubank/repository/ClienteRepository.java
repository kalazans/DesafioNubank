package br.com.desafio_nubank.Nubank.repository;

import br.com.desafio_nubank.Nubank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
