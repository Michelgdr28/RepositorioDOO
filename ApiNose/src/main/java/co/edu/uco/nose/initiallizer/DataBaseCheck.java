package co.edu.uco.nose.initiallizer;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataBaseCheck {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void testConnection() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("✅ Conexión exitosa a la base de datos");
        } catch (Exception e) {
            System.err.println("❌ Error de conexión a la base de datos:");
            e.printStackTrace();
        }
    }
}
