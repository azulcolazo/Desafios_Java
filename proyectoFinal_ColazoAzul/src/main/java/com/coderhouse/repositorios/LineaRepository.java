package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.clasesSoporte.Linea;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Integer>{

}
