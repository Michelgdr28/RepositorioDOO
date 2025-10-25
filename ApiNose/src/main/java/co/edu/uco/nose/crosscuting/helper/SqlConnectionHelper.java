package co.edu.uco.nose.crosscuting.helper;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public class SqlConnectionHelper {
	private static final String PROPERTIES_FILE = "/application.properties";
	private SqlConnectionHelper() {
		
	}

public static Connection setConnection() {
	try (InputStream input = SqlConnectionHelper.class.getResourceAsStream(PROPERTIES_FILE)){
		Properties prop = new Properties ();
		prop.load(input);
		
		String url = prop.getProperty("sping.datasource.url");
		String username = prop.getProperty("spring.datasource.username");
		String password = prop.getProperty("spring.datasource.password");
		String driver = prop.getProperty("spring.datasource.driver");
		
		Class.forName(driver);
		return DriverManager.getConnection(url,username,password);
	} catch (Exception exception) {
		var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
		var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
		throw NoseException.create(exception, userMessage, technicalMessage);
	}
	
}
public static void ensureConnectionIsNotNull(final Connection connection) {
    if (ObjectHelper.isNull(connection)) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_EMPTY.getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY.getContent();
        throw NoseException.create(userMessage, technicalMessage);
    }
}

public static void ensureConnectionIsOpen(final Connection connection) {

    ensureConnectionIsNotNull(connection);

    try {
        if (connection.isClosed()) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    } catch (final SQLException exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS
                .getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS
                .getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    } catch (final Exception exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS
                .getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS
                .getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    }
}

public static void ensureTransactionIsStarted(final Connection connection) {

    ensureConnectionIsOpen(connection);

    try {
        if (connection.getAutoCommit()) {
            var userMessage = MessagesEnum.USER_ERROR_TRANSACTION_IS_NOT_STARTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    } catch (final SQLException exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED
                .getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED
                .getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    } catch (final Exception exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED
                .getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED
                .getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    }
}

public static void ensureTransactionIsNotStarted(final Connection connection) {

    ensureConnectionIsOpen(connection);

    try {
        if (!connection.getAutoCommit()) {
            var userMessage = MessagesEnum.USER_ERROR_TRANSACTION_IS_NOT_STARTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    } catch (final SQLException exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED
                .getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED
                .getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    } catch (final Exception exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED
                .getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED
                .getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    }
}
}