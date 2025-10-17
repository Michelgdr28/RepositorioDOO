package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.mapper.DepartmentMapper;
import co.edu.uco.nose.data.dao.entity.DepartmentDAO;
import co.edu.uco.nose.data.dao.entity.DepartmentSql;
import co.edu.uco.nose.entity.DepartmentEntity;

public final class DepartmentSqlServerDAO extends SqlConnection implements DepartmentDAO{

	public DepartmentSqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<DepartmentEntity> finAll() {
		var departments = new ArrayList<DepartmentEntity>();
		
		try (var preparedStatement = this.getConnection().prepareStatement(DepartmentSql.FIND_ALL)) {
			
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					
					var department = DepartmentMapper.map(resultSet);
					departments.add(department);
					
				}
			} catch (final SQLException exception) {
				var userMessage = "Ocurrió un problema al ejecutar la consulta de estados";
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
		
		return departments;
	}

	@Override
	public List<DepartmentEntity> findByfilter(DepartmentEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentEntity findById(final UUID id) {
		var department = new DepartmentEntity();
		
		try (var preparedStatement = this.getConnection().prepareStatement(DepartmentSql.FIND_BY_ID)) {
			preparedStatement.setObject(1, id);
			
			try (var resultSet = preparedStatement.executeQuery()) {
				
				if (resultSet.next()) {
					
					department = DepartmentMapper.map(resultSet);
					
				}
				
			} catch (final SQLException exception) {
				
				var userMessage = "Ocurrió un problema al ejecutar la consulta de estado";
				var technicalMessage = "Error SQL ejecutando el query en .";
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			} catch (final Exception exception) {
				
				var userMessage = "Ocurrió un problema INESPERADO al ejecutar la consulta de estado";
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
		
		return department;
	}


}
