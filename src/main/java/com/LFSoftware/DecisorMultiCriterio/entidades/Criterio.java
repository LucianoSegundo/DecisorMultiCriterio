package com.LFSoftware.DecisorMultiCriterio.entidades;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Criterio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id_cri;
	
	private String referencia; // referencia que identifica qual critério está sendo julgado;
	
	private Double probMelhor; // probabilidade de ser melhor/
	
	private Double probPior;  // probabilidade de ser pior;
	
	private int valor; // valor fornecido pelo decisor, segue a escala de 1 a 5;
	
	private Double valor_normalizado; // valor normalizado
	
	private Double peso; // peso de cada critério;
	
	private Double valorTriangular; // valor da triangular desse criterio;
	
	private Double valorBetaPath; // valor da beta path.
	
	@ManyToOne(optional=false, cascade = CascadeType.ALL) 
    @JoinColumn(name="decisor_alter", nullable=false, updatable=false)
	private Alternativa alter;
	
	public Criterio(){}

	public Criterio(String referencia2, int valor2, int posicao) {
this.referencia = referencia2;
this.valor = valor2;

	}

	public UUID getId_cri() {
		return id_cri;
	}

	public void setId_cri(UUID id_cri) {
		this.id_cri = id_cri;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Double getValor_normalizado() {
		return valor_normalizado;
	}

	public void setValor_normalizado(Double valor_normalizado) {
		this.valor_normalizado = valor_normalizado;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getValorTriangular() {
		return valorTriangular;
	}

	public void setValorTriangular(Double valorTriangular) {
		this.valorTriangular = valorTriangular;
	}

	public Double getValorBetaPath() {
		return valorBetaPath;
	}

	public void setValorBetaPath(Double valorBetaPath) {
		this.valorBetaPath = valorBetaPath;
	}

	public Alternativa getAlter() {
		return alter;
	}

	public void setAlter(Alternativa alter) {
		this.alter = alter;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alter, id_cri, peso, probMelhor, probPior, referencia, valor, valorBetaPath,
				valorTriangular, valor_normalizado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criterio other = (Criterio) obj;
		return Objects.equals(referencia, other.referencia);
	}
	
	
	
}
