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
import co.edu.uco.nose.data.dao.entity.DepartmentDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.DepartmentEntity;

public final class DepartmentSqlServerDAO extends SqlConnection implements DepartmentDAO {

    public DepartmentSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public DepartmentEntity findById(final UUID id) {
        return findByfilter(new DepartmentEntity(id)).stream().findFirst().orElse(new DepartmentEntity());
    }

    @Override
    public List<DepartmentEntity> findAll() {
        return findByfilter(new DepartmentEntity());
    }

    @Override
    public List<DepartmentEntity> findByfilter(final DepartmentEntity filterEntity) {
        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }
            return executeSentenceFindByFilter(preparedStatement);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    private String createSentenceFindByFilter(final DepartmentEntity filterEntity, final List<Object> parametersList) {
        final var sql = new StringBuilder();
        sql.append("SELECT d.id AS idDepartamento, ");
        sql.append("d.nombre AS nombreDepartamento, ");
        sql.append("p.id AS idPais, ");
        sql.append("p.nombre AS nombrePais ");
        sql.append("FROM Departamento AS d ");
        sql.append("INNER JOIN Pais AS p ON d.pais = p.id ");

        createWhereClauseFindByFilter(sql, parametersList, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList, final DepartmentEntity filterEntity) {
        var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new DepartmentEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()),
                "d.id = ?", filterEntityValidated.getId());

        addCondition(conditions, parametersList,
                !TextHelper.isEmptyWithTrim(filterEntityValidated.getName()),
                "d.nombre = ?", filterEntityValidated.getName());

        if (filterEntityValidated.getCountry() != null) {
            addCondition(conditions, parametersList,
                    !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCountry().getId()),
                    "d.pais = ?", filterEntityValidated.getCountry().getId());
        }

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

    private List<DepartmentEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
        var listDepartments = new ArrayList<DepartmentEntity>();
        try (var resultset = preparedStatement.executeQuery()) {
            while (resultset.next()) {
                var country = new CountryEntity();
                country.setId(UUIDHelper.getUUIDHelper().getFromString(resultset.getString("idPais")));
                country.setName(resultset.getString("nombrePais"));

                var department = new DepartmentEntity();
                department.setId(UUIDHelper.getUUIDHelper().getFromString(resultset.getString("idDepartamento")));
                department.setName(resultset.getString("nombreDepartamento"));
                department.setCountry(country);

                listDepartments.add(department);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        return listDepartments;
    }
}
