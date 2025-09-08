package com.LFSoftware.DecisorMultiCriterio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.entidades.Alternativa;
import com.LFSoftware.DecisorMultiCriterio.entidades.Sala;

@Service
public class SalaService {

	public SalaService() {};
	
	public void agregarAltProb(Sala sala) {
		this.agregarProbAlteMelhor(sala);
		this.agregarProbAltePior(sala);
	}
	
	public List<Alternativa> hankearAlternativas(Sala sala){
		System.out.println("classe: AlternativaService; Metodo hankearAlternativas; status: não implementado;");

		return null;
	}
	private void agregarProbAlteMelhor(Sala sala) {
		System.out.println("classe: salaService; Metodo agregarProbAlteMelhor; status: não implementado;");
	}
	
	private void agregarProbAltePior(Sala sala) {
		System.out.println("classe: salaService; Metodo agregarProbAlteMelhor; status: não implementado;");

	}
}
