package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnum {

	USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada vacia","La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada esta vacia. Por favor intente de nuevo y si el problema persiste, contacte al administrador de la aplicacion"),
	TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula","La conexion requerida para llevar a cabo la operacion contra la base de datos llego nula"),
	
	USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada","La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada esta cerrada. Por favor intente de nuevo y si el problema persiste, contacte al administrador de la aplicacion"),
	TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada","La conexion requerida para llevar a cabo la operacion contra la base de datos esta cerrada."),
	
	USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Problema inesperado validando el estado de la conexion contra la fuente de datos deseado","Se ha presentado un problema inesperado tratando de validar el estado de la conexion requerida para llevar a cabo la operacion contra la fuente de datos deseada. Por favor intente de nuevo y si el problema persiste, contacte al administrador de la aplicacion"),
	TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error inesperado validando si la conexion contra la base de datos estaba abierta","Se presento un error de tipo SQL.Exception al validar si la conexion contra la base de datos. Por favor valide la consola de errores para revisar con detalle el problema presentado"), 
	
	USER_ERROR_TRANSACTION_IS_NOT_STARTED ("Transaccion no iniciada", 
			"La transaccion requerida para llevar a cabo la operacion contra la fuente de informacion deseada no ha sido iniciada. "
			+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador de la aplicacion"),
	TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED ("Transaccion no iniciada",
			"La transaccion requerida para llevar a cabo la operacion contra la fuente de informacion deseada no ha sido iniciada. "
					+ "Por favor intente de nuevo y si el problema persiste, contacto al administrador de la aplicacion"),
	
	 USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error inesperado al validar el inicio de la transacción",
             "Se presentó un problema inesperado al validar el estado de la transacción. "
             + "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),
	 TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error técnico inesperado al validar el inicio de la transacción",
             "Se presentó un error técnico inesperado al intentar validar el estado de la transacción. "
             + "Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),
	 USER_ERROR_FACTORY_NOT_INITIALIZED(
			    "Factoría no inicializada","No se ha seleccionado o inicializado la factoría de DAOs requerida. Por favor configure la factoría e intente de nuevo."),
	TECHNICAL_ERROR_FACTORY_NOT_INITIALIZED(
			    "Factoría no inicializada ","No se encontró una factoría de DAOs válida para la operación solicitada."),
	
	USER_ERROR_SQL_TRANSACTION_BEGIN(
				    "No fue posible iniciar la transacción","No se pudo iniciar la transacción contra la fuente de datos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación."),
	TECHNICAL_ERROR_SQL_TRANSACTION_BEGIN(
				    "Error técnico iniciando transacción","Se produjo un error técnico al intentar deshabilitar autoCommit (iniciar transacción). Revise la traza para más detalles."),

	USER_ERROR_SQL_TRANSACTION_COMMIT(
				    "No fue posible confirmar la transacción",
				    "Ocurrió un problema al confirmar la transacción. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación."),
	TECHNICAL_ERROR_SQL_TRANSACTION_COMMIT(
				    "Error técnico confirmando transacción",
				    "Se produjo un error técnico al ejecutar commit() en la conexión. Revise la consola de errores para más detalles."),

	USER_ERROR_SQL_TRANSACTION_ROLLBACK(
				    "No fue posible revertir la transacción",
				    "Ocurrió un problema al revertir la transacción. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación."),
	TECHNICAL_ERROR_SQL_TRANSACTION_ROLLBACK(
				    "Error técnico revirtiendo transacción",
				    "Se produjo un error técnico al ejecutar rollback() en la conexión. Revise la consola de errores para más detalles."),

	USER_ERROR_SQL_CONNECTION_CLOSE(
				    "No fue posible cerrar la conexión",
				    "Ocurrió un problema al cerrar la conexión con la fuente de datos. Por favor contacte al administrador de la aplicación."),
	TECHNICAL_ERROR_SQL_CONNECTION_CLOSE(
				    "Error técnico cerrando la conexión",
				    "Se produjo un error técnico al intentar cerrar la conexión. Revise la consola de errores para más detalles."),		

	USER_ERROR_USER_CREATE("Error creando usuario","Se ha presentado un problema tratando de registrar la informacion del nuevo usuario. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_CREATE("Error creando usuario","Se ha presentado un problema al tratar de ejecutar el proceso de creacion de un usuario"),

	USER_ERROR_USER_CREATE_UNEXPECTED("Error inesperado creando usuario",
	        "Se ha presentado un problema INESPERADO tratando de crear un nuevo usuario. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_CREATE_UNEXPECTED("Error inesperado creando usuario","Se ha presentado un problema inesperado al tratar de ejecutar el proceso de creación de usuario"),

	USER_ERROR_USER_FIND_ALL("Error consultando usuarios",
	        "Se ha presentado un problema tratando de consultar la informacion de los usuarios. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_FIND_ALL("Error consultando usuarios",
	        "Se ha presentado un problema al tratar de ejecutar el proceso de consulta de usuarios. "
	      + "Por favor verifique que la informacion este correcta"),

	USER_ERROR_USER_FIND_ALL_UNEXPECTED("Error inesperado consultando usuarios",
	        "Se ha presentado un problema INESPERADO tratando de consultar la informacion de los usuarios. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_FIND_ALL_UNEXPECTED("Error inesperado consultando usuarios",
	        "Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de usuarios"),

	USER_ERROR_USER_FIND_BY_FILTER("Error consultando usuarios con filtro",
	        "Se ha presentado un problema tratando de consultar la informacion de los usuarios. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_FIND_BY_FILTER("Error consultando usuarios con filtro",
	        "Se ha presentado un problema al tratar de ejecutar el proceso de consulta de usuarios con filtro. "
	      + "Por favor verifique que la informacion este correcta"),

	USER_ERROR_USER_FIND_BY_FILTER_UNEXPECTED("Error inesperadoconsultando usuarios con filtro",
	        "Se ha presentado un problema inesperado tratando de consultar la informacion de los usuarios. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_FIND_BY_FILTER_UNEXPECTED("Error inesperado consultando usuarios con filtro",
	        "Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de usuarios con filtro"),

	USER_ERROR_USER_FIND_BY_ID("Error consultando usuario por ID",
	        "Se ha presentado un problema tratando de consultar la informacion del usuario deseado. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_FIND_BY_ID("Error consultando usuario por ID",
	        "Se ha presentado un problema al tratar de ejecutar el proceso de consulta del usuario deseado. "
	      + "Por favor verifique que la informacion este correcta"),

	USER_ERROR_USER_FIND_BY_ID_UNEXPECTED("Error inesperado consultando usuario por ID",
	        "Se ha presentado un problema inesperado tratando de consultar la informacion del usuario deseado. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_FIND_BY_ID_UNEXPECTED("Error inesperado consultando usuario por ID",
	        "Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta del usuario deseado"),

	USER_ERROR_USER_UPDATE("Error modificando usuario",
	        "Se ha presentado un problema tratando de modificar la informacion del usuario. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_UPDATE("Error modificando usuario",
	        "Se ha presentado un problema al tratar de ejecutar el proceso de modificacion de un usuario"),

	USER_ERROR_USER_UPDATE_UNEXPECTED("Error inesperado modificando usuario",
	        "Se ha presentado un problema inesperado tratando de modificar la informacion del usuario. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_UPDATE_UNEXPECTED("Error inesperado modificando usuario",
	        "Se ha presentado un problema inesperado al tratar de ejecutar el proceso de modificacion de un usuario"),

	USER_ERROR_USER_DELETE("Error eliminando usuario",
	        "Se ha presentado un problema tratando de eliminar la informacion del usuario. "
	      + "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_USER_DELETE("Error eliminando usuario",
	        "Se ha presentado un problema al tratar de ejecutar el proceso de eliminacion del usuario"),

	USER_ERROR_USER_DELETE_UNEXPECTED("Error inesperado eliminando usuario",
	        "Se ha presentado un problema inesperado tratando de eliminar la informacion del usuario"),
	TECHNICAL_ERROR_USER_DELETE_UNEXPECTED("Error inesperado eliminando usuario",
	        "Se ha presentado un problema inesperado al tratar de ejecutar el proceso de eliminacion del usuario"),
		
	USER_ERROR_IDENTIFICATIONTYPE_FIND_ALL("Se ha presentado un problema tratando de consultar la información de los tipos de identificación", 
	   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema"),
	TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_ALL("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de tipos de identificación. ",
	   "Por favor verifique que la información esté correcta."),
	
	USER_ERROR_IDENTIFICATIONTYPE_FIND_ALL_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de los tipos de identificación. ",
	  "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_ALL_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de tipos de identificación. ",
	  "Por favor verifique que la información esté correcta."),

	USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER("Se ha presentado un problema tratando de consultar la información de los tipos de identificación aplicando filtros. ",
	   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de tipos de identificación con filtros. ",
	  "Por favor verifique que la información esté correcta."),
	USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de los tipos de identificación aplicando filtros. ",
	  "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_FILTER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de tipos de identificación con filtros. ",
	  "Por favor verifique que la información esté correcta."),

	USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID("Se ha presentado un problema tratando de consultar la información del tipo de identificación deseado. ",
	  "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID("Se ha presentado un problema al tratar de ejecutar el proceso de consulta del tipo de identificación deseado. ",
	   "Por favor verifique que la información esté correcta."),
	USER_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información del tipo de identificación deseado. ",
	   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_IDENTIFICATIONTYPE_FIND_BY_ID_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta del tipo de identificación deseado. ",
	   "Por favor verifique que la información esté correcta."),
	
	USER_ERROR_DEPARTMENT_FIND_ALL("Se ha presentado un problema tratando de consultar la información de los departamentos. ",
			  "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_DEPARTMENT_FIND_ALL("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de departamentos. ",
			  "Por favor verifique que la información esté correcta."),
			
	USER_ERROR_DEPARTMENT_FIND_ALL_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de los departamentos. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_DEPARTMENT_FIND_ALL_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de departamentos. ",
			   "Por favor verifique que la información esté correcta."),

	USER_ERROR_DEPARTMENT_FIND_BY_FILTER("Se ha presentado un problema tratando de consultar la información de los departamentos aplicando filtros. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de departamentos con filtros. ",
			   "Por favor verifique que la información esté correcta."),
	USER_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de los departamentos aplicando filtros. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_DEPARTMENT_FIND_BY_FILTER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de departamentos con filtros. ",
			   "Por favor verifique que la información esté correcta."),

	USER_ERROR_DEPARTMENT_FIND_BY_ID("Se ha presentado un problema tratando de consultar la información del departamento deseado. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_DEPARTMENT_FIND_BY_ID("Se ha presentado un problema al tratar de ejecutar el proceso de consulta del departamento deseado. ",
			   "Por favor verifique que la información esté correcta."),
	USER_ERROR_DEPARTMENT_FIND_BY_ID_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información del departamento deseado. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_DEPARTMENT_FIND_BY_ID_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta del departamento deseado. ",
			   "Por favor verifique que la información esté correcta."),
	
	USER_ERROR_COUNTRY_FIND_ALL("Se ha presentado un problema tratando de consultar la información de los países. ",
	  "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_COUNTRY_FIND_ALL("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de países. ",
	   "Por favor verifique que la información esté correcta."),
	USER_ERROR_COUNTRY_FIND_ALL_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de los países. ",
	   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_COUNTRY_FIND_ALL_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de países. ",
	  "Por favor verifique que la información esté correcta."),

	USER_ERROR_COUNTRY_FIND_BY_FILTER("Se ha presentado un problema tratando de consultar la información de los países aplicando filtros. ",
	  "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_COUNTRY_FIND_BY_FILTER("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de países con filtros. ",
	  "Por favor verifique que la información esté correcta."),
	USER_ERROR_COUNTRY_FIND_BY_FILTER_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de los países aplicando filtros. ",
	  "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_COUNTRY_FIND_BY_FILTER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de países con filtros. ",
	   "Por favor verifique que la información esté correcta."),

	USER_ERROR_COUNTRY_FIND_BY_ID("Se ha presentado un problema tratando de consultar la información del país deseado. ",
	   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_COUNTRY_FIND_BY_ID("Se ha presentado un problema al tratar de ejecutar el proceso de consulta del país deseado. ",
	   "Por favor verifique que la información esté correcta."),
	USER_ERROR_COUNTRY_FIND_BY_ID_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información del país deseado. ",
	   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_COUNTRY_FIND_BY_ID_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta del país deseado. ",
	   "Por favor verifique que la información esté correcta."),

	USER_ERROR_CITY_FIND_ALL("Se ha presentado un problema tratando de consultar la información de las ciudades. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_CITY_FIND_ALL("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de ciudades. ",
			   "Por favor verifique que la información esté correcta."),
	USER_ERROR_CITY_FIND_ALL_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de las ciudades. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_CITY_FIND_ALL_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de ciudades. ",
			   "Por favor verifique que la información esté correcta."),

	USER_ERROR_CITY_FIND_BY_FILTER("Se ha presentado un problema tratando de consultar la información de las ciudades aplicando filtros. ",
			  "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_CITY_FIND_BY_FILTER("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de ciudades con filtros. ",
			   "Por favor verifique que la información esté correcta."),
	USER_ERROR_CITY_FIND_BY_FILTER_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de las ciudades aplicando filtros. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_CITY_FIND_BY_FILTER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de ciudades con filtros. ",
			   "Por favor verifique que la información esté correcta."),

	USER_ERROR_CITY_FIND_BY_ID("Se ha presentado un problema tratando de consultar la información de la ciudad deseada. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_CITY_FIND_BY_ID("Se ha presentado un problema al tratar de ejecutar el proceso de consulta de la ciudad deseada. ",
			   "Por favor verifique que la información esté correcta."),
	USER_ERROR_CITY_FIND_BY_ID_UNEXPECTED("Se ha presentado un problema inesperado tratando de consultar la información de la ciudad deseada. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_CITY_FIND_BY_ID_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de consulta de la ciudad deseada. ",
			   "Por favor verifique que la información esté correcta."),
	
	USER_ERROR_CITY_MAPPER("Se ha presentado un problema tratando de mapear la información de la ciudad desde los datos obtenidos. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_CITY_MAPPER("Se ha presentado un problema al tratar de ejecutar el proceso de mapeo de datos de la ciudad. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),		
	USER_ERROR_CITY_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado tratando de mapear la información de la ciudad. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_CITY_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de mapeo de datos de la ciudad. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),
	
	USER_ERROR_DEPARTMENT_MAPPER("Se ha presentado un problema tratando de mapear la información del departamento desde los datos obtenidos. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_DEPARTMENT_MAPPER("Se ha presentado un problema al tratar de ejecutar el proceso de mapeo de datos del departamento. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),			
	USER_ERROR_DEPARTMENT_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado tratando de mapear la información del departamento. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_DEPARTMENT_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de mapeo de datos del departamento. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),
	
	USER_ERROR_COUNTRY_MAPPER("Se ha presentado un problema tratando de mapear la información del país desde los datos obtenidos. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_COUNTRY_MAPPER("Se ha presentado un problema al tratar de ejecutar el proceso de mapeo de datos del país. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),
			
	USER_ERROR_COUNTRY_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado tratando de mapear la información del país. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_COUNTRY_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de mapeo de datos del país. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),

	USER_ERROR_IDENTIFICATION_TYPE_MAPPER("Se ha presentado un problema tratando de mapear la información del tipo de identificación desde los datos obtenidos. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_IDENTIFICATION_TYPE_MAPPER("Se ha presentado un problema al tratar de ejecutar el proceso de mapeo de datos del tipo de identificación. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),
			
	USER_ERROR_IDENTIFICATION_TYPE_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado tratando de mapear la información del tipo de identificación. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_IDENTIFICATION_TYPE_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de mapeo de datos del tipo de identificación. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),

	USER_ERROR_USER_MAPPER("Se ha presentado un problema tratando de mapear la información del usuario desde los datos obtenidos. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_USER_MAPPER("Se ha presentado un problema al tratar de ejecutar el proceso de mapeo de datos del usuario. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),
			
	USER_ERROR_USER_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado tratando de mapear la información del usuario. ",
			   "Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema."),
	TECHNICAL_ERROR_USER_MAPPER_UNEXPECTED("Se ha presentado un problema inesperado al tratar de ejecutar el proceso de mapeo de datos del usuario. ",
			   "Por favor verifique que la información proveniente de la base de datos sea correcta."),
	USER_ERROR_USER_NOT_FOUND("",""),
	TECHNICAL_ERROR_USER_NOT_FOUND("",""),
	USER_ERROR_CONFIRM_MOBILE("",""),
	TECHNICAL_ERROR_CONFIRM_MOBILE("",""),
	USER_ERROR_CONFIRM_EMAIL("",""), 
	TECHNICAL_ERROR_CONFIRM_EMAIL("",""), 
	USER_ERROR_SEND_MOBILE_CONFIRMATION("",""), 
	TECHNICAL_ERROR_SEND_MOBILE_CONFIRMATION("",""), 
	USER_ERROR_SEND_EMAIL_CONFIRMATION("",""), 
	TECHNICAL_ERROR_SEND_EMAIL_CONFIRMATION("",""),
	
	

	;

	private String title;
	private String content;
	
	private MessagesEnum(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = TextHelper.getDefaultWithTrim(title);
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = TextHelper.getDefaultWithTrim(content);
		
	}
	
	
}
