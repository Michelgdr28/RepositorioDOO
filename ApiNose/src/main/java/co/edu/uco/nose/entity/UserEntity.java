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
	private boolean emailConfirmedIsDefaultValue;
	private boolean mobilePhoneConfirmedIsDefaultValue;

	
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
		setEmailConfirmedIsDefaultValue(true);
		setMobilePhoneConfirmedIsDefaultValue(true);
	
	}

	public UserEntity(final UUID id) {
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
		setEmailConfirmedIsDefaultValue(true);
		setMobilePhoneConfirmedIsDefaultValue(true);
		
	}

	public UserEntity(final UUID id, final String identificationNumber, final String firstName, final String secondName, final String firstLastName, final String secondLastName, final String email, final String mobilePhone,
			final IdentificationTypeEntity identificationType, final CityEntity city, final boolean emailConfirmed,final boolean mobilePhoneConfirmed) {
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
		setEmailConfirmed(emailConfirmed);
		setMobilePhoneConfirmed(mobilePhoneConfirmed);
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = TextHelper.getDefaultWithTrim(mobilePhone);
	}

	public IdentificationTypeEntity getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(IdentificationTypeEntity identificationType) {
		this.identificationType = ObjectHelper.getDefault(identificationType, new IdentificationTypeEntity());
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = ObjectHelper.getDefault(city, new CityEntity());
	}

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(final boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
		setEmailConfirmedIsDefaultValue(false);
	}

	public boolean isMobilePhoneConfirmed() {
		return mobilePhoneConfirmed;
	}

	public void setMobilePhoneConfirmed(final boolean mobilePhoneConfirmed) {
		this.mobilePhoneConfirmed = mobilePhoneConfirmed;
		setMobilePhoneConfirmedIsDefaultValue(false);
	}

	public boolean isEmailConfirmedIsDefaultValue() {
		return emailConfirmedIsDefaultValue;
	}

	public boolean isMobilePhoneConfirmedIsDefaultValue() {
		return mobilePhoneConfirmedIsDefaultValue;
	}

	public void setEmailConfirmedIsDefaultValue(final boolean emailConfirmedIsDefaultValue) {
		this.emailConfirmedIsDefaultValue = emailConfirmedIsDefaultValue;
	}
	private void setMobilePhoneConfirmedIsDefaultValue(final boolean mobilePhoneConfirmedIsDefaultValue) {
		this.mobilePhoneConfirmedIsDefaultValue = mobilePhoneConfirmedIsDefaultValue;
	}
		
}
