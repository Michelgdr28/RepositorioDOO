package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CityEntity;

public final class CitySqlServerDAO extends SqlConnection implements CityDAO {

    public CitySqlServerDAO(final java.sql.Connection connection) {
        super(connection);
    }

    @Override
    public List<CityEntity> finAll() {
        final String sql = "SELECT id, department_id, name FROM city";
        final List<CityEntity> results = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(mapResultSetToCityEntity(rs));
            }
            return results;
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<CityEntity> findByfilter(final CityEntity filterEntity) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, department_id, name FROM city WHERE 1=1");

        final List<Object> params = new ArrayList<>();

        if (filterEntity != null) {
            final UUID defaultUUID = UUIDHelper.getUUIDHelper().getDefault();
            if (filterEntity.getId() != null && !filterEntity.getId().equals(defaultUUID)) {
                sb.append(" AND id = ?");
                params.add(filterEntity.getId());
            }

            if (filterEntity.getDepartmentId() != null && !filterEntity.getDepartmentId().equals(defaultUUID)) {
                sb.append(" AND department_id = ?");
                params.add(filterEntity.getDepartment());
            }

            if (!TextHelper.isEmpty(filterEntity.getName())) {
                sb.append(" AND name = ?");
                params.add(filterEntity.getName());
            }
        }

        final List<CityEntity> results = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapResultSetToCityEntity(rs));
                }
            }

            return results;
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public CityEntity findById(final UUID id) {
        final String sql = "SELECT id, department_id, name FROM city WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCityEntity(rs);
                }
            }

            return null;
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_STATEMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_STATEMENT.getContent()
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    private CityEntity mapResultSetToCityEntity(final ResultSet rs) throws SQLException {
        final CityEntity entity = new CityEntity();

        try {
            final Object idObj = rs.getObject("id");
            if (idObj instanceof UUID) {
                entity.setId((UUID) idObj);
            } else {
                final String idStr = rs.getString("id");
                entity.setId(idStr == null ? UUIDHelper.getUUIDHelper().getDefault() : UUID.fromString(idStr));
            }
        } catch (final Exception exception) {
            entity.setId(UUIDHelper.getUUIDHelper().getDefault());
        }

        try {
            final Object deptObj = rs.getObject("department_id");
            if (deptObj instanceof UUID) {
                entity.setDepartmentId((UUID) deptObj);
            } else {
                final String deptStr = rs.getString("department_id");
                entity.setDepartmentId(deptStr == null ? UUIDHelper.getUUIDHelper().getDefault() : UUID.fromString(deptStr));
            }
        } catch (final Exception ex) {
            entity.setDepartmentId(UUIDHelper.getUUIDHelper().getDefault());
        }

    
        entity.setName(rs.getString("name"));

        return entity;
    }
}

