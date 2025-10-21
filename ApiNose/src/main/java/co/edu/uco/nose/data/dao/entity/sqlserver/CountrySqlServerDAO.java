package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CountryEntity;

public final class CountrySqlServerDAO extends SqlConnection implements CountryDAO {

    public CountrySqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<CountryEntity> findAll() {
        var countries = new ArrayList<CountryEntity>();

        try (var preparedStatement = this.getConnection().prepareStatement(
                "SELECT id, nombre FROM Pais")) {

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var country = mapCountry(resultSet);
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
    public List<CountryEntity> findByfilter(CountryEntity filterEntity) {
        var countries = new ArrayList<CountryEntity>();
        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {

            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var country = mapCountry(resultSet);
                    countries.add(country);
                }
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

    private String createSentenceFindByFilter(final CountryEntity filterEntity, final List<Object> parametersList) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, nombre FROM Pais");
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parametersList, filterEntity.getId() != null, "id = ?", filterEntity.getId());
        addCondition(conditions, parametersList,
                filterEntity.getName() != null && !filterEntity.getName().isBlank(),
                "nombre LIKE ?", "%" + filterEntity.getName() + "%");

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }

        return sql.toString();
    }

    private void addCondition(final List<String> conditions, final List<Object> parametersList, final boolean condition,
            final String clause, final Object value) {
        if (condition) {
            conditions.add(clause);
            parametersList.add(value);
        }
    }

    private CountryEntity mapCountry(ResultSet resultSet) throws SQLException {
        var entity = new CountryEntity();
        entity.setId(resultSet.getObject("id", UUID.class));
        entity.setName(resultSet.getString("nombre"));
        return entity;
    }

    @Override
    public CountryEntity findById(final UUID id) {
        var country = new CountryEntity();

        try (var preparedStatement = this.getConnection()
                .prepareStatement("SELECT id, nombre FROM Pais WHERE id = ?")) {

            preparedStatement.setObject(1, id);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    country = mapCountry(resultSet);
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

