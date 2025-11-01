package co.edu.uco.nose.business.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.business.validator.identificationtype.ValidateIdentificationTypeExistsById;
import co.edu.uco.nose.business.business.validator.user.ValidateDataUserConsistencyForRegisterNewInformation;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class UserBusinessImpl implements UserBusiness {
	
	private DAOFactory daoFactory;
	
	public UserBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registerNewUserInformation(final UserDomain userDomain) {
		// 1. Validar que la informacion sea consistente a nivel de tipo de dato, longitud, obligatoriedad, formato, rango, reglas propias del objeto
		
		ValidateDataUserConsistencyForRegisterNewInformation.executeValidation(userDomain);
		
		// 2. Validar que exista tipo de identificacion
		ValidateIdentificationTypeExistsById.executeValidation(userDomain.getIdentificationType().getId(), daoFactory);
		
		// 3. Validar que exista la ciudad de residencia
		
		// 4. Validar que no exista previamente otro usuario con el mismo tipo y numero de identificacion
		
		// 5. Validar que no exista previamente otro usuario con el mismo correo electronico
		
		// 6. Validar que no exista previamente otro usuario con el mismo numero de telefono celular
		
		// 7. Ensamblar objeto como entity
		var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
		
		// 8. Generar ID
		userEntity.setId(generateId());
		
		// 9. Registrar la informacion del nuevo usuario
		daoFactory.getUserDAO().create(userEntity);
		
	}
	
	private UUID generateId() {
		var id = UUIDHelper.getUUIDHelper().generateNewUUID();
		var userEntity = daoFactory.getUserDAO().findById(id);
		while (!UUIDHelper.getUUIDHelper().isDefaultUUID(userEntity.getId())) {
			id = UUIDHelper.getUUIDHelper().generateNewUUID();
			userEntity = daoFactory.getUserDAO().findById(id);
		};
		return id;
	}

	@Override
	public void dropUserInformation(final UUID id) {
		daoFactory.getUserDAO().delete(id);
		
	}

	@Override
	public void updateUserInformation(final UUID id, final UserDomain userDomain) {
		var user = daoFactory.getUserDAO().findById(id);
		daoFactory.getUserDAO().update(user);
	}

	@Override
	public List<UserDomain> findAllUsers() {
		var userEntityList = daoFactory.getUserDAO().findAll();
		var userDomainList = new ArrayList<UserDomain>();
		for (var userEntity : userEntityList) {
			userDomainList.add(UserEntityAssembler.getUserEntityAssembler().toDomain(userEntity));
		}
		return userDomainList;
	}

	@Override
	public List<UserDomain> findUsersByFilter(final UserDomain userFilters) {
		var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userFilters);
		var userEntityList = daoFactory.getUserDAO().findByfilter(userEntity);
		var userDomainList = new ArrayList<UserDomain>();
		
		for (var user : userEntityList) {
			userDomainList.add(UserEntityAssembler.getUserEntityAssembler().toDomain(user));
		}
		
		return userDomainList;
	}

	@Override
	public UserDomain findSpecificUser(final UUID id) {
		var userEntity = daoFactory.getUserDAO().findById(id);
		return UserEntityAssembler.getUserEntityAssembler().toDomain(userEntity);
	}

	@Override
	public void confirmMobileNumber(final UUID id, final int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmEmail(final UUID id, final int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMobileNumberConfirmation(final UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailConfirmation(final UUID id) {
		// TODO Auto-generated method stub
		
	}	
}
	
// validar que la informacion sea consistente a nivel de tipo de dato,
//longitud,obligatoriedad, formato, rango, reglas propio de negocio etc
//validar que no exista previamente otro usuario con el mismo tipo y numero de identificacion
//validar que no exista previamente otro usuario con el mismo correo electronico
//validar que no exista previamente otro usuario con el mismo numero de telefono celular
//generar un identificador para el nuevo usuario asegurando que no exista 
