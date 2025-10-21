package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.DepartmentDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.DepartmentEntity;

public final class DepartmentSqlServerDAO extends SqlConnection implements DepartmentDAO {

    public DepartmentSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<DepartmentEntity> findAll() {
        var departments = new ArrayList<DepartmentEntity>();
        try (var preparedStatement = this.getConnection()
                .prepareStatement("SELECT id, name, id_coutry FROM Departament")) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var department = mapDepartment(resultSet);
                    departments.add(department);
                }
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_ALL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_ALL.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_ALL_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_ALL_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        return departments;
    }

    @Override
    public List<DepartmentEntity> findByfilter(final DepartmentEntity filterEntity) {
        var departments = new ArrayList<DepartmentEntity>();
        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var department = mapDepartment(resultSet);
                    departments.add(department);
                }
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
        return departments;
    }

    private String createSentenceFindByFilter(final DepartmentEntity filterEntity, final List<Object> parametersList) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, name, id_country FROM Departament");
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parametersList, filterEntity.getId() != null, "id = ?", filterEntity.getId());
        addCondition(conditions, parametersList, 
                filterEntity.getName() != null && !filterEntity.getName().isBlank(), 
                "name = ?", filterEntity.getName());
        if (filterEntity.getCountry() != null && filterEntity.getCountry().getId() != null) {
            addCondition(conditions, parametersList, true, "id_country = ?", filterEntity.getCountry().getId());
        }

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
        return sql.toString();
    }

    private void addCondition(final List<String> conditions, final List<Object> parametersList,
                              final boolean condition, final String clause, final Object value) {
        if (condition) {
            conditions.add(clause);
            parametersList.add(value);
        }
    }

    private DepartmentEntity mapDepartment(final ResultSet resultSet) throws SQLException {
        var entity = new DepartmentEntity();
        entity.setId(resultSet.getObject("id", UUID.class));
        entity.setName(resultSet.getString("name"));

        var country = new co.edu.uco.nose.entity.CountryEntity();
        country.setId(resultSet.getObject("id_country", UUID.class));
        entity.setCountry(country);

        return entity;
    }

    @Override
    public DepartmentEntity findById(final UUID id) {
        var department = new DepartmentEntity();

        try (var preparedStatement = this.getConnection()
                .prepareStatement("SELECT id, name, id_country FROM Departament WHERE id = ?")) {
            preparedStatement.setObject(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    department = mapDepartment(resultSet);
                }
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_ID.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_ID.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_DEPARTMENT_FIND_BY_ID_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEPARTMENT_FIND_BY_ID_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        return department;
    }
}

