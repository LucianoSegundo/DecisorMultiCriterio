package com.LFSoftware.DecisorMultiCriterio.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LFSoftware.DecisorMultiCriterio.controller.dto.AlternativaDTO;
import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;
import com.LFSoftware.DecisorMultiCriterio.service.ExecuçãoService;

@RequestMapping("executar/")
@RestController
public class execucaoController {

	@Autowired
	private ExecuçãoService execucaoService;

	public execucaoController(){
		
	}
	
	@PostMapping("/tomarDecisao")
	public ResponseEntity<Void> responder( List<AlternativaDTO> opiniao){
		
		Decisor desicor = execucaoService.montarDecisor(UUID.randomUUID() , opiniao);
		
		execucaoService.tomarDecisao(desicor);
		
		return ResponseEntity.ok().build();
	}
	
}
