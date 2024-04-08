package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.entidades.Facturas;

public interface FacturasRepository extends JpaRepository<Facturas, Integer>{

}
