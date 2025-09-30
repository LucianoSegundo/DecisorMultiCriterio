package com.LFSoftware.DecisorMultiCriterio.service;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;
import org.apache.commons.math3.distribution.TriangularDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.entidades.Alternativa;
import com.LFSoftware.DecisorMultiCriterio.entidades.Criterio;
import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;
import com.LFSoftware.DecisorMultiCriterio.repository.DecisorRepository;

@Service
public class AlternativaService {

	@Autowired
	private DecisorRepository deciRepo;

	public AlternativaService() {
	}

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

	}; // Implementado

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
	} // Implementado

	public void definirProb(Decisor decisor) {

		// definir a triangular para cada criterio.
		
		for (Alternativa alter : decisor.getAlternativa()) {

			for (Criterio criterio : alter.getCriterios()) {

				this.definirTriangular(criterio);

			}

		}

		// fazendo uma aberração da natureza, vulgo definir probabilidade

		for (Alternativa alter : decisor.getAlternativa()) {

			for (Criterio criterio : alter.getCriterios()) {

				UnivariateFunction integrand1 = this.definirProbMelhor(criterio, decisor);
				UnivariateFunction integrand2 = this.definirProbPior(criterio, decisor);

				RombergIntegrator integrator = new RombergIntegrator();

				criterio.setProbMelhor(integrator.integrate(1000, integrand1, 0.0, 1.0));
				criterio.setProbPior(integrator.integrate(1000, integrand2, 0.0, 1.0));

			}

		}

		decisor.setConcluido(true);

		this.deciRepo.save(decisor);

		System.out.println("classe: AlternativaService; metodo: definirProb; status: persistencia não implementada.");
	} // Implementado, precisa ser testado.
	
	private void definirTriangular(Criterio cri) {

		// 1. Parâmetros da Distribuição Triangular (com 5% de margem)
		final double valor_Norma = cri.getValor_normalizado();
		final double minimo = Math.max(0.0, valor_Norma - (valor_Norma * 0.05));
		final double maximo = Math.min(1.0, valor_Norma + (valor_Norma * 0.05));

		// Cria a distribuição para a alternativa-alvo
		cri.setTriargular(new TriangularDistribution(minimo, valor_Norma, maximo));

	}

	private UnivariateFunction definirProbMelhor(Criterio criterio, Decisor decisor) {

		UnivariateFunction integrand = x -> {

			// Produto das CDFs de todas as outras alternativas

			double produtoCDFs = 1.0;

			for (Alternativa acumulo : decisor.getAlternativa()) {

				for (Criterio proba : acumulo.getCriterios()) {
					produtoCDFs *= proba.getTriargular().cumulativeProbability(x);

				}
			}

			return criterio.getTriargular().density(x) * produtoCDFs;
		};
		System.out.println("classe: AlternativaService; Metodo definirProbMelhor; status: não implementado;");

		return integrand;
	}

	private UnivariateFunction definirProbPior(Criterio criterio, Decisor decisor) {

		UnivariateFunction integrand = x -> {

			// Produto das CDFs de todas as outras alternativas

			double produtoCDFs = 1.0;

			for (Alternativa acumulo : decisor.getAlternativa()) {

				for (Criterio proba : acumulo.getCriterios()) {
					produtoCDFs *= proba.getTriargular().cumulativeProbability(x);

				}
			}

			return 1.0 - (criterio.getTriargular().density(x) * produtoCDFs);
		};

		System.out.println("classe: AlternativaService; Metodo definirProbAltePior; status: não implementado;");

		return integrand;

	}

}
