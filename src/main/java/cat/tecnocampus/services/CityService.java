package cat.tecnocampus.services;

import cat.tecnocampus.domain.City;

/**
 * Created by internet-manager on 11/04/17.
 */
public interface CityService {

    Iterable<City> listAllCity();

    City save(City city);

    City getCityById(Integer id);
}
