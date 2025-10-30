package co.edu.uco.nose.business.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class UserBusinessImpl implements UserBusiness {

	private DAOFactory daoFactory;

	public UserBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registerNewUserInformation(final UserDomain userDomain) {
		// 1. Validar que la información del usuario sea consistente
		if (ObjectHelper.getObjectHelper().isNull(userDomain)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_NULL);
		}

		if (TextHelper.isEmpty(userDomain.getEmail())) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_EMAIL_EMPTY);
		}

		// 2. Validar que no exista otro usuario con el mismo tipo y número de identificación
		var filterByIdentification = new UserDomain();
		filterByIdentification.setIdentificationType(userDomain.getIdentificationType());
		filterByIdentification.setIdentificationNumber(userDomain.getIdentificationNumber());

		var existingUsersById = findUsersByFilter(filterByIdentification);
		if (!existingUsersById.isEmpty()) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_DUPLICATED_IDENTIFICATION);
		}

		// 3. Validar que no exista otro usuario con el mismo correo electrónico
		var filterByEmail = new UserDomain();
		filterByEmail.setEmail(userDomain.getEmail());

		var existingUsersByEmail = findUsersByFilter(filterByEmail);
		if (!existingUsersByEmail.isEmpty()) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_DUPLICATED_EMAIL);
		}

		// 4. Validar que no exista otro usuario con el mismo número de teléfono
		var filterByPhone = new UserDomain();
		filterByPhone.setPhoneNumber(userDomain.getMobilePhone());

		var existingUsersByPhone = findUsersByFilter(filterByPhone);
		if (!existingUsersByPhone.isEmpty()) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_DUPLICATED_PHONE);
		}

		// 5. Generar un nuevo identificador
		var id = UUIDHelper.getUUIDHelper().generateNewUUID();

		// 6. Convertir el dominio en entidad
		var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
		userEntity.setId(id);

		// 7. Registrar el usuario en base de datos
		daoFactory.getUserDAO().create(userEntity);
	}

	@Override
	public void dropUserInformation(final UUID id) {
		if (UUIDHelper.getUUIDHelper().isDefaultUUID(id)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_INVALID_ID);
		}

		var existingUser = daoFactory.getUserDAO().findById(id);
		if (ObjectHelper.getObjectHelper().isNull(existingUser)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_NOT_FOUND);
		}

		daoFactory.getUserDAO().delete(id);
	}

	@Override
	public void updateUserInformation(final UUID id, final UserDomain userDomain) {
		if (UUIDHelper.getUUIDHelper().isDefaultUUID(id)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_INVALID_ID);
		}

		var existingUser = daoFactory.getUserDAO().findById(id);
		if (ObjectHelper.getObjectHelper().isNull(existingUser)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_NOT_FOUND);
		}

		var updatedEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
		updatedEntity.setId(id);

		daoFactory.getUserDAO().update(updatedEntity);
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

		if (ObjectHelper.getObjectHelper().isNull(userEntity)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_NOT_FOUND);
		}

		return UserEntityAssembler.getUserEntityAssembler().toDomain(userEntity);
	}

	@Override
	public void confirmMobileNumber(final UUID id, final int confirmationCode) {
		var user = daoFactory.getUserDAO().findById(id);
		if (ObjectHelper.getObjectHelper().isNull(user)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_NOT_FOUND);
		}

		if (user.getMobilePhoneConfirmationCode() != confirmationCode) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_INVALID_MOBILE_CODE);
		}

		user.setMobilePhoneConfirmed(true);
		daoFactory.getUserDAO().update(user);
	}

	@Override
	public void confirmEmail(final UUID id, final int confirmationCode) {
		var user = daoFactory.getUserDAO().findById(id);
		if (ObjectHelper.getObjectHelper().isNull(user)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_NOT_FOUND);
		}

		if (user.getEmailConfirmationCode() != confirmationCode) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_INVALID_EMAIL_CODE);
		}

		user.setEmailConfirmed(true);
		daoFactory.getUserDAO().update(user);
	}

	@Override
	public void sendMobileNumberConfirmation(final UUID id) {
		var user = daoFactory.getUserDAO().findById(id);
		if (ObjectHelper.getObjectHelper().isNull(user)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_NOT_FOUND);
		}

		// Aquí se podría integrar el servicio de envío de SMS
		System.out.println("Enviando código de confirmación móvil a: " + user.getPhoneNumber());
	}

	@Override
	public void sendEmailConfirmation(final UUID id) {
		var user = daoFactory.getUserDAO().findById(id);
		if (ObjectHelper.getObjectHelper().isNull(user)) {
			throw NoseException.create(MessagesEnum.BUSINESS_USER_NOT_FOUND);
		}

		// Aquí se podría integrar el servicio de envío de correo
		System.out.println("Enviando correo de confirmación a: " + user.getEmail());
	}
}

	
// validar que la informacion sea consistente a nivel de tipo de dato,
//longitud,obligatoriedad, formato, rango, reglas propio de negocio etc
//validar que no exista previamente otro usuario con el mismo tipo y numero de identificacion
//validar que no exista previamente otro usuario con el mismo correo electronico
//validar que no exista previamente otro usuario con el mismo numero de telefono celular
//generar un identificador para el nuevo usuario asegurando que no exista 
