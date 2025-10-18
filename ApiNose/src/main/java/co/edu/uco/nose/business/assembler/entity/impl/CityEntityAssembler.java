package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.entity.CityEntity;

public final class CityEntityAssembler implements EntityAssembler<CityEntity, CityDomain> {

	public static final CityEntityAssembler INSTANCE = new CityEntityAssembler();
	private CityEntityAssembler() {
	
	}
	public static EntityAssembler <CityEntity,CityDomain>getCityEntityAssembler() {
		return INSTANCE;
	}

	@Override
	public CityEntity toEntity(CityDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityDomain toDomain(CityEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
