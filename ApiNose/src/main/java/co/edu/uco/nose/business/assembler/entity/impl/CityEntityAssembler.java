package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.DepartmentEntityAssembler.getDepartmentEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.CityEntity;

public final class CityEntityAssembler implements EntityAssembler<CityEntity, CityDomain> {
	
	private static final EntityAssembler<CityEntity, CityDomain> INSTANCE = new CityEntityAssembler();
	
	private CityEntityAssembler() {
		
	}
	
	public static EntityAssembler<CityEntity, CityDomain> getCityEntityAssembler() {
		return INSTANCE;
	}

	@Override
	public CityEntity toEntity(CityDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CityDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var departmentEntityTmp = getDepartmentEntityAssembler().toEntity(domain.getDepartment());
		return new CityEntity(domainTmp.getId(), domainTmp.getName(), departmentEntityTmp);
	}

	@Override
	public CityDomain toDomain(CityEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new CityEntity(UUIDHelper.getUUIDHelper().getDefault()));
		var departmentDomainTmp = getDepartmentEntityAssembler().toDomain(entity.getDepartment());
		return new CityDomain(entityTmp.getId(), entityTmp.getName(), departmentDomainTmp);
	}

	@Override
	public List<CityEntity> toEntity(List<CityDomain> domainList) {
		var cityEntityList = new ArrayList<CityEntity>();
		
		for (var cityDomain : domainList) {
			cityEntityList.add(toEntity(cityDomain));
		}
		
		return cityEntityList;
	}

}
