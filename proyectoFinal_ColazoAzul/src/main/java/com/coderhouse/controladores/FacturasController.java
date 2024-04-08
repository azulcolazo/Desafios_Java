package com.coderhouse.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.servicios.FacturasService;

@RestController
@RequestMapping("/facturas")
public class FacturasController {

	@Autowired
	private FacturasService facturasService;;
}
