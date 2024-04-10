package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.entidades.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer>{

}
