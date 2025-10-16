package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.mapper.CountryMapper;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.data.dao.sql.CountrySql;

public final class CountrySqlServerDAO extends SqlConnection implements CountryDAO {
	
	public CountrySqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<CountryEntity> finAll()  {
		var countries = new ArrayList<CountryEntity>();
		
		try (var preparedStatement = this.getConnection().prepareStatement(CountrySql.FIND_ALL)) {
			
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					
					var country = CountryMapper.map(resultSet);
					countries.add(country);
					
				}
			} catch (final SQLException exception) {
				var userMessage = "Ocurrió un problema al ejecutar la consulta de paises";
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
		
		return countries;
	}

	@Override
	public List<CountryEntity> findByfilter(CountryEntity filterEntity){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryEntity findById(final UUID id) {
		
		var country = new CountryEntity();
		
		try (var preparedStatement = this.getConnection().prepareStatement(CountrySql.FIND_BY_ID)) {
			preparedStatement.setObject(1, id);
			
			try (var resultSet = preparedStatement.executeQuery()) {
				
				if (resultSet.next()) {
					
					country = CountryMapper.map(resultSet);
					
				}
				
			} catch (final SQLException exception) {
				
				var userMessage = "Ocurrió un problema al ejecutar la consulta de pais";
				var technicalMessage = "Error SQL ejecutando el query en .";
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			} catch (final Exception exception) {
				
				var userMessage = "Ocurrió un problema INESPERADO al ejecutar la consulta de usuario";
				var technicalMessage = "Error INESPERADO SQL ejecutando el query en UserDAO.findById.";
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
		
		return country;
	}


}
