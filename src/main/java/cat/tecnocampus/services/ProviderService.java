package cat.tecnocampus.services;

import cat.tecnocampus.domain.Provider;
import cat.tecnocampus.domain.ProviderType;

import java.util.List;

/**
 * Created by internet-manager on 11/04/17.
 */
public interface ProviderService {

    Iterable<Provider> listAllProvider();

    Provider save(Provider provider);

    Provider getProviderById(Integer id);

    List<ProviderType> getProviderTypes();

    Long getProvidersCount();
}
