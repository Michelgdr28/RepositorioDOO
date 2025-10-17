package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
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
				var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_ALL.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_ALL.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_ALL_UNEXPECTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_ALL_UNEXPECTED.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
			}
			
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_ALL.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_ALL.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_ALL_UNEXPECTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_ALL_UNEXPECTED.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		
		return departments;
	}

	@Override
	public List<DepartmentEntity> findByfilter(DepartmentEntity filterEntity) {
		var departments = new ArrayList<DepartmentEntity>();

		try (var preparedStatement = this.getConnection().prepareStatement(DepartmentSql.FIND_BY_FILTER)) {

			int index = 1;

			if (filterEntity.getId() != null) {
				preparedStatement.setObject(index++, filterEntity.getId());
			}

			if (filterEntity.getName() != null && !filterEntity.getName().isBlank()) {
				preparedStatement.setString(index++, "%" + filterEntity.getName() + "%");
			}

			if (filterEntity.getCountry() != null && filterEntity.getCountry().getId() != null) {
				preparedStatement.setObject(index++, filterEntity.getCountry().getId());
			}

			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					var department = DepartmentMapper.map(resultSet);
					departments.add(department);
				}
			} catch (final SQLException exception) {
				var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_FILTER.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
			}
			
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_FILTER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		
		return departments;
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
				
				var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_ID.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_ID.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			} catch (final Exception exception) {
				
				var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_ID_UNEXPECTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_ID_UNEXPECTED.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			}
				
		} catch (final SQLException exception) {
			
			var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_ID.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_ID.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		} catch (final Exception exception) {
			
			var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_ID_UNEXPECTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_ID_UNEXPECTED.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		}
		
		return department;
	}


}
