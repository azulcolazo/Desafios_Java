package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.entidades.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer>{

}
