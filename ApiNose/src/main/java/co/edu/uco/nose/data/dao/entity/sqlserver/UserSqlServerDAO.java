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
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.DepartmentEntity;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

public final class UserSqlServerDAO extends SqlConnection implements UserDAO {

    public UserSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
        final var sql = new StringBuilder();
        sql.append("INSERT INTO Usuario (id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, ciudadResidencia, correoElectronico, numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getIdentificationType().getId());
            preparedStatement.setString(3, entity.getIdNumber());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getSecondName());
            preparedStatement.setString(6, entity.getLastName());
            preparedStatement.setString(7, entity.getSecondLastName());
            preparedStatement.setObject(8, entity.getCity().getId());
            preparedStatement.setString(9, entity.getEmail());
            preparedStatement.setString(10, entity.getPhoneNumber());
            preparedStatement.setBoolean(11, entity.isEmailConfirmed());
            preparedStatement.setBoolean(12, entity.isPhoneConfirmed());
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_CREATE.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_CREATE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_CREATE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_CREATE_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
        final var sql = new StringBuilder();
        sql.append("UPDATE Usuario ");
        sql.append("SET tipoIdentificacion = ?, ");
        sql.append("numeroIdentificacion = ?, ");
        sql.append("primerNombre = ?, ");
        sql.append("segundoNombre = ?, ");
        sql.append("primerApellido = ?, ");
        sql.append("segundoApellido = ?, ");
        sql.append("ciudadResidencia = ?, ");
        sql.append("correoElectronico = ?, ");
        sql.append("numeroTelefonoMovil = ?, ");
        sql.append("correoElectronicoConfirmado = ?, ");
        sql.append("numeroTelefonoMovilConfirmado = ? ");
        sql.append("WHERE id = ?");
        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getIdentificationType().getId());
            preparedStatement.setString(2, entity.getIdNumber());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getSecondName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setString(6, entity.getSecondLastName());
            preparedStatement.setObject(7, entity.getCity().getId());
            preparedStatement.setString(8, entity.getEmail());
            preparedStatement.setString(9, entity.getPhoneNumber());
            preparedStatement.setBoolean(10, entity.isEmailConfirmed());
            preparedStatement.setBoolean(11, entity.isPhoneConfirmed());
            preparedStatement.setObject(12, entity.getId());
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_UPDATE.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_UPDATE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_UPDATE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_UPDATE_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
        final var sql = new StringBuilder();
        sql.append("DELETE FROM Usuario"); 
        sql.append("WHERE id = ?");
        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_DELETE.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_DELETE.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_DELETE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_DELETE_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public UserEntity findById(final UUID id) {
        return findByfilter(new UserEntity(id)).stream().findFirst().orElse(new UserEntity());
    }

    @Override
    public List<UserEntity> findAll() {
        return findByfilter(new UserEntity());
    }

    @Override
    public List<UserEntity> findByfilter(UserEntity filterEntity) {
        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);
        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }
            return executeSentenceFindByFilter(preparedStatement);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_FIND_BY_FILTER.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_FIND_BY_FILTER_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_FIND_BY_FILTER_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    private String createSentenceFindByFilter(final UserEntity filterEntity, final List<Object> parametersList) {
        final var sql = new StringBuilder();
        sql.append("SELECT u.id, ");
        sql.append("ti.id AS idTipoIdentificacion, ");
        sql.append("ti.nombre AS nombreTipoIdentificacion, ");
        sql.append("u.numeroIdentificacion, ");
        sql.append("u.primerNombre, ");
        sql.append("u.segundoNombre, ");
        sql.append("u.primerApellido, ");
        sql.append("u.segundoApellido, ");
        sql.append("c.id AS idCiudadResidencia, ");
        sql.append("c.nombre AS nombreCiudadResidencia, ");
        sql.append("d.id AS idDepartamentoCiudadResidencia, ");
        sql.append("d.nombre AS nombreDepartamentoCiudadResidencia, ");
        sql.append("p.id AS idPaisDepartamentoCiudadResidencia, ");
        sql.append("p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
        sql.append("u.correoElectronico, ");
        sql.append("u.numeroTelefonoMovil, ");
        sql.append("u.correoElectronicoConfirmado, ");
        sql.append("u.numeroTelefonoMovilConfirmado ");
        sql.append("FROM Usuario AS u ");
        sql.append("INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id ");
        sql.append("INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id ");
        sql.append("INNER JOIN Departamento AS d ON c.departamento = d.id ");
        sql.append("INNER JOIN Pais AS p ON d.pais = p.id ");
        createWhereClauseFindByFilter(sql, parametersList, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList, final UserEntity filterEntity) {
        var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new UserEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()), "u.id = ?", filterEntityValidated.getId());
        addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getIdentificationType().getId()), "u.tipoIdentificacion = ?", filterEntityValidated.getIdentificationType().getId());
        addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getIdNumber()), "u.numeroIdentificacion = ?", filterEntityValidated.getIdNumber());
        addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getFirstName()), "u.primerNombre = ?", filterEntityValidated.getFirstName());
        addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getSecondName()), "u.segundoNombre = ?", filterEntityValidated.getSecondName());
        addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCity().getId()), "u.ciudadResidencia = ?", filterEntityValidated.getCity().getId());
        addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getEmail()), "u.correoElectronico = ?", filterEntityValidated.getEmail());
        addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getPhoneNumber()), "u.numeroTelefonoMovil = ?", filterEntityValidated.getPhoneNumber());
        addCondition(conditions, parametersList, !filterEntityValidated.isEmailConfirmedIsDefaultValue(), "u.correoElectronicoConfirmado = ?", filterEntityValidated.isEmailConfirmed());
        addCondition(conditions, parametersList, !filterEntityValidated.isPhoneNumberConfirmedIsDefaultValue(), "u.numeroTelefonoMovilConfirmado = ?", filterEntityValidated.isPhoneConfirmed());

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parametersList, final boolean condition, final String clause, final Object value) {
        if (condition) {
            conditions.add(clause);
            parametersList.add(value);
        }
    }

    private List<UserEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
        var listUser = new ArrayList<UserEntity>();
        try (var resultset = preparedStatement.executeQuery()) {
            while (resultset.next()) {
                var identificationType = new IdentificationTypeEntity();
                identificationType.setId(UUIDHelper.getUUIDHelper().getFromString(resultset.getString("idTipoIdentificacion")));
                identificationType.setName(resultset.getString("nombreTipoIdentificacion"));

                var country = new CountryEntity();
                country.setId(UUIDHelper.getUUIDHelper().getFromString(resultset.getString("idPaisDepartamentoCiudadResidencia")));
                country.setName(resultset.getString("nombrePaisDepartamentoCiudadResidencia"));

                var department = new DepartmentEntity();
                department.setCountry(country);
                department.setId(UUIDHelper.getUUIDHelper().getFromString(resultset.getString("idDepartamentoCiudadResidencia")));
                department.setName(resultset.getString("nombreDepartamentoCiudadResidencia"));

                var city = new CityEntity();
                city.setDepartment(department);
                city.setId(UUIDHelper.getUUIDHelper().getFromString(resultset.getString("idCiudadResidencia")));
                city.setName(resultset.getString("nombreCiudadResidencia"));

                var user = new UserEntity();
                user.setId(UUIDHelper.getUUIDHelper().getFromString(resultset.getString("id")));
                user.setIdentificationType(identificationType);
                user.setFirstName(resultset.getString("primerNombre"));
                user.setSecondName(resultset.getString("segundoNombre"));
                user.setLastName(resultset.getString("primerApellido"));
                user.setSecondLastName(resultset.getString("segundoApellido"));
                user.setCity(city);
                user.setEmail(resultset.getString("correoElectronico"));
                user.setPhoneNumber(resultset.getString("numeroTelefonoMovil"));
                user.setEmailConfirmed(resultset.getBoolean("correoElectronicoConfirmado"));
                user.setPhoneConfirmed(resultset.getBoolean("numeroTelefonoMovilConfirmado"));

                listUser.add(user);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_FIND_BY_FILTER.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_USER_FIND_BY_FILTER_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_USER_FIND_BY_FILTER_UNEXPECTED.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        return listUser;
    }
}



