package co.edu.uco.nose.data.dao.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.entity.CountryEntity;

public final class CountryMapper {
	public static CountryEntity map(final ResultSet resultSet) {
		var country = new CountryEntity();
		try {
			country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
			country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_COUNTRY_MAPPER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_MAPPER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_COUNTRY_MAPPER_UNEXPECTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_MAPPER_UNEXPECTED.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return country;
		
	}
}