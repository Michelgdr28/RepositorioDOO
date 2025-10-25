package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.CityEntityAssembler.getCityEntityAssembler;
import static co.edu.uco.nose.business.assembler.entity.impl.IdentificationTypeEntityAssembler.getIdTypeEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.UserEntity;

public final class UserEntityAssembler implements EntityAssembler<UserEntity, UserDomain> {

	private static final EntityAssembler<UserEntity, UserDomain> INSTANCE = new UserEntityAssembler();
	
	private UserEntityAssembler() {
			
	}
	
	public static EntityAssembler<UserEntity, UserDomain> getUserEntityAssembler() {
		return INSTANCE;
	}
	
	
	@Override
	public UserEntity toEntity(UserDomain domain) {
		var identificationTypeEntityTmp = getIdTypeEntityAssembler().toEntity(domain.getIdentificationType());
		var cityEntityTmp = getCityEntityAssembler().toEntity(domain.getCity());
		
		var domainTmp = ObjectHelper.getDefault(domain, new UserDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new UserEntity(domainTmp.getId(), domainTmp.getIdentificationNumber(), domainTmp.getFirstName(), domainTmp.getSecondName(), domainTmp.getFirstLastName(), domainTmp.getSecondLastName(),
				cityEntityTmp,domainTmp.getEmail(), domainTmp.getMobilePhone(), identificationTypeEntityTmp,domainTmp.isEmailConfirmed(),domainTmp.isMobilePhoneConfirmed());
	}

	@Override
	public UserDomain toDomain(UserEntity entity) {
		var identificationTypeDomainTmp = getIdTypeEntityAssembler().toDomain(entity.getIdentificationType());
		var cityDomainTmp = getCityEntityAssembler().toDomain(entity.getCity());
		
		var entityTmp = ObjectHelper.getDefault(entity, new UserEntity(UUIDHelper.getUUIDHelper().getDefault()));
		return new UserDomain(entityTmp.getId(),  
				identificationTypeDomainTmp,
				entityTmp.getIdentificationNumber(), 
				entityTmp.getFirstName(), 
				entityTmp.getSecondName(), 
				entityTmp.getFirstLastName(), 
				entityTmp.getSecondLastName(),
				cityDomainTmp,
				entityTmp.getEmail(), 
				entityTmp.getMobilePhone(),
				entityTmp.isEmailConfirmed(),
				entityTmp.isMobilePhoneConfirmed());
	}

	@Override
	public List<UserEntity> toEntity(List<UserDomain> domainList) {
		var userEntityList = new ArrayList<UserEntity>();
		
		for (var userDomain : domainList) {
			userEntityList.add(toEntity(userDomain));
		}
		return userEntityList;
	}

}