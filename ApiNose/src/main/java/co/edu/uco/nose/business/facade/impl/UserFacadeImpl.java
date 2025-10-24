package co.edu.uco.nose.business.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.business.impl.UserBusinessImpl;
import co.edu.uco.nose.business.facade.UserFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.UserDTO;

public class UserFacadeImpl implements UserFacade {
	
private DAOFactory daoFactory;
	
	public UserFacadeImpl() {
		this.daoFactory = daoFactory.getFactory();
	}
	

	@Override
	public void registerNewUserInformation(final UserDTO userDTO) {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
		try {
			
			daoFactory.initTransaction();
			var domain = UserDTOAssembler.getUserDTOAssembler().toDomain(userDTO);
			business.registerNewUserInformation(domain);
			
			daoFactory.commitTransaction();
			
		}catch(final NoseException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		}catch(final Exception exception) {
			daoFactory.rollbackTransaction();
			
			var userMessage = "Se ha presentado un problema inesperado al registrar la información del nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema.";
			var technicalMessage = "Se ha presentado un problema inesperado al registrar la información del nuevo usuario. Por favor revise el log de errores para mayor detalle.";
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		}finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void dropUserInformation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserInformation(UUID id, UserDTO newUserDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserDTO> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findUsersByFilter(UserDTO userFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findSpecificUser(UUID id) {
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
