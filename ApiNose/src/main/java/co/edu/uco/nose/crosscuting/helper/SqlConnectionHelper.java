package co.edu.uco.nose.crosscuting.helper;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public class SqlConnectionHelper {

public static Connection setConnection(Connection connection) {
	if(ObjectHelper.isNull(connection)) {
		var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_EMPTY.getContent();
		var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY.getContent();
		throw NoseException.create(userMessage, technicalMessage);
	}
	
	try {
		if(connection.isClosed()) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
			throw NoseException.create(userMessage, technicalMessage);
		}
	} catch (Exception exception) {
		var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
		var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
		throw NoseException.create(exception, userMessage, technicalMessage);
	}
	
	return connection;
}


public static void ensureTransactionIsNotStarted(Connection connection) {

	try {
		if (!connection.getAutoCommit()) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_TRANSACTION_NOT_INITIATE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_NOT_INITIATE.getContent();
			throw NoseException.create(userMessage, technicalMessage);
        }
        
    } catch (Exception exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_STATUS.getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_STATUS.getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    }
}

public static void ensureTransactionIsStarted(Connection connection) {

	try {
		if (connection.getAutoCommit()) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_TRANSACTION_NOT_INITIATE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_NOT_INITIATE.getContent();
			throw NoseException.create(userMessage, technicalMessage);
        }
        
    } catch (Exception exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_STATUS.getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_STATUS.getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    }
}

public static void ensureConnectionIsOpen(Connection connection) {

	try {
		if (connection.getAutoCommit()) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_TRANSACTION_NOT_INITIATE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_NOT_INITIATE.getContent();
			throw NoseException.create(userMessage, technicalMessage);
        }
        
    } catch (Exception exception) {
        var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_STATUS.getContent();
        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_STATUS.getContent();
        throw NoseException.create(exception, userMessage, technicalMessage);
    }
}

}