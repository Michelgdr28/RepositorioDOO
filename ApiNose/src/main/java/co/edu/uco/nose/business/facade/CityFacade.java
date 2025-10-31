package co.edu.uco.nose.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.dto.CityDTO;

public interface CityFacade {

	List<CityDTO> findAllCities();

	List<CityDTO> findCitiesByFilter(CityDTO cityFilters);

	CityDTO findSpecificCity(UUID id);

}
