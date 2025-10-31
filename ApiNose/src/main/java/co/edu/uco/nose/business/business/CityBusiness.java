package co.edu.uco.nose.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.domain.CityDomain;

public interface CityBusiness {

	List<CityDomain> findAllCities();

	List<CityDomain> findCitiesByFilter(CityDomain cityFilters);

	CityDomain findSpecificCity(UUID id);

}
