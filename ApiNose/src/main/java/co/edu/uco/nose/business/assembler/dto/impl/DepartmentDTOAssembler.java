package co.edu.uco.nose.business.assembler.dto.impl;

import static co.edu.uco.nose.business.assembler.dto.impl.CountryDTOAssembler.getCountryDTOAssembler;

import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.DepartmentDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.DepartmentDTO;

public final class DepartmentDTOAssembler implements DTOAssembler <DepartmentDTO, DepartmentDomain> {
	
	private static final DTOAssembler <DepartmentDTO,DepartmentDomain>INSTANCE = new DepartmentDTOAssembler();
	private DepartmentDTOAssembler() {
		
	}
	public static DTOAssembler<DepartmentDTO, DepartmentDomain> getDepartmentDTOAssembler() {
		return INSTANCE;
	}

	@Override
	public DepartmentDTO toDTO(final DepartmentDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new DepartmentDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var countryDTOTmp = getCountryDTOAssembler().toDTO(domainTmp.getCountry());
		return new DepartmentDTO(domainTmp.getId(), domainTmp.getName(),countryDTOTmp);
	}

	@Override
	public DepartmentDomain toDomain(final DepartmentDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new DepartmentDTO());
		var countryDomainTmp = CountryDTOAssembler.getCountryDTOAssembler().toDomain(dtoTmp.getCountry());
		return new DepartmentDomain(dtoTmp.getId(), countryDomainTmp, dtoTmp.getName());
	}
	@Override
	public List<DepartmentDTO> toDTO(List<DepartmentDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
