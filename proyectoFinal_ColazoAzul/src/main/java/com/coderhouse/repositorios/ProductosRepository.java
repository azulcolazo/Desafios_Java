package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.entidades.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer>{

}
