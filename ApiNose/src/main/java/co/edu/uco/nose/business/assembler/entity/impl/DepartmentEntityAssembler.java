package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.DepartmentDomain;
import co.edu.uco.nose.entity.DepartmentEntity;

public final class DepartmentEntityAssembler implements EntityAssembler <DepartmentEntity, DepartmentDomain> {
	public static final DepartmentEntityAssembler INSTANCE = new DepartmentEntityAssembler();
	private DepartmentEntityAssembler() {
	
	}
  	public static EntityAssembler <DepartmentEntity, DepartmentDomain>getDepartmentEntityAssembler() {
		return INSTANCE;
	}

	@Override
	public DepartmentEntity toEntity(DepartmentDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDomain toDomain(DepartmentEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
