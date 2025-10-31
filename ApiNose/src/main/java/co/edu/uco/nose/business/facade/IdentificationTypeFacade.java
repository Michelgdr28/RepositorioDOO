package co.edu.uco.nose.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.dto.IdentificationTypeDTO;

public interface IdentificationTypeFacade {

	List<IdentificationTypeDTO> findAllIdentificationTypes();

	List<IdentificationTypeDTO> findIdentificationTypesByFilter(IdentificationTypeDTO identificationTypeFilters);

	IdentificationTypeDTO findSpecificIdentificationType(UUID id);

}
