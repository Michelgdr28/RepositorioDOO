package co.edu.uco.nose.data.dao.factory.sqlserver;

import java.util.Properties;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.DepartmentDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.CitySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.CountrySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.IdentificationTypeSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.DepartmentSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.UserSqlServerDAO;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class SqlServerDAOFactory extends DAOFactory {
	
	private JdbcTemplate jdbcTemplate;
	
	public SqlServerDAOFactory() {
		openConnection();
		jdbcTemplate = new JdbcTemplate();
	}
	
	private static final String PROPERTIES_FILE = "/application.properties";
	
	@Override
	protected void openConnection() {
		
		try (InputStream input = SqlConnectionHelper.class.getResourceAsStream(PROPERTIES_FILE)){
			Properties prop = new Properties();
			
            prop.load(input);

            String url = prop.getProperty("spring.datasource.url");
            String username = prop.getProperty("spring.datasource.username");
            String password = prop.getProperty("spring.datasource.password");

            this.connection = DriverManager.getConnection(url, username, password);
		} catch (final SQLException exception) {
			exception.printStackTrace();         
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
            throw NoseException.create(exception,userMessage,technicalMessage);
        } catch (final Exception exception){
        	exception.printStackTrace();
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
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
	public IdentificationTypeDAO getIdentificationTypeDAO() {
		return new IdentificationTypeSqlServerDAO(connection);
	}

	@Override
	public DepartmentDAO getDepartmentDAO() {
		return new DepartmentSqlServerDAO(connection);
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserSqlServerDAO(connection);
	}
	
}