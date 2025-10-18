package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public class IdentificationTypeEntityAssembler implements EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> {

	public static final IdentificationTypeEntityAssembler INSTANCE = new IdentificationTypeEntityAssembler();
	private IdentificationTypeEntityAssembler() {

	}

  	public static EntityAssembler <IdentificationTypeEntity,IdentificationTypeDomain>getIdentificationTypeEntityAssembler() {
		return INSTANCE;
	}
	@Override
	public IdentificationTypeEntity toEntity(IdentificationTypeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdentificationTypeDomain toDomain(IdentificationTypeEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
