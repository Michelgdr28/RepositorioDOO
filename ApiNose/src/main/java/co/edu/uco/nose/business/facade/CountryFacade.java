package co.edu.uco.nose.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.dto.CountryDTO;

public interface CountryFacade {

	List<CountryDTO> findAllCountries();

	List<CountryDTO> findCountriesByFilter(CountryDTO countryFilters);

	CountryDTO findSpecificCountry(UUID id);

}
