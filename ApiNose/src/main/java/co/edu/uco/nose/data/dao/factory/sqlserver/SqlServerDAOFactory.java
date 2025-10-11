package co.edu.uco.nose.data.dao.factory.sqlserver;

import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.DepartmentDAO;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.CitySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.CountrySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.DepartmentSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.IdentificationTypeSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.UserSqlServerDAO;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class SqlServerDAOFactory extends DAOFactory {
	
	public SqlServerDAOFactory() {
		openConnection();
	}
	
	@Override
	protected void openConnection() {
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=YourDatabase;user=YourUsername;password=YourPassword;");
		}catch (final SQLException exception) {
				var userMessage = "Error al abrir la conexión a la base de datos.";
				var technicalMessage = "SQLException: " + exception.getMessage();
				throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = "Error inesperado al abrir la conexión a la base de datos.";
				var technicalMessage = "Exception: " + exception.getMessage();
				throw NoseException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public CityDAO getCityDAO() {
		return new CitySqlServerDAO(connection);
	}

	@Override
	public CountryDAO getCountryDAO() {
		return new CountrySqlServerDAO(connection);
	}

	@Override
	public IdentificationTypeDAO getIdType() {
		return new IdentificationTypeSqlServerDAO(connection);
	}

	@Override
	public DepartmentDAO getStateDAO() {
		return new DepartmentSqlServerDAO(connection);
	}

	@Override
	public UserDAO getuserUserDAO() {
		return new UserSqlServerDAO(connection);
	}

}
