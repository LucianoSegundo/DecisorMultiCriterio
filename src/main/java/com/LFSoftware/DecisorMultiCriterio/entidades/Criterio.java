package com.LFSoftware.DecisorMultiCriterio.entidades;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Criterio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id_cri;
	
	private String referencia; // referencia que identifica qual critério está sendo julgado;
	
	private Long probMelhor; // probabilidade de ser melhor/
	
	private Long probPior;  // necessário para a normalização;
	
	private int valor; // valor fornecido pelo decisor, segue a escala de 1 a 5;
	
	private int valor_normalizado; // valor normalizado
	
	private Long peso; // peso de cada critério;
	
	private Long valorTriangular; // valor da triangular desse criterio;
	
	private Long valorBetaPath; // valor da beta path.
	
	@ManyToOne(optional=false, cascade = CascadeType.ALL) 
    @JoinColumn(name="decisor_alter", nullable=false, updatable=false)
	private Alternativa alter;
	
	public Criterio(){}

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

	public Long getProbMelhor() {
		return probMelhor;
	}

	public void setProbMelhor(Long probMelhor) {
		this.probMelhor = probMelhor;
	}

	public Long getProbPior() {
		return probPior;
	}

	public void setProbPior(Long probPior) {
		this.probPior = probPior;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getValor_normalizado() {
		return valor_normalizado;
	}

	public void setValor_normalizado(int valor_normalizado) {
		this.valor_normalizado = valor_normalizado;
	}

	public Long getPeso() {
		return peso;
	}

	public void setPeso(Long peso) {
		this.peso = peso;
	}

	public Long getValorTriangular() {
		return valorTriangular;
	}

	public void setValorTriangular(Long valorTriangular) {
		this.valorTriangular = valorTriangular;
	}

	public Long getValorBetaPath() {
		return valorBetaPath;
	}

	public void setValorBetaPath(Long valorBetaPath) {
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
