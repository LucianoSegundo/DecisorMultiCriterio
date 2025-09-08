package com.LFSoftware.DecisorMultiCriterio.service;

import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.entidades.Alternativa;
import com.LFSoftware.DecisorMultiCriterio.entidades.Criterio;
import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;

@Service
public class AlternativaService {

	public void normalizarCriterios(Decisor decisor) {

		for (Alternativa alter : decisor.getAlternativa()) {

			double maior = alter.getMaiorCriterio();
			double menor = alter.getMenorCriterio();

			for (Criterio cri : alter.getCriterios()) {

				double resultado = (double) cri.getValor() - menor;

				resultado = resultado / (maior - menor);

				cri.setValor_normalizado(resultado);

			}
		}

	};

	public void definirPesoCriterios(Decisor decisor) {

		for (Alternativa alter : decisor.getAlternativa()) {

			int numCriterio = alter.getDecisor().getSala().getNumCriterios();

			Double pesoAcumulado = 0.0;

			int numInteracoes = numCriterio;

			for (Criterio cri : alter.getCriterios()) {

				Double peso = 0.0;

				for (int i = 0; i < numInteracoes; i++) {
					peso = peso + 1.0 / (numCriterio - i);
				}

				numInteracoes--;
				peso *= (1.0 / numCriterio);
				pesoAcumulado += peso;

				cri.setPeso(peso);
			}
			if (pesoAcumulado > 1.0001 || pesoAcumulado < 0.99998 || pesoAcumulado == 0) {

				System.out.println(
						"Error: lançou excessão pura quando o acumulado dos pesos saiu diferente de um, confira metodo definirPeso na classe DecisaoService.");
				throw new RuntimeException("Pesos incompartiveis na alternativa : " + alter.getReferencia());

			}
		}
	}

	public void definirProb(Decisor decisor) {
		for (Alternativa alter : decisor.getAlternativa()) {

			this.definirProbMelhor(alter);
			this.definirProbPior(alter);
		}
		
		decisor.setConcluido(true);
		System.out.println("classe: AlternativaService; metodo: definirProb; status: persistencia não implementada.");
	}

	private void definirProbMelhor(Alternativa alter) {
		System.out.println("classe: AlternativaService; Metodo definirProbAlteMelhor; status: não implementado;");
	}

	private void definirProbPior(Alternativa alter) {
		System.out.println("classe: AlternativaService; Metodo definirProbAltePior; status: não implementado;");

	}

}
