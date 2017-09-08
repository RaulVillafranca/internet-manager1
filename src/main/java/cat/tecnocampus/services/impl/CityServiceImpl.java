package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.City;
import cat.tecnocampus.respositories.CityRepository;
import cat.tecnocampus.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by internet-manager on 11/04/17.
 */
@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Iterable<City> listAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCityById(Integer id) {
        return cityRepository.findOne(id);
    }
}
