package co.edu.uco.nose.data.dao.sql;

public class CountrySql {
	public static final String CREATE = """
			INSERT INTO Usuario (id, tipoIdentificacion,
				numeroIdentificacion, primerNombre, segundoNombre,
				primerApellido, segundoApellido, ciudadResidencia,
				correoElectronico, numeroTelefonoMovil,
				correoElectronicoConfirmado, numeroTelefonoMovilConfirmado)
			VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			""";
	
	public static final String FIND_ALL = """
			SELECT u.id,
				ti.id AS idTipoIdentificacion,
				ti.nombre AS nombreTipoIdentificacion,
				u.numeroIdentificacion,
				u.primerNombre,
				u.segundoNombre,
				u.primerApellido,
				u.segundoApellido,
				c.id AS idCiudadResidencia,
				c.nombre AS nombreCiudadResidencia,
				d.id AS idDepartamentoCiudadResidencia,
				d.nombre AS nombreDepartamentoCiudadResidencia,
				p.id AS idPaisDepartamentoCiudadResidencia,
				p.nombre AS nombrePaisDepartamentoCiudadResidencia,
				u.correoElectronico,
				u.numeroTelefonoMovil,
				u.correoElectronicoConfirmado,
				u.numeroTelefonoMovilConfirmado
			 FROM Usuario AS u
			 INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id
			 INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id
			 INNER JOIN Departamento AS d ON c.departamento = d.id
			 INNER JOIN Pais AS p ON d.pais = p.id
			""";
	
	public static final String FIND_BY_FILTER = """
				SELECT u.id,
				ti.id AS idTipoIdentificacion,
				ti.nombre AS nombreTipoIdentificacion,
				u.numeroIdentificacion,
				u.primerNombre,
				u.segundoNombre,
				u.primerApellido,
				u.segundoApellido,
				c.id AS idCiudadResidencia,
				c.nombre AS nombreCiudadResidencia,
				d.id AS idDepartamentoCiudadResidencia,
				d.nombre AS nombreDepartamentoCiudadResidencia,
				p.id AS idPaisDepartamentoCiudadResidencia,
				p.nombre AS nombrePaisDepartamentoCiudadResidencia,
				u.correoElectronico,
				u.numeroTelefonoMovil,
				u.correoElectronicoConfirmado,
				u.numeroTelefonoMovilConfirmado
			 FROM Usuario AS u
			 INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id
			 INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id
			 INNER JOIN Departamento AS d ON c.departamento = d.id
			 INNER JOIN Pais AS p ON d.pais = p.id
			WHERE (? IS NULL OR u.tipoIdentificacion = ?)
			  AND (? IS NULL OR u.numeroIdentificacion = ?)
			  AND (? IS NULL OR u.primerNombre LIKE CONCAT('%', ?, '%'))
			  AND (? IS NULL OR u.segundoNombre LIKE CONCAT('%', ?, '%'))
			  AND (? IS NULL OR u.primerApellido LIKE CONCAT('%', ?, '%'))
			  AND (? IS NULL OR u.segundoApellido LIKE CONCAT('%', ?, '%'))
			  AND (? IS NULL OR u.ciudadResidencia = ?)
			  AND (? IS NULL OR u.correoElectronico LIKE CONCAT('%', ?, '%'))
			  AND (? IS NULL OR u.numeroTelefonoMovil LIKE CONCAT('%', ?, '%'))
			  AND (? IS NULL OR u.correoElectronicoConfirmado = ?)
			  AND (? IS NULL OR u.numeroTelefonoMovilConfirmado = ?)
			""";
	
	public static final String FIND_BY_ID = """
			SELECT u.id,
				ti.id AS idTipoIdentificacion,
				ti.nombre AS nombreTipoIdentificacion,
				u.numeroIdentificacion,
				u.primerNombre,
				u.segundoNombre,
				u.primerApellido,
				u.segundoApellido,
				c.id AS idCiudadResidencia,
				c.nombre AS nombreCiudadResidencia,
				d.id AS idDepartamentoCiudadResidencia,
				d.nombre AS nombreDepartamentoCiudadResidencia,
				p.id AS idPaisDepartamentoCiudadResidencia,
				p.nombre AS nombrePaisDepartamentoCiudadResidencia,
				u.correoElectronico,
				u.numeroTelefonoMovil,
				u.correoElectronicoConfirmado,
				u.numeroTelefonoMovilConfirmado
			 FROM Usuario AS u
			 INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id
			 INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id
			 INNER JOIN Departamento AS d ON c.departamento = d.id
			 INNER JOIN Pais AS p ON d.pais = p.id
			 WHERE u.id = ?
     """;
	
	public static String UPDATE = """
			UPDATE Usuario
			SET		tipoIdentificacion = ?,
					numeroIdentificacion = ?,
					primerNombre = ? ,
					segundoNombre = ? ,
					primerApellido = ? ,
					segundoApellido = ?,
					ciudadResidencia = ?,
					correoElectronico = ?,
					numeroTelefonoMovil = ?,
					correoElectronicoConfirmado = ?,
					numeroTelefonoMovilConfirmado = ?,
			WHERE id = ?
				""";
	
	public static String DELETE = """
			DELETE
			FROM Usuario
			WHERE id = ?
			""";
}
