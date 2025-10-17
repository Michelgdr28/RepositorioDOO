package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.CountrySql;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.mapper.CountryMapper;
import co.edu.uco.nose.entity.CountryEntity;

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
				var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_ALL.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_ALL.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_ALL_UNEXPECTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_ALL_UNEXPECTED.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
			}
			
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_ALL.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_ALL.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_ALL_UNEXPECTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_ALL_UNEXPECTED.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		
		return countries;
	}

	@Override
	public List<CountryEntity> findByfilter(CountryEntity filterEntity){
		var countries = new ArrayList<CountryEntity>();

	    try (var preparedStatement = this.getConnection().prepareStatement(CountrySql.FIND_BY_FILTER)) {

	        int index = 1;

	        if (filterEntity.getId() != null) {
	            preparedStatement.setObject(index++, filterEntity.getId());
	        }

	        if (filterEntity.getName() != null && !filterEntity.getName().isBlank()) {
	            preparedStatement.setString(index++, "%" + filterEntity.getName() + "%");
	        }

	        try (var resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                var country = CountryMapper.map(resultSet);
	                countries.add(country);
	            }
	        } catch (final SQLException exception) {
	            var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_BY_FILTER.getContent();
	            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_BY_FILTER.getContent();
	            throw NoseException.create(exception, userMessage, technicalMessage);
	        } catch (final Exception exception) {
	            var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_BY_FILTER_UNEXPECTED.getContent();
	            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_BY_FILTER_UNEXPECTED.getContent();
	            throw NoseException.create(exception, userMessage, technicalMessage);
	        }

	    } catch (final SQLException exception) {
	        var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_BY_FILTER.getContent();
	        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_BY_FILTER.getContent();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    } catch (final Exception exception) {
	        var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_BY_FILTER_UNEXPECTED.getContent();
	        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_BY_FILTER_UNEXPECTED.getContent();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    }

	    return countries;
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
				
				var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_BY_ID.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_BY_ID.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			} catch (final Exception exception) {
				
				var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_BY_ID_UNEXPECTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_BY_ID_UNEXPECTED.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
				
			}
				
		} catch (final SQLException exception) {
			
			var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_BY_ID.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_BY_ID.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_COUNTRY_FIND_BY_ID_UNEXPECTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_COUNTRY_FIND_BY_ID_UNEXPECTED.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		}
		
		return country;
	}


}
