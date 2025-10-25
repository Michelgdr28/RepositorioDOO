package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.CountryEntityAssembler.getCountryEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.DepartmentDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.DepartmentEntity;

public final class DepartmentEntityAssembler implements EntityAssembler<DepartmentEntity, DepartmentDomain> {
	
	private static final EntityAssembler<DepartmentEntity, DepartmentDomain> INSTANCE = new DepartmentEntityAssembler();
	
	private DepartmentEntityAssembler() {
		
	}
	
	public static EntityAssembler<DepartmentEntity, DepartmentDomain> getDepartmentEntityAssembler() {
		return INSTANCE;
	}

	@Override
	public DepartmentEntity toEntity(DepartmentDomain domain) {
		var countryEntityTmp = getCountryEntityAssembler().toEntity(domain.getCountry());
		var domainTmp = ObjectHelper.getDefault(domain, new DepartmentDomain(UUIDHelper.getUUIDHelper().getDefault()));
		
		return new DepartmentEntity(domainTmp.getId(), domainTmp.getName(), countryEntityTmp);
	}

	@Override
	public DepartmentDomain toDomain(DepartmentEntity entity) {
		var countryDomainTmp = getCountryEntityAssembler().toDomain(entity.getCountry());
		var entityTmp = ObjectHelper.getDefault(entity, new DepartmentEntity(UUIDHelper.getUUIDHelper().getDefault()));
		
		return new DepartmentDomain(entityTmp.getId(), countryDomainTmp,entityTmp.getName());
	}

	@Override
	public List<DepartmentEntity> toEntity(List<DepartmentDomain> domainList) {
		var departmentEntityList = new ArrayList<DepartmentEntity>();
		
		for (var departmentDomain : domainList) {
			departmentEntityList.add(toEntity(departmentDomain));
			
		}
		
		return departmentEntityList;
	}

}
