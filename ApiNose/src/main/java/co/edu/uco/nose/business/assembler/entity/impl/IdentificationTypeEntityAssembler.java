package co.edu.uco.nose.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public final class IdentificationTypeEntityAssembler implements EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> {
	
	private static final EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> INSTANCE = new IdentificationTypeEntityAssembler();

	
	private IdentificationTypeEntityAssembler() {
		super();
		
	}
	
	public static EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> getIdTypeEntityAssembler() {
		return INSTANCE;
	}

	@Override
	public IdentificationTypeEntity toEntity(IdentificationTypeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new IdentificationTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new IdentificationTypeEntity(domainTmp.getId(), domainTmp.getName());
	}

	@Override
	public IdentificationTypeDomain toDomain(IdentificationTypeEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new IdentificationTypeEntity(UUIDHelper.getUUIDHelper().getDefault()));
		return new IdentificationTypeDomain(entityTmp.getId(), entityTmp.getName());
	}

	@Override
	public List<IdentificationTypeEntity> toEntity(List<IdentificationTypeDomain> domainList) {
		var identificationTypeEntityList = new ArrayList<IdentificationTypeEntity>();
		
		for (var identificationTypeDomain : domainList) {
			identificationTypeEntityList.add(toEntity(identificationTypeDomain));
		}
		return identificationTypeEntityList;
	}

}
