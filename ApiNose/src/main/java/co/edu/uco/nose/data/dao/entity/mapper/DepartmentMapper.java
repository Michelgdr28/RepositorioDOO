package co.edu.uco.nose.data.dao.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.entity.DepartmentEntity;

public final class DepartmentMapper {
	
	public static DepartmentEntity map(final ResultSet resultSet) {
		var department = new DepartmentEntity();
		try {
			var country = CountryMapper.map(resultSet);
			
			department.setCountry(country);
			department.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
			department.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_MAPPER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_MAPPER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_MAPPER_UNEXPECTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_MAPPER_UNEXPECTED.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return department;
		
	}

}
