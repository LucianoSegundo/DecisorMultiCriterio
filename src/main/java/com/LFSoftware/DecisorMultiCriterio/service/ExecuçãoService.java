package com.LFSoftware.DecisorMultiCriterio.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.entidades.Alternativa;
import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;
import com.LFSoftware.DecisorMultiCriterio.entidades.Sala;

@Service
public class ExecuçãoService {

	
	@Autowired
	private ValidaçãoService vaService;
	
	@Autowired
	private AlternativaService altService;
	
	@Autowired
	private SalaService salaService;
	
	public ExecuçãoService() {}
	
	public void criarSala() {}

	public void tomarDecisao(Decisor decisor) {
		vaService.validarAlternativas(decisor);
		
		if(decisor.getSala().isNormalizar() == true) altService.normalizarCriterios(decisor);
		
		altService.definirPesoCriterios(decisor);
		
		altService.definirProb(decisor);
		
		
	}
	
	public List<Alternativa> hankearAlternativas(Sala sala){
		vaService.verificarConclusãoDecisores(sala);
		
		salaService.agregarAltProb(sala);
		
		return salaService.hankearAlternativas(sala);
	}
	
	public Decisor montarDecisor(UUID idDecisor) {
		return null;
	};
}
