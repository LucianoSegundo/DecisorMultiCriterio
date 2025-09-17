package com.LFSoftware.DecisorMultiCriterio.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.controller.dto.AlternativaDTO;
import com.LFSoftware.DecisorMultiCriterio.controller.dto.CriterioDTO;
import com.LFSoftware.DecisorMultiCriterio.entidades.Alternativa;
import com.LFSoftware.DecisorMultiCriterio.entidades.Criterio;
import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;
import com.LFSoftware.DecisorMultiCriterio.entidades.Sala;
import com.LFSoftware.DecisorMultiCriterio.repository.DecisorRepository;
import com.LFSoftware.DecisorMultiCriterio.repository.SalaRepository;

@Service
public class ExecuçãoService {

	
	@Autowired
	private ValidaçãoService vaService;
	
	@Autowired
	private AlternativaService altService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private DecisorRepository decirepo;
	
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
				
		salaService.agregarProbabilidades(sala);
		
		salaService.calcularPerfisDecisao(sala);
		
		salaService.agregarPerfisDecisao(sala);
		
		return salaService.hankearAlternativas(sala);
	}
	
	public Decisor montarDecisor(UUID decisorid,List<AlternativaDTO> opiniao) {
		
		//Decisor decisor = decirepo.findById(decisorid).get();
		
		Decisor decisor = new Decisor();
		
		decisor.setId_decisor(decisorid);
		
		for (AlternativaDTO DTO : opiniao) {
			
			Alternativa alter = new Alternativa(DTO.referencia(), DTO.maior(), DTO.pior());
			
			for (CriterioDTO cri : DTO.criterios()) {
				
				if(cri.valor() > alter.getMaiorCriterio() || alter.getMaiorCriterio() == 0 ) alter.setMaiorCriterio(cri.valor());
				if(cri.valor() < alter.getMenorCriterio() || alter.getMenorCriterio() == 0 ) alter.setMenorCriterio(cri.valor());
				
				Criterio crite = new Criterio(cri.referencia(), cri.valor(), cri.posicao());
				
				alter.getCriterios().add(crite);
			}
			
			decisor.getAlternativa().add(alter);
		}
		
		return decisor;
	};
}
