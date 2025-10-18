package co.edu.uco.nose.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public class UserBusinessImpl implements UserBusiness {
	
	private DAOFactory daoFactory;
	
	public UserBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registerNewUserInformation(UserDomain userDomain) {
		
		
		// validar que la informacion sea consistente a nivel de tipo de dato,
		//longitud,obligatoriedad, formato, rango, reglas propio de negocio etc
		//validar que no exista previamente otro usuario con el mismo tipo y numero de identificacion
		//validar que no exista previamente otro usuario con el mismo correo electronico
		//validar que no exista previamente otro usuario con el mismo numero de telefono celular
		//generar un identificador para el nuevo usuario asegurando que no exista 
		var id = UUIDHelper.getUUIDHelper().generateNewUUID();
		var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
		
		userEntity.setId(id);
		
		daoFactory.getUserDAO().create(userEntity);
		
	}

	@Override
	public void dropUserInformation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserInformation(UUID id, UserDomain newUserDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserDomain> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDomain> findUsersByFilter(UserDomain userFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDomain findSpecificUser(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmMobileNumber(UUID id, int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmEmail(UUID id, int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMobileNumberConfirmation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailConfirmation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	
}
