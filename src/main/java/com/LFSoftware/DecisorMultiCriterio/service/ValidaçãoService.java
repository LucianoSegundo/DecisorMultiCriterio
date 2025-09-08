package com.LFSoftware.DecisorMultiCriterio.service;

import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;
import com.LFSoftware.DecisorMultiCriterio.entidades.Sala;

@Service
public class ValidaçãoService {

	public ValidaçãoService() {
	}

	// valida se as alternativas esão conformes 
	public void validarAlternativas(Decisor decisor) {
		
		if(verificarConclusãoSala(decisor.getSala()) == true) throw new RuntimeException("Sala concluida, não é possivel decidir novamente");
		
		validarDecisaoDada(decisor);
		
		System.out.println("classe: ValidacaoService; Metodo ValidacarAlternativa; status: não implementado;");
	}

	// valida se o decisor já forneceu a sua decisao / implementado porem...
	private void validarDecisaoDada(Decisor decisor) {
		System.out.println("classe: ValidacaoService; Metodo validarDecisaoDada; status: implementado, mas com excessão genêrica.");
		
		if(decisor.isConcluido() == true)  throw new RuntimeException("Decisor " + decisor.getReferencia()+ " já realizou a sua decisão, não pode decidir novamente.");
	}

	// valida se a tomada de decião daquela sala já foi concluida; / implementado porem...
	public boolean verificarConclusãoSala(Sala sala) {
		System.out.println("classe: ValidacaoService; Metodo verificarConclusãoSala; status: implementado");

		return sala.isConcluido();
	}

	// valida se todos os tomadores de decição daquela sala já concluiram sua parte do serviço; / implementado porem...
	public void verificarConclusãoDecisores(Sala sala) {
		System.out.println("classe: ValidacaoService; Metodo verificarConclusãoDecisores; status: implementado, mas com excessão genêrica.");
		
		String decisores = "";
		for(Decisor decisor: sala.getDecisores()) {
			
			if(decisor.isConcluido() == false) decisores += " " + decisor.getReferencia();
		}
		if(decisores != "") throw new RuntimeException("Decisores"+ decisores+ " ainda não concluiram a tomada de decião.");
		
	}

	// valida se todos os tomadores de decição daquela sala já concluiram sua parte
	// do serviço e notifica o analista da concluão dessa parte do processo;
	public void verificarConclusãoDecisoresENotificarAnalista(Sala sala) {
		verificarConclusãoDecisores(sala);
		System.out.println(
				"classe: ValidacaoService; Metodo verificarConclusãoDecisoresENotificarAnalista; status: não implementado;");

	}
}
