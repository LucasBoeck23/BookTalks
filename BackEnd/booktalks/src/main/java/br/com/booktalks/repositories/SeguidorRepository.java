package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.Seguidor;

public interface SeguidorRepository extends JpaRepository<Seguidor, Integer>{

}
