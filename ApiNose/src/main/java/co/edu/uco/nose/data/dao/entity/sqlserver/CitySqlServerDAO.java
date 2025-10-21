package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.DepartmentEntity;

public final class CitySqlServerDAO extends SqlConnection implements CityDAO {

    public CitySqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<CityEntity> findAll() {
        var cities = new ArrayList<CityEntity>();

        try (var preparedStatement = this.getConnection()
                .prepareStatement("SELECT id, name, id_departament FROM City")) {

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var city = mapCity(resultSet);
                    cities.add(city);
                }
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_CITY_FIND_ALL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CITY_FIND_ALL.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_CITY_FIND_ALL_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CITY_FIND_ALL_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return cities;
    }

    @Override
    public List<CityEntity> findByfilter(final CityEntity filterEntity) {
        var cities = new ArrayList<CityEntity>();
        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var city = mapCity(resultSet);
                    cities.add(city);
                }
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_CITY_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CITY_FIND_BY_FILTER.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_CITY_FIND_BY_FILTER_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CITY_FIND_BY_FILTER_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return cities;
    }

    private String createSentenceFindByFilter(final CityEntity filterEntity, final List<Object> parametersList) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, name, id_departament FROM City");

        final var conditions = new ArrayList<String>();

        addCondition(conditions, parametersList, filterEntity.getId() != null, "id = ?", filterEntity.getId());
        addCondition(conditions, parametersList,
                filterEntity.getName() != null && !filterEntity.getName().isBlank(),
                "name = ?", filterEntity.getName());

        if (filterEntity.getDepartment() != null && filterEntity.getDepartment().getId() != null) {
            addCondition(conditions, parametersList, true, "id_departament = ?", filterEntity.getDepartment().getId());
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

    private CityEntity mapCity(final ResultSet resultSet) throws SQLException {
        var entity = new CityEntity();
        entity.setId(resultSet.getObject("id", UUID.class));
        entity.setName(resultSet.getString("name"));

        var department = new DepartmentEntity();
        department.setId(resultSet.getObject("id_departament", UUID.class));
        entity.setDepartment(department);

        return entity;
    }

    @Override
    public CityEntity findById(final UUID id) {
        var city = new CityEntity();

        try (var preparedStatement = this.getConnection()
                .prepareStatement("SELECT id, name, id_departament FROM City WHERE id = ?")) {

            preparedStatement.setObject(1, id);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    city = mapCity(resultSet);
                }
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_CITY_FIND_BY_ID.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CITY_FIND_BY_ID.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_CITY_FIND_BY_ID_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CITY_FIND_BY_ID_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return city;
    }
}

