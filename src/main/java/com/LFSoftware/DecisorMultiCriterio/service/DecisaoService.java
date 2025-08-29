package com.LFSoftware.DecisorMultiCriterio.service;

import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.entidades.Alternativa;
import com.LFSoftware.DecisorMultiCriterio.entidades.Criterio;


@Service
public class DecisaoService {

private void normalizar(Alternativa alter) {
	
		double maior =  alter.getMaiorCriterio();
		double menor =  alter.getMenorCriterio();
		
	for (Criterio cri : alter.getCriterios()) {
		
		double resultado = (double) cri.getValor() - menor;
		
		resultado = resultado/(maior-menor);
		
		cri.setValor_normalizado(resultado);
		
	}
	
};
private void definirPeso(Alternativa alter) {

	int numCriterio = alter.getDecisor().getSala().getNumCriterios();
	
	int numValidacao = 0;
	
	Double pesoAcumulado = 0.0;
	
	int numInteracoes = numCriterio;

	for (Criterio cri : alter.getCriterios()) {
		
		Double peso = 0.0;
		
		for(int i = 0; i < numInteracoes; i++) {
			peso = peso + 1.0/(numCriterio-i);
		}
		
		numInteracoes--;
		peso *= (1.0/numCriterio);
	    pesoAcumulado += peso;
	    
		cri.setPeso(peso);
	}
	if(pesoAcumulado>1.0001 || pesoAcumulado< 0.99998 || pesoAcumulado == 0) {
		
		System.out.println("Error: lançou excessão pura quando o acumulado dos pesos saiu diferente de um, confira metodo definirPeso na classe DecisaoService.");
		throw  new RuntimeException("Pesos incompartiveis na alternativa : "+ alter.getReferencia());
		
	}
	
}

}
