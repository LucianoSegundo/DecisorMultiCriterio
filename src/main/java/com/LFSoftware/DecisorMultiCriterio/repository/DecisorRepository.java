package com.LFSoftware.DecisorMultiCriterio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LFSoftware.DecisorMultiCriterio.entidades.Decisor;

public interface DecisorRepository extends JpaRepository<Decisor, UUID> {

}
