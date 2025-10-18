package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.entity.UserEntity;

public class UserEntityAssembler implements EntityAssembler<UserEntity, UserDomain> {

	public static final UserEntityAssembler INSTANCE = new UserEntityAssembler();
	private UserEntityAssembler() {

	}

  	public static EntityAssembler <UserEntity,UserDomain>getUserEntityAssembler() {
		return INSTANCE;
	}
	@Override
	public UserEntity toEntity(UserDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDomain toDomain(UserEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}



}
