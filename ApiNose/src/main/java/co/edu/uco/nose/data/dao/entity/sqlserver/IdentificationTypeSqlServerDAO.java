package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public final class IdentificationTypeSqlServerDAO extends SqlConnection implements IdentificationTypeDAO {

    public IdentificationTypeSqlServerDAO(final java.sql.Connection connection) {
        super(connection);
    }

    @Override
    public List<IdentificationTypeEntity> finAll() {
        final String sql = "SELECT id, name FROM identification_type";
        final List<IdentificationTypeEntity> results = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(mapResultSetToIdentificationTypeEntity(rs));
            }
            return results;
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<IdentificationTypeEntity> findByfilter(final IdentificationTypeEntity filterEntity) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, name FROM identification_type WHERE 1=1");
        final List<Object> params = new ArrayList<>();
        final UUID defaultUUID = UUIDHelper.getUUIDHelper().getDefault();

        if (filterEntity != null) {
            if (filterEntity.getId() != null && !filterEntity.getId().equals(defaultUUID)) {
                sb.append(" AND id = ?");
                params.add(filterEntity.getId());
            }
            if (filterEntity.getName() != null && !filterEntity.getName().isBlank()) {
                sb.append(" AND name = ?");
                params.add(filterEntity.getName());
            }
        }

        final List<IdentificationTypeEntity> results = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapResultSetToIdentificationTypeEntity(rs));
                }
            }
            return results;
        } catch (final SQLException exeption) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public IdentificationTypeEntity findById(final UUID id) {
        final String sql = "SELECT id, name FROM identification_type WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToIdentificationTypeEntity(rs);
                }
            }
            return null;
        } catch (final SQLException ex) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    private IdentificationTypeEntity mapResultSetToIdentificationTypeEntity(final ResultSet rs) throws SQLException {
        final IdentificationTypeEntity entity = new IdentificationTypeEntity();
        try {
            Object idObj = rs.getObject("id");
            if (idObj instanceof UUID) {
                entity.setId((UUID) idObj);
            } else {
                final String idStr = rs.getString("id");
                entity.setId(idStr == null ? UUIDHelper.getUUIDHelper().getDefault() : UUID.fromString(idStr));
            }
        } catch (final Exception ex) {
            entity.setId(UUIDHelper.getUUIDHelper().getDefault());
        }
        entity.setName(rs.getString("name"));
        return entity;
    }
}

