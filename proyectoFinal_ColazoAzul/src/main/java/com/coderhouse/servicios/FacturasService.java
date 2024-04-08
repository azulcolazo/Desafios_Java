package com.coderhouse.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.repositorios.FacturasRepository;

@Service
public class FacturasService {

	@Autowired
	private FacturasRepository facturasRepository;;
}
