package co.edu.uco.nose.dto;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class UserDTO {
    private UUID id;
    private IdentificationTypeDTO identificationType;
    private String identificationNumber;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private CityDTO residenceCity;
    private String email;
    private String mobilePhone;
    private boolean emailConfirmed;
    private boolean mobilePhoneConfirmed;

    public UserDTO() {
        this.id = UUIDHelper.getUUIDHelper().getDefault();
        this.identificationType = new IdentificationTypeDTO();
        this.identificationNumber = TextHelper.getDefault();
        this.firstName = TextHelper.getDefault();
        this.secondName = TextHelper.getDefault();
        this.firstLastName = TextHelper.getDefault();
        this.secondLastName = TextHelper.getDefault();
        this.residenceCity = new CityDTO();
        this.email = TextHelper.getDefault();
        this.mobilePhone = TextHelper.getDefault();
        this.emailConfirmed = false;
        this.mobilePhoneConfirmed = false;
    }

    public UserDTO(final UUID id,
                   final IdentificationTypeDTO identificationType,
                   final String identificationNumber,
                   final String firstName,
                   final String secondName,
                   final String firstLastName,
                   final String secondLastName,
                   final CityDTO residenceCity,
                   final String email,
                   final String mobilePhone,
                   final boolean emailConfirmed,
                   final boolean mobilePhoneConfirmed) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
        this.identificationType = (identificationType == null) ? new IdentificationTypeDTO() : identificationType;
        this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
        this.firstName = TextHelper.getDefaultWithTrim(firstName);
        this.secondName = TextHelper.getDefaultWithTrim(secondName);
        this.firstLastName = TextHelper.getDefaultWithTrim(firstLastName);
        this.secondLastName = TextHelper.getDefaultWithTrim(secondLastName);
        this.residenceCity = (residenceCity == null) ? new CityDTO() : residenceCity;
        this.email = TextHelper.getDefaultWithTrim(email);
        this.mobilePhone = TextHelper.getDefaultWithTrim(mobilePhone);
        this.emailConfirmed = emailConfirmed;
        this.mobilePhoneConfirmed = mobilePhoneConfirmed;
    }

    public UUID getId() { 
    	return id;
    	}
    public void setId(UUID id) {
    	this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    	}

    public IdentificationTypeDTO getIdentificationType() { 
    	return identificationType;
    	}
    public void setIdentificationType(IdentificationTypeDTO identificationType) { 
    	this.identificationType = (identificationType == null) ? new IdentificationTypeDTO() : identificationType;
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

    public CityDTO getResidenceCity() { 
    	return residenceCity;
    	}
    public void setResidenceCity(CityDTO residenceCity) { 
    	this.residenceCity = (residenceCity == null) ? new CityDTO() : residenceCity; 
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

