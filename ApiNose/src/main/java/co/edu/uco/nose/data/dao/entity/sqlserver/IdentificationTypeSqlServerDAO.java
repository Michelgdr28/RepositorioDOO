package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public final class IdentificationTypeSqlServerDAO extends SqlConnection implements IdentificationTypeDAO {

    public IdentificationTypeSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<IdentificationTypeEntity> findAll() {
        var idTypes = new ArrayList<IdentificationTypeEntity>();
        try (var preparedStatement = this.getConnection().prepareStatement("SELECT id, nombre FROM TipoIdentificacion")) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var idType = mapIdentificationType(resultSet);
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
            var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_ALL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_ALL.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_ALL_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_ALL_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        return idTypes;
    }

    @Override
    public List<IdentificationTypeEntity> findByfilter(IdentificationTypeEntity filterEntity) {
        var idTypes = new ArrayList<IdentificationTypeEntity>();
        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var idType = mapIdentificationType(resultSet);
                    idTypes.add(idType);
                }
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

    private String createSentenceFindByFilter(final IdentificationTypeEntity filterEntity, final List<Object> parametersList) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, nombre FROM TipoIdentificacion");
        final var conditions = new ArrayList<String>();
        addCondition(conditions, parametersList, filterEntity.getId() != null, "id = ?", filterEntity.getId());
        addCondition(conditions, parametersList, filterEntity.getName() != null && !filterEntity.getName().isBlank(), "nombre = ?", filterEntity.getName());
        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
        return sql.toString();
    }

    private void addCondition(final List<String> conditions, final List<Object> parametersList, final boolean condition, final String clause, final Object value) {
        if (condition) {
            conditions.add(clause);
            parametersList.add(value);
        }
    }

    private IdentificationTypeEntity mapIdentificationType(ResultSet resultSet) throws SQLException {
        var entity = new IdentificationTypeEntity();
        entity.setId(resultSet.getObject("id", UUID.class));
        entity.setName(resultSet.getString("nombre"));
        return entity;
    }

    @Override
    public IdentificationTypeEntity findById(final UUID id) {
        var identificationType = new IdentificationTypeEntity();
        try (var preparedStatement = this.getConnection().prepareStatement("SELECT id, nombre FROM TipoIdentificacion WHERE id = ?")) {
            preparedStatement.setObject(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    identificationType = mapIdentificationType(resultSet);
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
