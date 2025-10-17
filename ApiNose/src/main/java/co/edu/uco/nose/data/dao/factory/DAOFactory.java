package co.edu.uco.nose.data.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.DepartmentDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.factory.sqlserver.SqlServerDAOFactory;

public abstract class DAOFactory {

	protected Connection connection;
	protected static FactoryEnum factory = FactoryEnum.SQLSERVER;
	
	public static DAOFactory getFactory() {

		if(FactoryEnum.SQLSERVER.equals(factory)) {
			return new SqlServerDAOFactory();
		} else {
			var userMessage = MessagesEnum.USER_ERROR_FACTORY_NOT_INITIALIZED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FACTORY_NOT_INITIALIZED.getContent();
			throw NoseException.create(userMessage, technicalMessage);
		}
	}

	public abstract CityDAO getCityDAO();

	public abstract CountryDAO getCountryDAO();

	public abstract IdentificationTypeDAO getIdentificationType();

	public abstract DepartmentDAO getDepartmentDAO();

	public abstract UserDAO getUserDAO();

	protected abstract void openConnection();

	protected final void initTransaction() {
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
		try {
			connection.setAutoCommit(false);
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_TRANSACTION_BEGIN.getContent();
			var technicalMessage =  MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_BEGIN.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage =MessagesEnum.USER_ERROR_SQL_TRANSACTION_BEGIN.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_BEGIN.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	protected final void commitTransaction() {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);
		try {
			connection.commit();
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_TRANSACTION_COMMIT.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_COMMIT.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_TRANSACTION_COMMIT.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_COMMIT.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	protected final void rollbackTransaction() {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);
		try {
			connection.rollback();
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_TRANSACTION_ROLLBACK.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_ROLLBACK.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_TRANSACTION_ROLLBACK.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_TRANSACTION_ROLLBACK.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	protected final void closeConnection() {
		SqlConnectionHelper.ensureConnectionIsOpen(connection);
		try {
			connection.rollback();
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_CLOSE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_CLOSE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_CLOSE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_CLOSE.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
	}
}
}