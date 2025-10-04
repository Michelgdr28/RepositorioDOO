package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnum {

	USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada vacia","La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada esta vacia. Por favor intente de nuevo y si el problema persiste, contacte al administrador de la aplicacion"),
	TECHNICAL_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula","La conexion requerida para llevar a cabo la operacion contra la base de datos llego nula"),
	
	USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada","La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada esta cerrada. Por favor intente de nuevo y si el problema persiste, contacte al administrador de la aplicacion"),
	TECHNICAL_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada","La conexion requerida para llevar a cabo la operacion contra la base de datos esta cerrada."),
	
	USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Problema inesperado validando el estado de la conexion contra la fuente de datos deseado","Se ha presentado un problema inesperado tratando de validar el estado de la conexion requerida para llevar a cabo la operacion contra la fuente de datos deseada. Por favor intente de nuevo y si el problema persiste, contacte al administrador de la aplicacion"),
	TECHNICAL_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error inesperado validando si la conexion contra la base de datos estaba abierta","Se presento un error de tipo SQL.Exception al validar si la conexion contra la base de datos. Por favor valide la consola de errores para revisar con detalle el problema presentado");
	
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
