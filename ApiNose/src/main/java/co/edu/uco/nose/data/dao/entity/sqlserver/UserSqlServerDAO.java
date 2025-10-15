package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
import co.edu.uco.nose.entity.DepartmentEntity;
import co.edu.uco.nose.entity.UserEntity;

public final class UserSqlServerDAO extends SqlConnection implements UserDAO {

	public UserSqlServerDAO(Connection connection) {
		super(connection);

	}
	@Override
	public void create(UserEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO Usuario (id, tipoIdentificacion, "
				+ "numeroIdentificacion, primerNombre, segundoNombre, "
				+ "primerApellido, segundoApellido, ciudadResidencia, "
				+ "correoElectronico, numeroTelefonoMovil, "
				+ "correoElectronicoConfirmado, numeroTelefonoMovilConfirmado)");
		sql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
		
		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
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
			var userMessage = "Se ha presentado un problema tratando de registrar la informacion";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el ";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de registrar la informacion";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el ";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

	}

	@Override
	public UserEntity findById(UUID id) {
		
		var user = new UserEntity();
		
		final var sql = new StringBuilder();
		sql.append("SELECT 	u.id,");
		sql.append("	ti.id AS idTipoIdentificacion,");
		sql.append("	ti.nombre AS nombreTipoIdentificacion,");
		sql.append("	u.numeroIdentificacion,");
		sql.append("	u.primerNombre,");
		sql.append("	u.segundoNombre,");
		sql.append("	u.primerApellido,");
		sql.append("	u.segundoApellido,");
		sql.append("	c.id AS idCiudadResidencia,");
		sql.append("	c.nombre AS nombreCiudadResidencia,");
		sql.append("	d.id AS idDepartamentoCiudadResidencia,");
		sql.append("	d.nombre AS nombreDepartamentoCiudadResidencia,");
		sql.append("	p.id AS idPaisDepartamentoCiudadResidencia,");
		sql.append("	p.nombre AS nombrePaisDepartamentoCiudadResidencia,");
		sql.append("	u.correoElectronico,");
		sql.append("	u.numeroTelefonoMovil,");
		sql.append("	u.correoElectronicoConfirmado,");
		sql.append("	u.numeroTelefonoMovilCOnfirmado");
		sql.append("FROM Usuario AS u");
		sql.append("INNER JOIN Tipoidentificacion AS ti");
		sql.append("ON u.tipoIdentificacion = ti.id");
		sql.append("INNER JOIN Ciudad AS c");
		sql.append("ON c.departamento");
		sql.append("INNER JOIN Pais AS p");
		sql.append("ON d.pais = p.id");
		sql.append("WHERE u.id = ?");
		
		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
			preparedStatement.setObject(1, id);			
			preparedStatement.executeUpdate();
			
			try(var resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					
					var idType = new IdentificationTypeEntity();
					idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
					idType.setName(resultSet.getString("nombreTipoIdentificacion"));
					
					var country = new CountryEntity();
					country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
					country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));
					
