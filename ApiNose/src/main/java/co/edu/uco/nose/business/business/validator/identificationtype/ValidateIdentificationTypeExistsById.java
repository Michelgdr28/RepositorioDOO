package co.edu.uco.nose.business.business.validator.identificationtype;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.business.business.rule.identificationType.IdentificationTypeExistsByIdRule;
import co.edu.uco.nose.business.business.validator.Validator;

public class ValidateIdentificationTypeExistsById implements Validator {
	
	private static final Validator INSTANCE = new ValidateIdentificationTypeExistsById();
	
	private ValidateIdentificationTypeExistsById() {
	}
	
	public static void executeValidation(final Object... data) {
		INSTANCE.validate(data);
	}

	@Override
	public void validate(final Object... data) {
		IdentificationTypeExistsByIdRule.executeRule(data);
	}

}