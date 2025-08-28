package com.LFSoftware.DecisorMultiCriterio.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Analista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id_analis;
	
	
	
	@OneToMany(mappedBy = "analista", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sala> salas;
	
	public Analista() {
		this.salas = new ArrayList<Sala>();
	}

	public UUID getId_analis() {
		return id_analis;
	}

	public void setId_analis(UUID id_analis) {
		this.id_analis = id_analis;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_analis, salas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Analista other = (Analista) obj;
		return Objects.equals(id_analis, other.id_analis) && Objects.equals(salas, other.salas);
	}
	
	
	
}
