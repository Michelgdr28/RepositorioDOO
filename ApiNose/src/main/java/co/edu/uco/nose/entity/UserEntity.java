package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class UserEntity {
	
	private UUID id;
	private String identificationNumber;
	private String firstName;
	private String secondName;
	private String firstLastName;
	private String secondLastName;
	private String email;
	private String mobilePhone;
	private IdentificationTypeEntity identificationType;
	private CityEntity city;
	private boolean emailConfirmed;
	private boolean mobilePhoneConfirmed;
	
	public UserEntity() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setEmail(TextHelper.getDefault());
		setMobilePhone(TextHelper.getDefault());
		setIdentificationType(new IdentificationTypeEntity());
		setCity(new CityEntity());
		setEmailConfirmed(false);
		setMobilePhoneConfirmed(false);
	}
	
	public UserEntity(UUID id) {
		setId(id);
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setEmail(TextHelper.getDefault());
		setMobilePhone(TextHelper.getDefault());
		setIdentificationType(new IdentificationTypeEntity());
		setCity(new CityEntity());
		setEmailConfirmed(false);
		setMobilePhoneConfirmed(false);
	}
	
	public UserEntity(UUID id, String identificationNumber, String firstName, String secondName, String firstLastName, String secondLastName, String email, String phoneNumber,
			IdentificationTypeEntity identificationType, CityEntity city) {
		setId(id);
		setIdentificationNumber(identificationNumber);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstLastName(firstLastName);
		setSecondLastName(secondLastName);
		setEmail(email);
		setMobilePhone(mobilePhone);
		setIdentificationType(identificationType);
		setCity(city);
		setEmailConfirmed(false);
		setMobilePhoneConfirmed(false);
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String idNumber) {
		this.identificationNumber = TextHelper.getDefaultWithTrim(idNumber);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = TextHelper.getDefaultWithTrim(email);
	}

	public String getPhoneNumber() {
		return mobilePhone;
	}

	public void setMobilePhone(String phoneNumber) {
		this.mobilePhone = TextHelper.getDefaultWithTrim(phoneNumber);
	}

	public IdentificationTypeEntity getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(IdentificationTypeEntity identificationType) {
		this.identificationType = (ObjectHelper.isNull(identificationType) ? new IdentificationTypeEntity() : identificationType);
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = (ObjectHelper.isNull(city) ? new CityEntity() : city);
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

	public boolean isEmailConfirmedIsDefaultValue() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isMobilePhoneConfirmedIsDefaultValue() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
