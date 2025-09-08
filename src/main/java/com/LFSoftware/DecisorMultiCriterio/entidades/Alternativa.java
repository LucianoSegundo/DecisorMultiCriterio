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
	
	private String referencia; // referencia que identifica qual critério está sendo julgado;
	
	private int maiorCriterio; // necessário para a normalização
	
	private int menorCriterio;  // necessário para a normalização
	
	private Double probaAgregado;  // probabilidade agregado de todos os critérios da alternativa.
	
	@ManyToOne(optional=false, cascade = CascadeType.ALL) 
    @JoinColumn(name="decisor_alter", nullable=false, updatable=false)
	private Decisor decisor;
	
	@OneToMany(mappedBy = "alter", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Criterio> criterios;
	
	public void agregarProbMelhor() {
		
	}
	
	public void agregarProbPior() {
		
	}
	
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

	public Double getProbaAgregado() {
		return probaAgregado;
	}

	public void setProbaAgregado(Double probaAgregado) {
		this.probaAgregado = probaAgregado;
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
		return Objects.hash(criterios, decisor, id_alter, maiorCriterio, menorCriterio, probaAgregado, referencia);
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
	
	
	
}
