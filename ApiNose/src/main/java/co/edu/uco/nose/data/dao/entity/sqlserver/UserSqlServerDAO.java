package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.UserEntity;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
import co.edu.uco.nose.entity.CityEntity;

public class UserSqlServerDAO extends SqlConnection implements UserDAO {

    public UserSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(UserEntity entity) {
        validateTransactionActive();
        String sql = "INSERT INTO [User] (id, id_number, first_name, second_name, last_name, second_last_name, email, phone_number, identification_type_id, city_id, email_confirmed, phone_confirmed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setObject(1, entity.getId());
            stmt.setString(2, entity.getIdNumber());
            stmt.setString(3, entity.getFirstName());
            stmt.setString(4, entity.getSecondName());
            stmt.setString(5, entity.getLastName());
            stmt.setString(6, entity.getSecondLastName());
            stmt.setString(7, entity.getEmail());
            stmt.setString(8, entity.getPhoneNumber());
            stmt.setObject(9, entity.getIdentificationType().getId());
            stmt.setObject(10, entity.getCity().getId());
            stmt.setBoolean(11, entity.isEmailConfirmed());
            stmt.setBoolean(12, entity.isPhoneConfirmed());
            stmt.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<UserEntity> finAll() {
        String sql = "SELECT * FROM [User]";
        List<UserEntity> users = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(mapResultSetToUserEntity(rs));
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
        return users;
    }

    @Override
    public List<UserEntity> findByfilter(UserEntity filterEntity) {
        StringBuilder sql = new StringBuilder("SELECT * FROM [User] WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (filterEntity.getId() != null) {
            sql.append(" AND id=?");
            params.add(filterEntity.getId());
        }
        if (!filterEntity.getIdNumber().isEmpty()) {
            sql.append(" AND id_number=?");
            params.add(filterEntity.getIdNumber());
        }
        if (!filterEntity.getFirstName().isEmpty()) {
            sql.append(" AND first_name=?");
            params.add(filterEntity.getFirstName());
        }
        if (!filterEntity.getSecondName().isEmpty()) {
            sql.append(" AND second_name=?");
            params.add(filterEntity.getSecondName());
        }
        if (!filterEntity.getLastName().isEmpty()) {
            sql.append(" AND last_name=?");
            params.add(filterEntity.getLastName());
        }
        if (!filterEntity.getSecondLastName().isEmpty()) {
            sql.append(" AND second_last_name=?");
            params.add(filterEntity.getSecondLastName());
        }
        if (!filterEntity.getEmail().isEmpty()) {
            sql.append(" AND email=?");
            params.add(filterEntity.getEmail());
        }
        if (!filterEntity.getPhoneNumber().isEmpty()) {
            sql.append(" AND phone_number=?");
            params.add(filterEntity.getPhoneNumber());
        }
        if (filterEntity.getIdentificationType() != null && filterEntity.getIdentificationType().getId() != null) {
            sql.append(" AND identification_type_id=?");
            params.add(filterEntity.getIdentificationType().getId());
        }
        if (filterEntity.getCity() != null && filterEntity.getCity().getId() != null) {
            sql.append(" AND city_id=?");
            params.add(filterEntity.getCity().getId());
        }
        if (filterEntity.isEmailConfirmed()) {
            sql.append(" AND email_confirmed=?");
            params.add(true);
        }
        if (filterEntity.isPhoneConfirmed()) {
            sql.append(" AND phone_confirmed=?");
            params.add(true);
        }
        List<UserEntity> users = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(mapResultSetToUserEntity(rs));
                }
            }
        } catch (SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
        return users;
    }

    @Override
    public UserEntity findById(UUID id) {
        String sql = "SELECT * FROM [User] WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setObject(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUserEntity(rs);
                }
            }
        } catch (SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
        return null;
    }

    @Override
    public void update(UserEntity entity) {
        validateTransactionActive();
        String sql = "UPDATE [User] SET id_number=?, first_name=?, second_name=?, last_name=?, second_last_name=?, email=?, phone_number=?, identification_type_id=?, city_id=?, email_confirmed=?, phone_confirmed=? WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, entity.getIdNumber());
            stmt.setString(2, entity.getFirstName());
            stmt.setString(3, entity.getSecondName());
            stmt.setString(4, entity.getLastName());
            stmt.setString(5, entity.getSecondLastName());
            stmt.setString(6, entity.getEmail());
            stmt.setString(7, entity.getPhoneNumber());
            stmt.setObject(8, entity.getIdentificationType().getId());
            stmt.setObject(9, entity.getCity().getId());
            stmt.setBoolean(10, entity.isEmailConfirmed());
            stmt.setBoolean(11, entity.isPhoneConfirmed());
            stmt.setObject(12, entity.getId());
            stmt.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(UUID id) {
        validateTransactionActive();
        String sql = "DELETE FROM [User] WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    private UserEntity mapResultSetToUserEntity(ResultSet rs) throws SQLException {
        UserEntity user = new UserEntity();
        user.setId((UUID) rs.getObject("id"));
        user.setIdNumber(rs.getString("id_number"));
        user.setFirstName(rs.getString("first_name"));
        user.setSecondName(rs.getString("second_name"));
        user.setLastName(rs.getString("last_name"));
        user.setSecondLastName(rs.getString("second_last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setIdentificationType(new IdentificationTypeEntity((UUID) rs.getObject("identification_type_id")));
        user.setCity(new CityEntity((UUID) rs.getObject("city_id")));
        user.setEmailConfirmed(rs.getBoolean("email_confirmed"));
        user.setPhoneConfirmed(rs.getBoolean("phone_confirmed"));
        return user;
    }

}
