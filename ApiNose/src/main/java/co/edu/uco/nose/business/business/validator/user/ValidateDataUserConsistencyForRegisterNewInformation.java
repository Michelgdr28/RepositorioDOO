package co.edu.uco.nose.business.business.validator.user;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.business.business.rule.generics.StringLengthValueIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.nose.business.business.validator.Validator;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class ValidateDataUserConsistencyForRegisterNewInformation implements Validator {
	
	private static final Validator INSTANCE = new ValidateDataUserConsistencyForRegisterNewInformation();
	
	private ValidateDataUserConsistencyForRegisterNewInformation() {
	}
	
	public static void executeValidation(final Object... data) {
		INSTANCE.validate(data);
	}

	@Override
	public void validate(final Object... data) {
		
		// Validaciones del objeto data
		var userDomainData = (UserDomain) data[0];
		
		// Valid empty data
		validateEmptyData(userDomainData);
		
		// Valid data length
		validateDataLength(userDomainData);
		
		// Valid data format
		
		// Valid data valid range
				
	}
	
	private void validateEmptyData(final UserDomain data) {
		
		StringValueIsPresentRule.executeRule(data.getIdentificationNumber(), "numero de identificacion", true);
		StringValueIsPresentRule.executeRule(data.getFirstName(), "primer nombre", true);
		StringValueIsPresentRule.executeRule(data.getFirstLastName(), "primer apellido", true);
		
		// COntinue with other attributes validation
		// UUIDValueIsPresent
	}
	
	private void validateDataLength(final UserDomain data) {
		
		StringLengthValueIsValidRule.executeRule(data.getIdentificationNumber(), "numero de identificacion", 1, 50, true);
		StringLengthValueIsValidRule.executeRule(data.getFirstName(), "primer nombre", 1, 100, true);
		
		if (TextHelper.isEmptyWithTrim(data.getSecondName())) {
			StringLengthValueIsValidRule.executeRule(data.getSecondName(), "segundo nombre", 1, 100, true);
		}
		
		StringLengthValueIsValidRule.executeRule(data.getFirstLastName(), "primer apellido", 1, 100, true);
		
		if (TextHelper.isEmptyWithTrim(data.getSecondLastName())) {
			StringLengthValueIsValidRule.executeRule(data.getSecondLastName(), "segundo apellido", 1, 100, true);
		}
		
		// COntinue with other attributes validation
		// UUIDValueIsPresent
	}
	
}