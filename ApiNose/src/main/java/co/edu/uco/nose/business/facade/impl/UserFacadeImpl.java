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
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.dropUserInformation(id);
            daoFactory.commitTransaction();
        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Se presentó un problema inesperado al eliminar la información del usuario.";
            var technicalMessage = "Error inesperado en UserFacadeImpl.dropUserInformation al intentar eliminar usuario.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

	@Override
	public void updateUserInformation(UUID id, UserDTO newUserDTO) {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var newUserDomain = UserDTOAssembler.getUserDTOAssembler().toDomain(newUserDTO);
            business.updateUserInformation(id, newUserDomain);
            daoFactory.commitTransaction();
        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Se presentó un problema inesperado al actualizar la información del usuario.";
            var technicalMessage = "Error inesperado en UserFacadeImpl.updateUserInformation al intentar actualizar usuario.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }


	@Override
	public List<UserDTO> findAllUsers() {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
        try {
            var users = business.findAllUsers();
            return UserDTOAssembler.getUserDTOAssembler().toDTO(users);
        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado al consultar la lista de usuarios.";
            var technicalMessage = "Error inesperado en UserFacadeImpl.findAllUsers.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }


	@Override
	public List<UserDTO> findUsersByFilter(UserDTO userFilters) {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
        try {
            var filterDomain = UserDTOAssembler.getUserDTOAssembler().toDomain(userFilters);
            var result = business.findUsersByFilter(filterDomain);
            return UserDTOAssembler.getUserDTOAssembler().toDTO(result);
        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado al consultar los usuarios por filtro.";
            var technicalMessage = "Error inesperado en UserFacadeImpl.findUsersByFilter.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

	@Override
	public UserDTO findSpecificUser(UUID id) {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
        try {
            var user = business.findSpecificUser(id);
            return UserDTOAssembler.getUserDTOAssembler().toDTO(user);
        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado al consultar la información específica del usuario.";
            var technicalMessage = "Error inesperado en UserFacadeImpl.findSpecificUser.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

	@Override
	public void confirmMobileNumber(UUID id, int confirmationCode) {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.confirmMobileNumber(id, confirmationCode);
            daoFactory.commitTransaction();
        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Se presentó un problema inesperado al confirmar el número de celular del usuario.";
            var technicalMessage = "Error inesperado en UserFacadeImpl.confirmMobileNumber.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

	@Override
	public void confirmEmail(UUID id, int confirmationCode) {
		 var daoFactory = DAOFactory.getFactory();
		 var business = new UserBusinessImpl(daoFactory);
	        try {
	            daoFactory.initTransaction();
	            business.confirmEmail(id, confirmationCode);
	            daoFactory.commitTransaction();
	        } catch (final NoseException exception) {
	            daoFactory.rollbackTransaction();
	            throw exception;
	        } catch (final Exception exception) {
	            daoFactory.rollbackTransaction();
	            var userMessage = "Se presentó un problema inesperado al confirmar el correo electrónico del usuario.";
	            var technicalMessage = "Error inesperado en UserFacadeImpl.confirmEmail.";
	            throw NoseException.create(exception, userMessage, technicalMessage);
	        } finally {
	            daoFactory.closeConnection();
	        }
	    }

	@Override
	public void sendMobileNumberConfirmation(UUID id) {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.sendMobileNumberConfirmation(id);
            daoFactory.commitTransaction();
        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Se presentó un problema inesperado al enviar la confirmación del número de celular.";
            var technicalMessage = "Error inesperado en UserFacadeImpl.sendMobileNumberConfirmation.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

	@Override
	public void sendEmailConfirmation(UUID id) {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.sendEmailConfirmation(id);
            daoFactory.commitTransaction();
        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Se presentó un problema inesperado al enviar la confirmación de correo electrónico.";
            var technicalMessage = "Error inesperado en UserFacadeImpl.sendEmailConfirmation.";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }
}


