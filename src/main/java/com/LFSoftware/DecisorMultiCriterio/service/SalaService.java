package com.LFSoftware.DecisorMultiCriterio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LFSoftware.DecisorMultiCriterio.entidades.Alternativa;
import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;
import com.LFSoftware.DecisorMultiCriterio.entidades.Sala;

@Service
public class SalaService {

	public SalaService() {};
	
	public List<Alternativa> hankearAlternativas(Sala sala){
		System.out.println("classe: AlternativaService; Metodo hankearAlternativas; status: não implementado;");

		return null;
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
