package co.edu.uco.nose.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.IdentificationTypeDTO;

public final class IdentificationTypeDTOAssembler implements DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> {

	private static final DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> INSTANCE = new IdentificationTypeDTOAssembler();
	
	private IdentificationTypeDTOAssembler() {
		
	}
	
	public static DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> getIdentificationTypeDTOAssembler() {
		return INSTANCE;
	}
	
	@Override
	public IdentificationTypeDTO toDTO(IdentificationTypeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new IdentificationTypeDomain(UUIDHelper.getUUIDHelper().getDefault())); 
		return new IdentificationTypeDTO(domainTmp.getId(), domainTmp.getName());
	}

	@Override
	public IdentificationTypeDomain toDomain(IdentificationTypeDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new IdentificationTypeDTO(UUIDHelper.getUUIDHelper().getDefault()));
		return new IdentificationTypeDomain(dtoTmp.getId(), dtoTmp.getName());
	}

	@Override
	public List<IdentificationTypeDTO> toDTO(List<IdentificationTypeDomain> domainList) {
		var userDTOList = new ArrayList<IdentificationTypeDTO>();
		
		for (var identificationType : domainList) {
			userDTOList.add(toDTO(identificationType));
		}
		
		return userDTOList;
	}

}
