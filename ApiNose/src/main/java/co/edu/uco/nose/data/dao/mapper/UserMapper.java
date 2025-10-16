package co.edu.uco.nose.data.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
import co.edu.uco.nose.entity.DepartmentEntity;
import co.edu.uco.nose.entity.UserEntity;

public final class UserMapper {
	
	public static UserEntity map(ResultSet resultSet) {
		var user = new UserEntity();
		try {
			var idType = IdentificationTypeMapper.map(resultSet);
			
			var city = CityMapper.map(resultSet);
			
			user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
			user.setIdentificationType(idType);
			user.setFirstName(resultSet.getString("primerNombre"));
			user.setSecondName(resultSet.getString("segundoNombre"));
			user.setLastName(resultSet.getString("primerApellido"));
			user.setSecondLastName(resultSet.getString("segundoApellido"));
			user.setCity(city);
			user.setEmail(resultSet.getString("correoElectronico"));
			user.setPhoneNumber(resultSet.getString("numeroTelefonoMovil"));
			user.setPhoneConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));
			user.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));
			
			
		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return user;
	}
}
