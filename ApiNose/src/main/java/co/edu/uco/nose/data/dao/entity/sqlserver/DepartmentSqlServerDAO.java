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
import co.edu.uco.nose.data.dao.entity.DepartmentDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.DepartmentEntity;

public final class DepartmentSqlServerDAO extends SqlConnection implements DepartmentDAO {

    public DepartmentSqlServerDAO(final java.sql.Connection connection) {
        super(connection);
    }

    @Override
    public List<DepartmentEntity> finAll() {
        final String sql = "SELECT id, country_id, name FROM department";
        final List<DepartmentEntity> results = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(mapResultSetToDepartmentEntity(rs));
            }
            return results;
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<DepartmentEntity> findByfilter(final DepartmentEntity filterEntity) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, country_id, name FROM department WHERE 1=1");
        final List<Object> params = new ArrayList<>();
        final UUID defaultUUID = UUIDHelper.getUUIDHelper().getDefault();

        if (filterEntity != null) {
            if (filterEntity.getId() != null && !filterEntity.getId().equals(defaultUUID)) {
                sb.append(" AND id = ?");
                params.add(filterEntity.getId());
            }
            if (filterEntity.getCountryId() != null && !filterEntity.getCountryId().equals(defaultUUID)) {
                sb.append(" AND country_id = ?");
                params.add(filterEntity.getCountryId());
            }
            if (filterEntity.getName() != null && !filterEntity.getName().isBlank()) {
                sb.append(" AND name = ?");
                params.add(filterEntity.getName());
            }
        }

        final List<DepartmentEntity> results = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapResultSetToDepartmentEntity(rs));
                }
            }
            return results;
        } catch (final SQLException ex) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = "Error finding departments by filter: " + ex.getMessage();
            throw NoseException.create(userMessage, technicalMessage, ex);
        }
    }

    @Override
    public DepartmentEntity findById(final UUID id) {
        final String sql = "SELECT id, country_id, name FROM department WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDepartmentEntity(rs);
                }
            }
            return null;
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    private DepartmentEntity mapResultSetToDepartmentEntity(final ResultSet rs) throws SQLException {
        final DepartmentEntity entity = new DepartmentEntity();
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

        try {
            Object countryObj = rs.getObject("country_id");
            if (countryObj instanceof UUID) {
                entity.setCountryId((UUID) countryObj);
            } else {
                final String cStr = rs.getString("country_id");
                entity.setCountryId(cStr == null ? UUIDHelper.getUUIDHelper().getDefault() : UUID.fromString(cStr));
            }
        } catch (final Exception ex) {
            entity.setCountryId(UUIDHelper.getUUIDHelper().getDefault());
        }

        entity.setName(rs.getString("name"));
        return entity;
    }
}

