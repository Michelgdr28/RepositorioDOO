package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.mapper.IdentificationTypeMapper;
import co.edu.uco.nose.data.dao.sql.IdentificationTypeSql;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public final class IdentificationTypeSqlServerDAO extends SqlConnection implements IdentificationTypeDAO {

	public IdentificationTypeSqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<IdentificationTypeEntity> finAll() {
		var idTypes = new ArrayList<IdentificationTypeEntity>();
		
		try (var preparedStatement = this.getConnection().prepareStatement(IdentificationTypeSql.FIND_ALL)) {
			
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					
					var idType = IdentificationTypeMapper.map(resultSet);
					idTypes.add(idType);
					
				}
			} catch (final SQLException exception) {
				var userMessage = "Ocurrió un problema al ejecutar la consulta de tipos de identificacion";
				var technicalMessage = "Error SQL ejecutando el query en .";
				throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = "Ocurrió un problema INESPERADO al ejecutar la consulta de ";
				var technicalMessage = "Error INESPERADO SQL ejecutando el query en .";
				throw NoseException.create(exception, userMessage, technicalMessage);
			}
			
		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		
		return idTypes;
	}

	@Override
	public List<IdentificationTypeEntity> findByfilter(IdentificationTypeEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdentificationTypeEntity findById(final UUID id) {
		var idType = new IdentificationTypeEntity();
		
		try (var preparedStatement = this.getConnection().prepareStatement(IdentificationTypeSql.FIND_BY_ID)) {
			preparedStatement.setObject(1, id);
			
			try (var resultSet = preparedStatement.executeQuery()) {
				
				if (resultSet.next()) {
					
					idType = IdentificationTypeMapper.map(resultSet);
					
				}
				
			} catch (final SQLException exception) {
				
				var userMessage = "Ocurrió un problema al ejecutar la consulta de tipo de identificacion";
				var technicalMessage = "Error SQL ejecutando el query en .";
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			} catch (final Exception exception) {
				
				var userMessage = "Ocurrió un problema INESPERADO al ejecutar la consulta de tipo de identificacion";
				var technicalMessage = "Error INESPERADO SQL ejecutando el query en ";
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			}
				
		} catch (final SQLException exception) {
			
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		} catch (final Exception exception) {
			
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		}
		
		return idType;
	}



}