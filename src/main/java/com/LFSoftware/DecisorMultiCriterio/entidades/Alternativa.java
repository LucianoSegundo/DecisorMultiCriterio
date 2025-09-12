package com.LFSoftware.DecisorMultiCriterio.entidades;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Alternativa {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id_alter;

	private Double probMelhor; // probabilidade agregado de todos os critérios da alternativa.
	private Double probPior; // probabilidade agregado de todos os critérios da alternativa.

	private Double OP; // Valor do perfil de avaliação Optimistic/progressive
	private Double OC; // Valor do perfil de avaliação Optimistic/conservative
	private Double PPe; // Valor do perfil de avaliação Pessimistic/progressive
	private Double PC; // Valor do perfil de avaliação Pessimistic/conservative

	// variaveis de agregação

	private Double ProMelhorAgregado; // probabilidade agregada de uma alternativa ser a melhor. Essa caracteristica é
										// armazenada na copia da alternativa vinculada a sala.
	private Double ProPiorAgregado; // probabilidade agregada de uma alternativa ser a melhor. Essa caracteristica é
									// armazenada na copia da alternativa vinculada a sala.

	private Double OPAgregado; // Valor do perfil de avaliação Optimistic/progressive
	private Double OCAgregado; // Valor do perfil de avaliação Optimistic/conservative
	private Double PPeAgregado; // Valor do perfil de avaliação Pessimistic/progressive
	private Double PCAgregado; // Valor do perfil de avaliação Pessimistic/conservative

	// utilitarios

	private String referencia; // referencia que identifica qual critério está sendo julgado;
	private int maiorCriterio; // necessário para a normalização
	private int menorCriterio; // necessário para a normalização

	// mapeamento

	@OneToMany(mappedBy = "alter", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Criterio> criterios;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "decisor_alter", nullable = false, updatable = false)
	private Decisor decisor;

	@ManyToOne(optional = true)
	@JoinColumn(name = "sala_alter", nullable = true)
	private Sala sala;

	public UUID getId_alter() {
		return id_alter;
	}

	public void setId_alter(UUID id_alter) {
		this.id_alter = id_alter;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getMaiorCriterio() {
		return maiorCriterio;
	}

	public void setMaiorCriterio(int maiorCriterio) {
		this.maiorCriterio = maiorCriterio;
	}

	public int getMenorCriterio() {
		return menorCriterio;
	}

	public void setMenorCriterio(int menorCriterio) {
		this.menorCriterio = menorCriterio;
	}

	public Decisor getDecisor() {
		return decisor;
	}

	public void setDecisor(Decisor decisor) {
		this.decisor = decisor;
	}

	public Set<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(Set<Criterio> criterios) {
		this.criterios = criterios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(criterios, decisor, id_alter, maiorCriterio, menorCriterio, probMelhor, probPior,
				referencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alternativa other = (Alternativa) obj;
		return Objects.equals(referencia, other.referencia);
	}

	public Double getProbMelhor() {
		return probMelhor;
	}

	public void setProbMelhor(Double probMelhor) {
		this.probMelhor = probMelhor;
	}

	public Double getProbPior() {
		return probPior;
	}

	public void setProbPior(Double probPior) {
		this.probPior = probPior;
	}

	public Double getProMelhorAgregado() {
		return ProMelhorAgregado;
	}

	public void setProMelhorAgregado(Double proMelhorAgregado) {
		ProMelhorAgregado = proMelhorAgregado;
	}

	public Double getProPiorAgregado() {
		return ProPiorAgregado;
	}

	public void setProPiorAgregado(Double proPiorAgregado) {
		ProPiorAgregado = proPiorAgregado;
	}

	public Double getOP() {
		return OP;
	}

	public void setOP(Double oP) {
		OP = oP;
	}

	public Double getOC() {
		return OC;
	}

	public void setOC(Double oC) {
		OC = oC;
	}

	public Double getPPe() {
		return PPe;
	}

	public void setPPe(Double pPe) {
		PPe = pPe;
	}

	public Double getPC() {
		return PC;
	}

	public void setPC(Double pC) {
		PC = pC;
	}

	public Double getOPAgregado() {
		return OPAgregado;
	}

	public void setOPAgregado(Double oPAgregado) {
		OPAgregado = oPAgregado;
	}

	public Double getOCAgregado() {
		return OCAgregado;
	}

	public void setOCAgregado(Double oCAgregado) {
		OCAgregado = oCAgregado;
	}

	public Double getPPeAgregado() {
		return PPeAgregado;
	}

	public void setPPeAgregado(Double pPeAgregado) {
		PPeAgregado = pPeAgregado;
	}

	public Double getPCAgregado() {
		return PCAgregado;
	}

	public void setPCAgregado(Double pCAgregado) {
		PCAgregado = pCAgregado;
	}

}
