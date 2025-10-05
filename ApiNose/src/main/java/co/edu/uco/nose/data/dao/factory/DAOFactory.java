package co.edu.uco.nose.data.dao.factory;

import java.sql.Connection;

import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.DepartmentDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;

public abstract class DAOFactory {

	protected Connection connection;
	protected FactoryEnum factory = FactoryEnum.SQLSERVER;
	
	public static DAOFactory getFactory() {
		return null;
	}

	public abstract CityDAO getCityDAO();

	public abstract CountryDAO getCountryDAO();

	public abstract IdentificationTypeDAO getIdType();

	public abstract DepartmentDAO getStateDAO();

	public abstract UserDAO getuserUserDAO();

	protected final void openConnection() {

	}

	protected final void initTransaction() {

	}

	protected final void commitTransaction() {

	}

	protected final void rollbackTransaction() {

	}

	protected final void closeConnection() {

	}

}
