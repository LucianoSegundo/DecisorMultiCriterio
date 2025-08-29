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
public class Decisor {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id_decisor;
	
	private String referencia;  // email do decisor, será usado para convidar o decisor;
	
	private String perfildecisor;  // perfil de decisão do decisor;
	
	private boolean concluido;  // Se esse decisor concluiu sua participação na aplicação.
	
	@ManyToOne(optional=false, cascade = CascadeType.ALL) 
    @JoinColumn(name="sala_decisor", nullable=false, updatable=false)
	private Sala sala;
	
	@OneToMany(mappedBy = "decisor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Alternativa> alternativa;
	
	public Decisor() {}
	
	public Decisor(String referencia, Sala sala, Set<Alternativa> alternativa) {
		this.alternativa = alternativa;
		this.sala = sala;
		this.referencia = referencia;
		this.concluido = false;
	}

	public UUID getId_decisor() {
		return id_decisor;
	}

	public void setId_decisor(UUID id_decisor) {
		this.id_decisor = id_decisor;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getPerfildecisor() {
		return perfildecisor;
	}

	public void setPerfildecisor(String perfildecisor) {
		this.perfildecisor = perfildecisor;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Set<Alternativa> getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Set<Alternativa> alternativa) {
		this.alternativa = alternativa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alternativa, id_decisor, perfildecisor, referencia, sala);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Decisor other = (Decisor) obj;
		return Objects.equals(alternativa, other.alternativa) && Objects.equals(id_decisor, other.id_decisor)
				&& Objects.equals(perfildecisor, other.perfildecisor) && Objects.equals(referencia, other.referencia)
				&& Objects.equals(sala, other.sala);
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	
	
	
}
