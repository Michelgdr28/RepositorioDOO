package co.edu.uco.nose.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.dto.IdentificationTypeDTO;

public class IdentificationTypeDTOAssembler implements DTOAssembler <IdentificationTypeDTO, IdentificationTypeDomain> {

	private static final DTOAssembler <IdentificationTypeDTO, IdentificationTypeDomain> INSTANCE = new IdentificationTypeDTOAssembler();
	private IdentificationTypeDTOAssembler() {
		
	}
	public static DTOAssembler <IdentificationTypeDTO, IdentificationTypeDomain> getIdentificationTypeDTOAssembler() {
		return INSTANCE;
	}
	@Override
	public IdentificationTypeDTO toDTO(IdentificationTypeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdentificationTypeDomain toDomain(IdentificationTypeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<IdentificationTypeDTO> toDTO(List<IdentificationTypeDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
