package co.edu.uco.nose.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.CountryEntity;

public final class CountryEntityAssembler implements EntityAssembler<CountryEntity, CountryDomain> {
	
	private static final EntityAssembler<CountryEntity, CountryDomain> INSTANCE = new CountryEntityAssembler();
	
	private CountryEntityAssembler() {
		
	}
	
	public static EntityAssembler<CountryEntity, CountryDomain> getCountryEntityAssembler() {
		return INSTANCE;
	}

	@Override
	public CountryEntity toEntity(CountryDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CountryDomain(UUIDHelper.getUUIDHelper().getDefault(domain.getId())));
		
		return new CountryEntity(domainTmp.getId(), domainTmp.getName());
	}

	@Override
	public CountryDomain toDomain(CountryEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new CountryEntity(UUIDHelper.getUUIDHelper().getDefault()));
		
		return new CountryDomain(entityTmp.getId(), entityTmp.getName());
	}

	@Override
	public List<CountryEntity> toEntity(List<CountryDomain> domainList) {
		var CountryEntityList = new ArrayList<CountryEntity>();
		
		for (var countryDomain : domainList) {
			CountryEntityList.add(toEntity(countryDomain));
			
		}
		
		return CountryEntityList;
	}

}
