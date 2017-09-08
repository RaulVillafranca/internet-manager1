package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Provider;
import cat.tecnocampus.domain.ProviderType;
import cat.tecnocampus.respositories.ProviderRepository;
import cat.tecnocampus.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by internet-manager on 11/04/17.
 */
@Service
public class ProviderServiceImpl implements ProviderService {
    private ProviderRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Iterable<Provider> listAllProvider() {
        return providerRepository.findAll();
    }

    @Override
    public Provider save(Provider Provider) {
        return providerRepository.save(Provider);
    }

    @Override
    public Provider getProviderById(Integer id) {
        return providerRepository.findOne(id);
    }

    @Override
    public List<ProviderType> getProviderTypes() {
        List<ProviderType> providerTypes = new ArrayList<>();
        for (ProviderType providerType : ProviderType.values()) {
            providerTypes.add(providerType);
        }
        return providerTypes;
    }

    @Override
    public Long getProvidersCount() {
        return providerRepository.count();
    }
}
