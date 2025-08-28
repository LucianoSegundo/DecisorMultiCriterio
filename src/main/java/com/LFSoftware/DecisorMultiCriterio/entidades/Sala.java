package com.LFSoftware.DecisorMultiCriterio.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;



public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id_sala;
	
	private int numAlternativas;  // Número de alternativas por critério.
	
	private int NumCriterios;  // Número de criterios por alternativa;
	
	private boolean concluido;  // Se a tomada de decisão já foi concluida;
	
	private List<String> RankingFinal; // Ranking final das alternativas.
	
	@ManyToOne(optional=false) 
    @JoinColumn(name="analis_sala", nullable=false, updatable=false)
	private Analista analista;
	
	@OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Decisor> decisores;
	
	public Sala() {}
	
	public Sala(Analista analista) {
		
		this.concluido = false;
		
		this.analista = analista;
		
		this.decisores = new ArrayList<Decisor>();
		
		this.RankingFinal = new ArrayList<String>();
		
	}

	public UUID getId_sala() {
		return id_sala;
	}

	public void setId_sala(UUID id_sala) {
		this.id_sala = id_sala;
	}

	public int getNumAlternativas() {
		return numAlternativas;
	}

	public void setNumAlternativas(int numAlternativas) {
		this.numAlternativas = numAlternativas;
	}

	public int getNumCriterios() {
		return NumCriterios;
	}

	public void setNumCriterios(int numCriterios) {
		NumCriterios = numCriterios;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public List<String> getRankingFinal() {
		return RankingFinal;
	}

	public void setRankingFinal(List<String> rankingFinal) {
		RankingFinal = rankingFinal;
	}

	public Analista getAnalista() {
		return analista;
	}

	public void setAnalista(Analista analista) {
		this.analista = analista;
	}

	public List<Decisor> getDecisores() {
		return decisores;
	}

	public void setDecisores(List<Decisor> decisores) {
		this.decisores = decisores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(NumCriterios, RankingFinal, analista, concluido, decisores, id_sala, numAlternativas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return NumCriterios == other.NumCriterios && Objects.equals(RankingFinal, other.RankingFinal)
				&& Objects.equals(analista, other.analista) && concluido == other.concluido
				&& Objects.equals(decisores, other.decisores) && Objects.equals(id_sala, other.id_sala)
				&& numAlternativas == other.numAlternativas;
	}
	
	
	
}
