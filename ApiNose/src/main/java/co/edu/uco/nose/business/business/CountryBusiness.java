package co.edu.uco.nose.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.domain.CountryDomain;

public interface CountryBusiness {

	List<CountryDomain> findAllCountries();

	List<CountryDomain> findCountriesByFilter(CountryDomain countryFilters);

	CountryDomain findSpecificCountry(UUID id);

}
