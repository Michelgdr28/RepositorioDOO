package co.edu.uco.nose.business.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;

public final class UserBusinessImpl implements UserBusiness {

	private final DAOFactory daoFactory;

	public UserBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registerNewUserInformation(final UserDomain userDomain) {
		var id = UUIDHelper.getUUIDHelper().generateNewUUID();
		var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
		userEntity.setId(id);

		try {
			daoFactory.getUserDAO().create(userEntity);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_USER_CREATE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_CREATE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void dropUserInformation(final UUID id) {
		try {
			daoFactory.getUserDAO().delete(id);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_USER_DELETE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_DELETE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void updateUserInformation(final UUID id, final UserDomain newUserDomain) {
		try {
			var existingUser = daoFactory.getUserDAO().findById(id);

			if (ObjectHelper.isNull(existingUser) || UUIDHelper.getUUIDHelper().isDefaultUUID(existingUser.getId())) {
				var userMessage = MessagesEnum.USER_ERROR_USER_NOT_FOUND.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_NOT_FOUND.getContent();
				throw NoseException.create(userMessage, technicalMessage);
			}

			var updatedEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(newUserDomain);
			updatedEntity.setId(id);
			daoFactory.getUserDAO().update(updatedEntity);

		} catch (final NoseException exception) {
			throw exception;
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_USER_UPDATE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_UPDATE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<UserDomain> findAllUsers() {
		try {
			List<UserEntity> entityList = daoFactory.getUserDAO().findAll();
			List<UserDomain> domainList = new ArrayList<>();

			for (UserEntity entity : entityList) {
				domainList.add(UserEntityAssembler.getUserEntityAssembler().toDomain(entity));
			}
			return domainList;

		} catch (final NoseException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw NoseException.create(exception,
					MessagesEnum.USER_ERROR_USER_FIND_ALL.getContent(),
					MessagesEnum.TECHNICAL_ERROR_USER_FIND_ALL.getContent());
		}
	}
	@Override
	public List<UserDomain> findUsersByFilter(final UserDomain userFilters) {
		try {
			var entityFilter = UserEntityAssembler.getUserEntityAssembler().toEntity(userFilters);
			var entityList = daoFactory.getUserDAO().findByfilter(entityFilter);

			List<UserDomain> domainList = new ArrayList<>();
			for (UserEntity entity : entityList) {
				domainList.add(UserEntityAssembler.getUserEntityAssembler().toDomain(entity));
			}
			return domainList;

		} catch (final NoseException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw NoseException.create(exception,
					MessagesEnum.USER_ERROR_USER_FIND_BY_FILTER.getContent(),
					MessagesEnum.TECHNICAL_ERROR_USER_FIND_BY_FILTER.getContent());
		}
	}


	@Override
	public UserDomain findSpecificUser(final UUID id) {
		try {
			var entity = daoFactory.getUserDAO().findById(id);

			if (ObjectHelper.isNull(entity)) {
				throw NoseException.create(
						MessagesEnum.USER_ERROR_USER_NOT_FOUND.getContent(),
						MessagesEnum.TECHNICAL_ERROR_USER_NOT_FOUND.getContent());
			}

			return UserEntityAssembler.getUserEntityAssembler().toDomain(entity);

		} catch (final NoseException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw NoseException.create(exception,
					MessagesEnum.USER_ERROR_USER_FIND_BY_ID.getContent(),
					MessagesEnum.TECHNICAL_ERROR_USER_FIND_BY_ID.getContent());
		}
	}

	@Override
	public void confirmMobileNumber(final UUID id, final int confirmationCode) {
		try {
			var user = daoFactory.getUserDAO().findById(id);

			if (ObjectHelper.isNull(user)) {
				var userMessage = MessagesEnum.USER_ERROR_USER_NOT_FOUND.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_NOT_FOUND.getContent();
				throw NoseException.create(userMessage, technicalMessage);
			}

			// Aquí se podría agregar lógica de verificación del código si el campo existe
			user.setMobilePhoneConfirmed(true);
			daoFactory.getUserDAO().update(user);

		} catch (final NoseException exception) {
			throw exception;
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_CONFIRM_MOBILE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CONFIRM_MOBILE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void confirmEmail(final UUID id, final int confirmationCode) {
		try {
			var user = daoFactory.getUserDAO().findById(id);

			if (ObjectHelper.isNull(user)) {
				var userMessage = MessagesEnum.USER_ERROR_USER_NOT_FOUND.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_NOT_FOUND.getContent();
				throw NoseException.create(userMessage, technicalMessage);
			}

			user.setEmailConfirmed(true);
			daoFactory.getUserDAO().update(user);

		} catch (final NoseException exception) {
			throw exception;
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_CONFIRM_EMAIL.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CONFIRM_EMAIL.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void sendMobileNumberConfirmation(final UUID id) {
		try {
			var user = daoFactory.getUserDAO().findById(id);
			if (ObjectHelper.isNull(user)) {
				var userMessage = MessagesEnum.USER_ERROR_USER_NOT_FOUND.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_NOT_FOUND.getContent();
				throw NoseException.create(userMessage, technicalMessage);
			}

			// Lógica simulada de envío de SMS
			System.out.println("Enviando código de confirmación al número: " + user.getMobilePhone());

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SEND_MOBILE_CONFIRMATION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SEND_MOBILE_CONFIRMATION.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void sendEmailConfirmation(final UUID id) {
		try {
			var user = daoFactory.getUserDAO().findById(id);
			if (ObjectHelper.isNull(user)) {
				var userMessage = MessagesEnum.USER_ERROR_USER_NOT_FOUND.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_NOT_FOUND.getContent();
				throw NoseException.create(userMessage, technicalMessage);
			}

			// Lógica simulada de envío de correo
			System.out.println("Enviando código de confirmación al correo: " + user.getEmail());

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SEND_EMAIL_CONFIRMATION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SEND_EMAIL_CONFIRMATION.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}
}

	
// validar que la informacion sea consistente a nivel de tipo de dato,
//longitud,obligatoriedad, formato, rango, reglas propio de negocio etc
//validar que no exista previamente otro usuario con el mismo tipo y numero de identificacion
//validar que no exista previamente otro usuario con el mismo correo electronico
//validar que no exista previamente otro usuario con el mismo numero de telefono celular
//generar un identificador para el nuevo usuario asegurando que no exista 
