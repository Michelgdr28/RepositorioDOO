package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.UserSql;
import co.edu.uco.nose.data.dao.entity.mapper.UserMapper;

import co.edu.uco.nose.entity.UserEntity;

public final class UserSqlServerDAO extends SqlConnection implements UserDAO {

	
	public UserSqlServerDAO(final Connection connection) {
		super(connection);
	}
	
	
	@Override
	public void create(final UserEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		try (var preparedStatement = this.getConnection().prepareStatement(UserSql.CREATE)) {
			preparedStatement.setObject(1, entity.getId());
			preparedStatement.setObject(2, entity.getIdentificationType().getId());
			preparedStatement.setString(3, entity.getIdNumber());
			preparedStatement.setString(4, entity.getFirstName());
			preparedStatement.setString(5, entity.getSecondName());
			preparedStatement.setString(6, entity.getLastName());
			preparedStatement.setString(7, entity.getSecondLastName());
			preparedStatement.setObject(8, entity.getCity().getId());
			preparedStatement.setString(9, entity.getEmail());
			preparedStatement.setString(10, entity.getPhoneNumber());
			preparedStatement.setBoolean(11, entity.isEmailConfirmed());
			preparedStatement.setBoolean(12, entity.isPhoneConfirmed());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de registrar la informacion del nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de creacion de un usuario";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de crear un nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el ";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<UserEntity> finAll(){
		var users = new ArrayList<UserEntity>();
		
		try (var preparedStatement = this.getConnection().prepareStatement(UserSql.FIND_ALL)) {			
			
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					
					var user = UserMapper.map(resultSet);
					users.add(user);
				} 
			} catch (final SQLException exception) {
				var userMessage = "Ocurrió un problema al ejecutar la consulta de usuarios";
				var technicalMessage = "Error SQL ejecutando el query en UserDAO.findAll.";
				throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = "Ocurrió un problema INESPERADO al ejecutar la consulta de usuarios";
				var technicalMessage = "Error INESPERADO SQL ejecutando el query en UserDAO.findAll.";
				throw NoseException.create(exception, userMessage, technicalMessage);
			}
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de consultar la informacion de los usuarios. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de consulta de usuarios. Por favor verifique que la informacion este correcta";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de consultar la informacion de los usuarios. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema INESPERADO al tratar de ejecutar el proceso de consulta de usuarios. Por favor verifique que la informacion este correcta";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return users;
	}

	@Override
	public List<UserEntity> findByfilter(UserEntity filterEntity) {
		var users = new ArrayList<UserEntity>();
		
		try (var preparedStatement = this.getConnection().prepareStatement(UserSql.FIND_BY_FILTER)) {			
			
			preparedStatement.setObject(1, filterEntity.getId());
			preparedStatement.setObject(2, filterEntity.getIdentificationType().getId());
			preparedStatement.setString(3, filterEntity.getIdNumber());
			preparedStatement.setString(4, filterEntity.getFirstName());
			preparedStatement.setString(5, filterEntity.getSecondName());
			preparedStatement.setString(6, filterEntity.getLastName());
			preparedStatement.setString(7, filterEntity.getSecondLastName());
			preparedStatement.setObject(8, filterEntity.getCity().getId());
			preparedStatement.setString(9, filterEntity.getEmail());
			preparedStatement.setString(10, filterEntity.getPhoneNumber());
			
			if (filterEntity.isEmailConfirmed()) {
				preparedStatement.setBoolean(11, filterEntity.isEmailConfirmed());
			}
			if (filterEntity.isPhoneConfirmed()) {
				preparedStatement.setBoolean(12, filterEntity.isPhoneConfirmed());
			}
			
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					
					var user = UserMapper.map(resultSet);
					users.add(user);
				} 
			} catch (final SQLException exception) {
				var userMessage = "Ocurrió un problema al ejecutar la consulta de usuarios";
				var technicalMessage = "Error SQL ejecutando el query en UserDAO.findByFilter.";
				throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = "Ocurrió un problema INESPERADO al ejecutar la consulta de usuarios";
				var technicalMessage = "Error INESPERADO SQL ejecutando el query en UserDAO.findByFilter.";
				throw NoseException.create(exception, userMessage, technicalMessage);
			}
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de consultar la informacion de los usuarios. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de consulta de usuarios. Por favor verifique que la informacion este correcta";
			throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = "Se ha presentado un problema INESPERADO tratando de consultar la informacion de los usuarios. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
				var technicalMessage = "Se ha presentado un problema INESPERADO al tratar de ejecutar el proceso de consulta de usuarios. Por favor verifique que la informacion este correcta";
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			}
		
		return users;
	}

	@Override
	public UserEntity findById(final UUID id) {
		
		var user = new UserEntity();
		
		try (var preparedStatement = this.getConnection().prepareStatement(UserSql.FIND_BY_ID)) {
			preparedStatement.setObject(1, id);			
			
			try (var resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					
					user = UserMapper.map(resultSet);
					
				} 
				
			} catch (final SQLException exception) {
				
				var userMessage = "Ocurrió un problema al ejecutar la consulta de usuario";
				var technicalMessage = "Error SQL ejecutando el query en UserDAO.findById.";
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			} catch (final Exception exception) {
				
				var userMessage = "Ocurrió un problema INESPERADO al ejecutar la consulta de usuario";
				var technicalMessage = "Error INESPERADO SQL ejecutando el query en UserDAO.findById.";
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			}
			
		} catch (final SQLException exception) {
			
			var userMessage = "Se ha presentado un problema tratando de consultar la informacion del usuario deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de consulta del usuario deseado. Por favor verifique que la informacion este correcta";
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		} catch (final Exception exception) {
			
			var userMessage = "Se ha presentado un problema INESPERADO tratando de consultar la informacion del usuario deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema INESPERADO al tratar de ejecutar el proceso de consulta del usuario deseado. Por favor verifique que la informacion este correcta";
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		}
		return user;
	}

	@Override
	public void update(final UserEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		try (var preparedStatement = this.getConnection().prepareStatement(UserSql.UPDATE)) {
			preparedStatement.setObject(1, entity.getId());
			preparedStatement.setObject(2, entity.getIdentificationType().getId());
			preparedStatement.setString(3, entity.getIdNumber());
			preparedStatement.setString(4, entity.getFirstName());
			preparedStatement.setString(5, entity.getSecondName());
			preparedStatement.setString(6, entity.getLastName());
			preparedStatement.setString(7, entity.getSecondLastName());
			preparedStatement.setObject(8, entity.getCity().getId());
			preparedStatement.setString(9, entity.getEmail());
			preparedStatement.setString(10, entity.getPhoneNumber());
			preparedStatement.setBoolean(11, entity.isEmailConfirmed());
			preparedStatement.setBoolean(11, entity.isPhoneConfirmed());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de modificar la informacion del nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de modificacion de un usuario";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de modificar la informacion del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el proceso de modificacion de un usuario";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public void delete(final UUID id) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		try (var preparedStatement = this.getConnection().prepareStatement(UserSql.DELETE)) {
			preparedStatement.setObject(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de eliminar la informacion del nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de eliminacion del usuario";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de eliminar la informacion del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el proceso de eliminacion del usuario";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}		
	}
}
