package co.edu.uco.nose.business.domain;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class UserDomain extends Domain {
    private UUID identificationTypeId;
    private String identificationNumber;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private UUID residenceCityId;
    private String email;
    private String mobilePhone;
    private boolean emailConfirmed;
    private boolean mobilePhoneConfirmed;

    public UserDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setIdentificationTypeId(UUIDHelper.getUUIDHelper().getDefault());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setResidenceCityId(UUIDHelper.getUUIDHelper().getDefault());
        setEmail(TextHelper.getDefault());
        setMobilePhone(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobilePhoneConfirmed(false);
    }

    public UserDomain(UUID id, UUID identificationTypeId, String identificationNumber,
                      String firstName, String secondName, String firstLastName, String secondLastName,
                      UUID residenceCityId, String email, String mobilePhone,
                      boolean emailConfirmed, boolean mobilePhoneConfirmed) {
        super(id);
        setIdentificationTypeId(identificationTypeId);
        setIdentificationNumber(identificationNumber);
        setFirstName(firstName);
        setSecondName(secondName);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
        setResidenceCityId(residenceCityId);
        setEmail(email);
        setMobilePhone(mobilePhone);
        setEmailConfirmed(emailConfirmed);
        setMobilePhoneConfirmed(mobilePhoneConfirmed);
    }

    public UUID getIdentificationTypeId() { 
    	return identificationTypeId;
    	}
    public void setIdentificationTypeId(UUID identificationTypeId) { 
    	this.identificationTypeId = UUIDHelper.getUUIDHelper().getDefault(identificationTypeId);
    	}

    public String getIdentificationNumber() {
    	return identificationNumber;
    	}
    public void setIdentificationNumber(String identificationNumber) {
    	this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
    	}

    public String getFirstName() { 
    	return firstName;
    	}
    public void setFirstName(String firstName) { 
    	this.firstName = TextHelper.getDefaultWithTrim(firstName);
    	}

    public String getSecondName() { 
    	return secondName;
    	}
    public void setSecondName(String secondName) {
    	this.secondName = TextHelper.getDefaultWithTrim(secondName);
    	}

    public String getFirstLastName() {
    	return firstLastName;
    	}
    public void setFirstLastName(String firstLastName) { 
    	this.firstLastName = TextHelper.getDefaultWithTrim(firstLastName);
    	}

    public String getSecondLastName() { 
    	return secondLastName; 
    	}
    public void setSecondLastName(String secondLastName) {
    	this.secondLastName = TextHelper.getDefaultWithTrim(secondLastName); 
    	}

    public UUID getResidenceCityId() {
    	return residenceCityId;
    	}
    public void setResidenceCityId(UUID residenceCityId) {
    	this.residenceCityId = UUIDHelper.getUUIDHelper().getDefault(residenceCityId);
    	}

    public String getEmail() { 
    	return email;
    	}
    public void setEmail(String email) { 
    	this.email = TextHelper.getDefaultWithTrim(email);
    	}

    public String getMobilePhone() { 
    	return mobilePhone; 
    	}
    public void setMobilePhone(String mobilePhone) {
    	this.mobilePhone = TextHelper.getDefaultWithTrim(mobilePhone); 
    	}

    public boolean isEmailConfirmed() { 
    	return emailConfirmed; 
    	}
    public void setEmailConfirmed(boolean emailConfirmed) {
    	this.emailConfirmed = emailConfirmed;
    	}

    public boolean isMobilePhoneConfirmed() { 
    	return mobilePhoneConfirmed;
    	}
    public void setMobilePhoneConfirmed(boolean mobilePhoneConfirmed) { 
    	this.mobilePhoneConfirmed = mobilePhoneConfirmed; 
    	}
}

