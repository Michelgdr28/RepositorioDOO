package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class UserEntity extends Entity {

    private IdentificationTypeEntity identificationType;
    private String identificationNumber;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private CityEntity residenceCity;
    private String email;
    private String mobilePhone;
    private boolean emailConfirmed;
    private boolean mobilePhoneConfirmed;

    public UserEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setIdentificationType(new IdentificationTypeEntity());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setResidenceCity(new CityEntity());
        setEmail(TextHelper.getDefault());
        setMobilePhone(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobilePhoneConfirmed(false);
    }

    public UserEntity(final UUID id) {
        super(id);
        setIdentificationType(new IdentificationTypeEntity());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setResidenceCity(new CityEntity());
        setEmail(TextHelper.getDefault());
        setMobilePhone(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobilePhoneConfirmed(false);
    }

    public UserEntity(final UUID id,
                      final IdentificationTypeEntity identificationType,
                      final String identificationNumber,
                      final String firstName,
                      final String secondName,
                      final String firstLastName,
                      final String secondLastName,
                      final CityEntity residenceCity,
                      final String email,
                      final String mobilePhone,
                      final boolean emailConfirmed,
                      final boolean mobilePhoneConfirmed) {
        super(id);
        setIdentificationType(identificationType);
        setIdentificationNumber(identificationNumber);
        setFirstName(firstName);
        setSecondName(secondName);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
        setResidenceCity(residenceCity);
        setEmail(email);
        setMobilePhone(mobilePhone);
        setEmailConfirmed(emailConfirmed);
        setMobilePhoneConfirmed(mobilePhoneConfirmed);
    }

    public IdentificationTypeEntity getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(final IdentificationTypeEntity identificationType) {
        this.identificationType = (identificationType == null) ? new IdentificationTypeEntity() : identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(final String identificationNumber) {
        this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = TextHelper.getDefaultWithTrim(firstName);
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String secondName) {
        this.secondName = TextHelper.getDefaultWithTrim(secondName);
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(final String firstLastName) {
        this.firstLastName = TextHelper.getDefaultWithTrim(firstLastName);
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(final String secondLastName) {
        this.secondLastName = TextHelper.getDefaultWithTrim(secondLastName);
    }

    public CityEntity getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(final CityEntity residenceCity) {
        this.residenceCity = (residenceCity == null) ? new CityEntity() : residenceCity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = TextHelper.getDefaultWithTrim(email);
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = TextHelper.getDefaultWithTrim(mobilePhone);
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(final boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isMobilePhoneConfirmed() {
        return mobilePhoneConfirmed;
    }

    public void setMobilePhoneConfirmed(final boolean mobilePhoneConfirmed) {
        this.mobilePhoneConfirmed = mobilePhoneConfirmed;
    }
}


