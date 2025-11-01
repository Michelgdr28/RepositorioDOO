package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class StringValueIsPresentRule implements Rule {
	
	private static final Rule INSTANCE = new StringValueIsPresentRule();
	
	private StringValueIsPresentRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		INSTANCE.execute(data);
	}

	@Override
	public void execute(final Object... data) {
		
		if (ObjectHelper.isNull(data)) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada...";
			var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla de StringValueIsPresentRule.";
			throw NoseException.create(userMessage,	technicalMessage);
		}
		
		if (data.length < 3) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada...";
			var technicalMessage = "Se requerian tres parametros y llegó una cantidad menor a esta ejecutar la regla de StringValueIsPresentRule";
			throw NoseException.create(userMessage,	technicalMessage);
		}
		
		var stringData = (String) data[0];
		var dataName = (String) data[1];
		boolean mustApplyTrim = (boolean) data[2];
		
		if ((mustApplyTrim) 
			? TextHelper.isEmptyWithTrim(stringData) 
					: TextHelper.isEmpty(stringData)) {
			var userMessage = "El dato [".concat(dataName).concat("] es requerido para llevar a cabo la operacion deseada.");
			var technicalMessage = "La regla StringValueIsPresentRule falló porque el dato [".concat(dataName).concat("] requerido para llevar a cabo la operacion esta vacio");
			throw NoseException.create(userMessage,	technicalMessage);
		};
	}

}