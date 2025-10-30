package co.edu.uco.nose.business.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.business.impl.UserBusinessImpl;
import co.edu.uco.nose.business.facade.UserFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.UserDTO;

public final class UserFacadeImpl implements UserFacade {
		
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
			
			var userMessage = MessagesEnum.USER_ERROR_USER_CREATE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_CREATE.getContent();
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
            var userMessage = MessagesEnum.USER_ERROR_USER_DELETE.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_DELETE.getContent();
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
            var userMessage =MessagesEnum.USER_ERROR_USER_UPDATE.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_UPDATE.getContent();
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
            var userMessage = MessagesEnum.USER_ERROR_USER_FIND_ALL.getContent();
            var technicalMessage =  MessagesEnum.TECHNICAL_ERROR_USER_FIND_ALL.getContent();
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
            var userMessage = MessagesEnum.USER_ERROR_USER_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_FIND_BY_FILTER.getContent();
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
            var userMessage = MessagesEnum.USER_ERROR_USER_NOT_FOUND.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_NOT_FOUND.getContent();
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
            var userMessage =  MessagesEnum.USER_ERROR_CONFIRM_MOBILE.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CONFIRM_MOBILE.getContent();
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
	            var userMessage = MessagesEnum.USER_ERROR_CONFIRM_EMAIL.getContent();
	            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CONFIRM_EMAIL.getContent();
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
            var userMessage = MessagesEnum.USER_ERROR_SEND_MOBILE_CONFIRMATION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SEND_MOBILE_CONFIRMATION.getContent();
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
            var userMessage = MessagesEnum.USER_ERROR_SEND_EMAIL_CONFIRMATION.getContent();
            var technicalMessage =MessagesEnum.TECHNICAL_ERROR_SEND_EMAIL_CONFIRMATION.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }
}


