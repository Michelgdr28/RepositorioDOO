package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeSql;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.mapper.IdentificationTypeMapper;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public final class IdentificationTypeSqlServerDAO extends SqlConnection implements IdentificationTypeDAO {

	public IdentificationTypeSqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<IdentificationTypeEntity> finAll() {
		var idTypes = new ArrayList<IdentificationTypeEntity>();
		
		try (var preparedStatement = this.getConnection().prepareStatement(IdentificationTypeSql.FIND_ALL)) {
			
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					
					var idType = IdentificationTypeMapper.map(resultSet);
					idTypes.add(idType);
					
				}
			} catch (final SQLException exception) {
				var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_ALL.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_ALL.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
			} catch (final Exception exception) {
				var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_ALL_UNEXPECTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_ALL_UNEXPECTED.getContent();
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
		
		return idTypes;
	}

	@Override
	public List<IdentificationTypeEntity> findByfilter(IdentificationTypeEntity filterEntity) {
		var idTypes = new ArrayList<IdentificationTypeEntity>();

        try (var preparedStatement = this.getConnection().prepareStatement(IdentificationTypeSql.FIND_BY_FILTER)) {

            preparedStatement.setObject(1, filterEntity.getId());
            preparedStatement.setString(2, filterEntity.getName());
         

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var idType = IdentificationTypeMapper.map(resultSet);
                    idTypes.add(idType);
                }

            } catch (final SQLException exception) {
                var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER.getContent();
                throw NoseException.create(exception, userMessage, technicalMessage);

            } catch (final Exception exception) {
                var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER_UNEXPECTED.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER_UNEXPECTED.getContent(); 
                throw NoseException.create(exception, userMessage, technicalMessage);
            }

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return idTypes;
    }

	@Override
	public IdentificationTypeEntity findById(final UUID id) {
        var identificationType = new IdentificationTypeEntity();

        try (var preparedStatement = this.getConnection().prepareStatement(IdentificationTypeSql.FIND_BY_ID)) {
            preparedStatement.setObject(1, id);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    identificationType = IdentificationTypeMapper.map(resultSet);
                }

            } catch (final SQLException exception) {
                var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID.getContent();
                throw NoseException.create(exception, userMessage, technicalMessage);

            } catch (final Exception exception) {
                var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID_UNEXPECTED.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID_UNEXPECTED.getContent();
                throw NoseException.create(exception, userMessage, technicalMessage);
            }

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return identificationType;
    }
}