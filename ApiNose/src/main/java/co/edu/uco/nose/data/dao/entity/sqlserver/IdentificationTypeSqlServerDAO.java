package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public final class IdentificationTypeSqlServerDAO extends SqlConnection implements IdentificationTypeDAO {

    public IdentificationTypeSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public IdentificationTypeEntity findById(final UUID id) {
        return findByfilter(new IdentificationTypeEntity(id)).stream().findFirst().orElse(new IdentificationTypeEntity());
    }

    @Override
    public List<IdentificationTypeEntity> findAll() {
        return findByfilter(new IdentificationTypeEntity());
    }

    @Override
    public List<IdentificationTypeEntity> findByfilter(final IdentificationTypeEntity filterEntity) {
        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }
            return executeSentenceFindByFilter(preparedStatement);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    private String createSentenceFindByFilter(final IdentificationTypeEntity filterEntity, final List<Object> parametersList) {
        final var sql = new StringBuilder();
        sql.append("SELECT ti.id AS idTipoIdentificacion, ");
        sql.append("ti.nombre AS nombreTipoIdentificacion ");
        sql.append("FROM TipoIdentificacion AS ti ");

        createWhereClauseFindByFilter(sql, parametersList, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
                                               final IdentificationTypeEntity filterEntity) {
        var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new IdentificationTypeEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()),
                "ti.id = ?", filterEntityValidated.getId());

        addCondition(conditions, parametersList,
                !TextHelper.isEmptyWithTrim(filterEntityValidated.getName()),
                "ti.nombre = ?", filterEntityValidated.getName());

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parametersList,
                              final boolean condition, final String clause, final Object value) {
        if (condition) {
            conditions.add(clause);
            parametersList.add(value);
        }
    }

    private List<IdentificationTypeEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
        var listIdTypes = new ArrayList<IdentificationTypeEntity>();
        try (var resultset = preparedStatement.executeQuery()) {
            while (resultset.next()) {
                var idType = new IdentificationTypeEntity();
                idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultset.getString("idTipoIdentificacion")));
                idType.setName(resultset.getString("nombreTipoIdentificacion"));

                listIdTypes.add(idType);
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
        return listIdTypes;
    }
}
