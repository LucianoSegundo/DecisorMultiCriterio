package com.LFSoftware.DecisorMultiCriterio.controller.dto;

import java.util.List;

public record AlternativaDTO(String referencia, Double maior, Double pior, List<CriterioDTO> criterios) {

}
