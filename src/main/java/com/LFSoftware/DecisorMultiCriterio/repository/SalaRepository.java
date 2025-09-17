package com.LFSoftware.DecisorMultiCriterio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LFSoftware.DecisorMultiCriterio.entidades.Sala;

public interface SalaRepository extends JpaRepository<Sala, UUID> {

}
