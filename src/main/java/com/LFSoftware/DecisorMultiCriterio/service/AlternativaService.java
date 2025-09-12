package com.LFSoftware.DecisorMultiCriterio.service;

import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.entidades.Alternativa;
import com.LFSoftware.DecisorMultiCriterio.entidades.Criterio;
import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;
import com.LFSoftware.DecisorMultiCriterio.entidades.Sala;

@Service
public class AlternativaService {

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
		for (Alternativa alter : decisor.getAlternativa()) {

			this.definirProbMelhor(alter);
			this.definirProbPior(alter);
		}

		decisor.setConcluido(true);
		System.out.println("classe: AlternativaService; metodo: definirProb; status: persistencia não implementada.");
	} // Implementado entre aspas.

	private void definirProbMelhor(Alternativa alter) {
		System.out.println("classe: AlternativaService; Metodo definirProbAlteMelhor; status: não implementado;");
	}

	private void definirProbPior(Alternativa alter) {
		System.out.println("classe: AlternativaService; Metodo definirProbAltePior; status: não implementado;");

	}

	public void agregarProbabilidades(Sala sala) {

		int numeroDecisores = sala.getDecisores().size();
		for (int i = 0; i < sala.getNumAlternativas(); i++) {
			Double melhor = 0.0;
			Double pior = 0.0;
			for (Decisor decisor : sala.getDecisores()) {

				Alternativa alter = decisor.getAlternativa().get(i);

				melhor += alter.getProbMelhor();
				pior += alter.getProbPior();
			}
			Alternativa alter = sala.getAlternativas().get(i);
			alter.setProMelhorAgregado(melhor * numeroDecisores);
			alter.setProPiorAgregado(pior * numeroDecisores);
		}

		System.out.println("classe: AlternativaService; metodo: agregarProbabilidades; status: Implementado.");
	} // Implementado

	public void calcularPerfisDecisao(Sala sala) {

		for (int i = 0; i < sala.getNumAlternativas(); i++) {
			Double OP = 1.0;
			Double OC = 1.0;
			Double PPe = 1.0;
			Double PC = 1.0;

			for (Decisor decisor : sala.getDecisores()) {

				Alternativa alter = decisor.getAlternativa().get(i);

				// OP
				OP = OP * (1 - alter.getProbMelhor());
				// OC
				OC = OC * alter.getProbPior();
				// PPe
				PPe = PPe * alter.getProbMelhor();
				// PC
				PC = PC * (1 - alter.getProbPior());
			}

			Alternativa alter = sala.getAlternativas().get(i);
			alter.setOP(1 - OP);
			alter.setOC(1 - OC);
			alter.setPPe(PPe);
			alter.setPC(PC);

		}

		System.out.println("classe: AlternativaService; metodo: calcularPerfisDecisao; status: Implementado.");

	} // Implementado

	public void agregarPerfisDecisao(Sala sala) {

		for (Alternativa alter : sala.getAlternativas()) {

			// OP agregado
			double opAgregado = alter.getOP() * sala.getDecisores().size();
			alter.setOPAgregado(opAgregado);
			// OC agregado
			double ocAgregado = alter.getOC() * sala.getDecisores().size();
			alter.setOCAgregado(ocAgregado);
			// PPe agregado
			double ppeAgregado = alter.getPPe() * sala.getDecisores().size();
			alter.setPPeAgregado(ppeAgregado);
			// PC agregado
			double pcAgregado = alter.getPC() * sala.getDecisores().size();
			alter.setPCAgregado(pcAgregado);

		}
		System.out.println("classe: AlternativaService; metodo: agregarPerfisDecisao; status: Implementado.");

	} // Imolementado
}