					var state = new DepartmentEntity();
					state.setCountry(country);
					state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
					state.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));
					
					var city = new CityEntity();
					city.setDepartment(state);
					city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
					city.setName(resultSet.getString("nombreCiudadResidencia"));
					
					user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
					user.setIdentificationType(idType);
					user.setFirstName(resultSet.getString(resultSet.getString("primerNombre")));
					user.setSecondName(resultSet.getString(resultSet.getString("segundoNombre")));
					user.setLastName(resultSet.getString(resultSet.getString("primerApellido")));
					user.setSecondLastName(resultSet.getString(resultSet.getString("segundoApellido")));
					user.setCity(city);
					user.setEmail(resultSet.getString(resultSet.getString("correoElectronico")));
					user.setPhoneNumber(resultSet.getString(resultSet.getString("numeroTelefonoMovil")));
					user.setPhoneConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));
					user.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));
				} // Como aseguro que puedo mostrar claramente cuando un problema se presento ejecutando la sentencia SQL de consulta o preparando la sentencia de consulta
			}
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de modificar la informacion del usuario deseado";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de consulta del usuario deseado";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de consultar la informacion del usuario deseado";
			var technicalMessage = "Se ha presentado un problema INESPERADO al tratar de ejcutar el proceso de consulta del usuario deseado" + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return user;
	}

	@Override
	public void update(UserEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("UPDATE Usuario");
		sql.append("SET		tipoIdentificacion = ?,");
		sql.append("		numeroIdentificacion = ? ,");
		sql.append("		primerNombre = ? ,");
		sql.append("		segundoNombre = ? ,");
		sql.append("		primerApellido = ? ,");
		sql.append("		segundoApellido = ? ,");
		sql.append("		ciudadResidencia = ? ,");
		sql.append("		correoElectronico = ? ,");
		sql.append("		numeroTelefonoMovil = ? ,");
		sql.append("		correoElectronicoConfirmado = ? ,");
		sql.append("		numeroTelefonoMovilConfirmado = ? ,");
		sql.append("WHERE id = ?");
		
		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
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
			var userMessage = "Se ha presentado un problema tratando de registrar la informacion";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el ";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de registrar la informacion";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el ";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public void delete(UUID id) {
SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM Usuario");
		sql.append("WHERE id = ?");
		
		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
			preparedStatement.setObject(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de eliminar la informacion";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el ";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de eliminar la informacion";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el ";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
    }
	@Override
	public List<UserEntity> finAll() {
	    SqlConnectionHelper.ensureConnectionIsOpen(getConnection());

	    final var sql = new StringBuilder();
	    sql.append("SELECT  u.id,");
	    sql.append("        ti.id AS idTipoIdentificacion,");
	    sql.append("        ti.nombre AS nombreTipoIdentificacion,");
	    sql.append("        u.numeroIdentificacion,");
	    sql.append("        u.primerNombre,");
	    sql.append("        u.segundoNombre,");
	    sql.append("        u.primerApellido,");
	    sql.append("        u.segundoApellido,");
	    sql.append("        c.id AS idCiudadResidencia,");
	    sql.append("        c.nombre AS nombreCiudadResidencia,");
	    sql.append("        d.id AS idDepartamentoCiudadResidencia,");
	    sql.append("        d.nombre AS nombreDepartamentoCiudadResidencia,");
	    sql.append("        p.id AS idPaisDepartamentoCiudadResidencia,");
	    sql.append("        p.nombre AS nombrePaisDepartamentoCiudadResidencia,");
	    sql.append("        u.correoElectronico,");
	    sql.append("        u.numeroTelefonoMovil,");
	    sql.append("        u.correoElectronicoConfirmado,");
	    sql.append("        u.numeroTelefonoMovilConfirmado");
	    sql.append(" FROM Usuario AS u");
	    sql.append(" INNER JOIN Tipoidentificacion AS ti ON u.tipoIdentificacion = ti.id");
	    sql.append(" INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id");
	    sql.append(" INNER JOIN Departamento AS d ON c.departamento = d.id");
	    sql.append(" INNER JOIN Pais AS p ON d.pais = p.id");

	    final var users = new ArrayList<UserEntity>();

	    try (var preparedStatement = getConnection().prepareStatement(sql.toString());
	         var rs = preparedStatement.executeQuery()) {

	        while (rs.next()) {
	            users.add(mapResultSetToUserEntity(rs));
	        }
	        return users;

	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de recuperar la lista de usuarios";
	        var technicalMessage = "Se ha presentado un problema al ejecutar la consulta SELECT de usuarios: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    } catch (final Exception exception) {
	        var userMessage = "Se ha presentado un problema INESPERADO tratando de recuperar la lista de usuarios";
	        var technicalMessage = "Se ha presentado un error inesperado al procesar la consulta de usuarios: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    }
	}

	@Override
	public List<UserEntity> findByfilter(UserEntity filterEntity) {
	    SqlConnectionHelper.ensureConnectionIsOpen(getConnection());

	    final var sb = new StringBuilder();
	    sb.append("SELECT  u.id,");
	    sb.append("        ti.id AS idTipoIdentificacion,");
	    sb.append("        ti.nombre AS nombreTipoIdentificacion,");
	    sb.append("        u.numeroIdentificacion,");
	    sb.append("        u.primerNombre,");
	    sb.append("        u.segundoNombre,");
	    sb.append("        u.primerApellido,");
	    sb.append("        u.segundoApellido,");
	    sb.append("        c.id AS idCiudadResidencia,");
	    sb.append("        c.nombre AS nombreCiudadResidencia,");
	    sb.append("        d.id AS idDepartamentoCiudadResidencia,");
	    sb.append("        d.nombre AS nombreDepartamentoCiudadResidencia,");
	    sb.append("        p.id AS idPaisDepartamentoCiudadResidencia,");
	    sb.append("        p.nombre AS nombrePaisDepartamentoCiudadResidencia,");
	    sb.append("        u.correoElectronico,");
	    sb.append("        u.numeroTelefonoMovil,");
	    sb.append("        u.correoElectronicoConfirmado,");
	    sb.append("        u.numeroTelefonoMovilConfirmado");
	    sb.append(" FROM Usuario AS u");
	    sb.append(" INNER JOIN Tipoidentificacion AS ti ON u.tipoIdentificacion = ti.id");
	    sb.append(" INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id");
	    sb.append(" INNER JOIN Departamento AS d ON c.departamento = d.id");
	    sb.append(" INNER JOIN Pais AS p ON d.pais = p.id");
	    sb.append(" WHERE 1 = 1");

	    final var params = new ArrayList<Object>();
	    final var defaultUUID = UUIDHelper.getUUIDHelper().getDefault();

	    if (filterEntity != null) {
	        if (filterEntity.getId() != null && !filterEntity.getId().equals(defaultUUID)) {
	            sb.append(" AND u.id = ?");
	            params.add(filterEntity.getId());
	        }
	        if (filterEntity.getIdentificationType() != null && filterEntity.getIdentificationType().getId() != null
	                && !filterEntity.getIdentificationType().getId().equals(defaultUUID)) {
	            sb.append(" AND u.tipoIdentificacion = ?");
	            params.add(filterEntity.getIdentificationType().getId());
	        }
	        if (filterEntity.getIdNumber() != null && !filterEntity.getIdNumber().isBlank()) {
	            sb.append(" AND u.numeroIdentificacion = ?");
	            params.add(filterEntity.getIdNumber());
	        }
	        if (filterEntity.getFirstName() != null && !filterEntity.getFirstName().isBlank()) {
	            sb.append(" AND u.primerNombre = ?");
	            params.add(filterEntity.getFirstName());
	        }
	        if (filterEntity.getSecondName() != null && !filterEntity.getSecondName().isBlank()) {
	            sb.append(" AND u.segundoNombre = ?");
	            params.add(filterEntity.getSecondName());
	        }
	        if (filterEntity.getLastName() != null && !filterEntity.getLastName().isBlank()) {
	            sb.append(" AND u.primerApellido = ?");
	            params.add(filterEntity.getLastName());
	        }
	        if (filterEntity.getSecondLastName() != null && !filterEntity.getSecondLastName().isBlank()) {
	            sb.append(" AND u.segundoApellido = ?");
	            params.add(filterEntity.getSecondLastName());
	        }
	        if (filterEntity.getCity() != null && filterEntity.getCity().getId() != null
	                && !filterEntity.getCity().getId().equals(defaultUUID)) {
	            sb.append(" AND u.ciudadResidencia = ?");
	            params.add(filterEntity.getCity().getId());
	        }
	        if (filterEntity.getEmail() != null && !filterEntity.getEmail().isBlank()) {
	            sb.append(" AND u.correoElectronico = ?");
	            params.add(filterEntity.getEmail());
	        }
	        if (filterEntity.getPhoneNumber() != null && !filterEntity.getPhoneNumber().isBlank()) {
	            sb.append(" AND u.numeroTelefonoMovil = ?");
	            params.add(filterEntity.getPhoneNumber());
	        }
	        if (filterEntity.isEmailConfirmed()) {
	            sb.append(" AND u.correoElectronicoConfirmado = ?");
	            params.add(true);
	        }
	        if (filterEntity.isPhoneConfirmed()) {
	            sb.append(" AND u.numeroTelefonoMovilConfirmado = ?");
	            params.add(true);
	        }
	    }

	    final var results = new ArrayList<UserEntity>();

	    try (var preparedStatement = getConnection().prepareStatement(sb.toString())) {

	        for (int i = 0; i < params.size(); i++) {
	            preparedStatement.setObject(i + 1, params.get(i));
	        }

	        try (var rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                results.add(mapResultSetToUserEntity(rs));
	            }
	        }

	        return results;

	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de consultar usuarios por filtro";
	        var technicalMessage = "Error ejecutando la consulta de usuarios por filtro: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    } catch (final Exception exception) {
	        var userMessage = "Se ha presentado un problema INESPERADO tratando de consultar usuarios por filtro";
	        var technicalMessage = "Error inesperado al procesar la consulta de usuarios por filtro: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    }
	}

	/**
	 * Mapea la fila actual del ResultSet a UserEntity con sus objetos relacionados (IdentificationType, City, Department, Country).
	 */
	private UserEntity mapResultSetToUserEntity(final ResultSet resultSet) throws SQLException {
	    final var user = new UserEntity();

	    // identification type
	    var idType = new IdentificationTypeEntity();
	    idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
	    idType.setName(resultSet.getString("nombreTipoIdentificacion"));

	    // country
	    var country = new CountryEntity();
	    country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
	    country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));

	    // department/state
	    var state = new DepartmentEntity();
	    state.setCountry(country);
	    state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
	    state.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));

	    // city
	    var city = new CityEntity();
	    city.setDepartment(state);
	    city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
	    city.setName(resultSet.getString("nombreCiudadResidencia"));

	    // user basic fields
	    user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
	    user.setIdentificationType(idType);
	    user.setIdNumber(resultSet.getString("numeroIdentificacion"));
	    user.setFirstName(resultSet.getString("primerNombre"));
	    user.setSecondName(resultSet.getString("segundoNombre"));
	    user.setLastName(resultSet.getString("primerApellido"));
	    user.setSecondLastName(resultSet.getString("segundoApellido"));
	    user.setCity(city);
	    user.setEmail(resultSet.getString("correoElectronico"));
	    user.setPhoneNumber(resultSet.getString("numeroTelefonoMovil"));
	    user.setPhoneConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));
	    user.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));

	    return user;
	}
}

