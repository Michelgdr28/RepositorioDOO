package co.edu.uco.nose.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.domain.IdentificationTypeDomain;

public interface IdentificationTypeBusiness {

	List<IdentificationTypeDomain> findAllIdentificationTypes();

	List<IdentificationTypeDomain> findIdentificationTypesByFilter(IdentificationTypeDomain identificationTypeFilters);

	IdentificationTypeDomain findSpecificIdentificationType(UUID id);

}
