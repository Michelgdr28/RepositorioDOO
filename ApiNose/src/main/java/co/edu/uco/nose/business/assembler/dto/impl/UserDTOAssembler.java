package co.edu.uco.nose.business.assembler.dto.impl;

import static co.edu.uco.nose.business.assembler.dto.impl.CityDTOAssembler.getCityDTOAssembler;
import static co.edu.uco.nose.business.assembler.dto.impl.IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.UserDTO;

public final class UserDTOAssembler implements DTOAssembler<UserDTO, UserDomain> {
	
	private static final DTOAssembler<UserDTO, UserDomain> INSTANCE = new UserDTOAssembler();
	
	private UserDTOAssembler() {
		
	}
	
	public static DTOAssembler<UserDTO, UserDomain> getUserDTOAssembler() {
		return INSTANCE;
	}

	@Override
	public UserDTO toDTO(final UserDomain domain) {
		var identificationTypeDtoTmp = getIdentificationTypeDTOAssembler().toDTO(domain.getIdentificationType());
		var cityDtoTmp = getCityDTOAssembler().toDTO(domain.getCity());
		
		var domainTmp = ObjectHelper.getDefault(domain, new UserDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new UserDTO(domainTmp.getId(), domainTmp.getIdentificationNumber(), domainTmp.getFirstName(), domainTmp.getSecondName(), domainTmp.getFirstLastName(), domainTmp.getSecondLastName(),
				domainTmp.getEmail(), domainTmp.getMobilePhone(), identificationTypeDtoTmp, cityDtoTmp,domainTmp.isEmailConfirmed(),domainTmp.isMobilePhoneConfirmed());
	}

	@Override
	public UserDomain toDomain(UserDTO dto) {
		var identificationTypeDomainTmp = getIdentificationTypeDTOAssembler().toDomain(dto.getIdentificationType());
		var cityDomainTmp = getCityDTOAssembler().toDomain(dto.getCity());
		
		var dtoTmp = ObjectHelper.getDefault(dto, new UserDTO(UUIDHelper.getUUIDHelper().getDefault()));
		return new UserDomain(dtoTmp.getId(),identificationTypeDomainTmp, dtoTmp.getIdentificationNumber(), dtoTmp.getFirstName(), dtoTmp.getSecondName(), dtoTmp.getFirstLastName(), dtoTmp.getSecondLastName(),
				cityDomainTmp,dtoTmp.getEmail(), dtoTmp.getMobilePhone(),dtoTmp.isEmailConfirmed(),dtoTmp.isMobilePhoneConfirmed());
	}

	@Override
	public List<UserDTO> toDTO(List<UserDomain> domainList) {
		var userDTOList = new ArrayList<UserDTO>();
		
		for (var userDomain : domainList) {
			userDTOList.add(toDTO(userDomain));
		}
		
		return userDTOList;
	}

}
