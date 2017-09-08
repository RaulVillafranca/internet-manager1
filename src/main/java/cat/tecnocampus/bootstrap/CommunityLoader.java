package cat.tecnocampus.bootstrap;

import cat.tecnocampus.domain.*;
import cat.tecnocampus.respositories.CityRepository;
import cat.tecnocampus.respositories.CommunityRepository;
import cat.tecnocampus.respositories.ContractRepository;
import cat.tecnocampus.respositories.ProviderRepository;
import cat.tecnocampus.services.ResidentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by internet-manager on 29/3/17.
 */
@Component
public class CommunityLoader implements ApplicationListener<ContextRefreshedEvent> {
    private CityRepository cityRepository;
    private CommunityRepository communityRepository;
    private ProviderRepository providerRepository;
    private ResidentService residentService;
    private ContractRepository contractRepository;

    private Logger log = Logger.getLogger(CommunityLoader.class);

    @Autowired
    public CommunityLoader(CityRepository cityRepository, CommunityRepository communityRepository, ProviderRepository providerRepository, ResidentService residentService,
                            ContractRepository contractRepository) {
        this.cityRepository = cityRepository;
        this.communityRepository = communityRepository;
        this.providerRepository = providerRepository;
        this.residentService = residentService;
        this.contractRepository = contractRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        City city1 = new City();
        city1.setName("Mataro");
        city1.setPostalcode("08300");
        city1.setProvince("Barcelona");

        City city2 = new City();
        city2.setName("Badalona");
        city2.setPostalcode("08917");
        city2.setProvince("Barcelona");

        /* Al guardar el objeto, ya nos creará el ID */
        cityRepository.save(city1);
        cityRepository.save(city2);

        Community community1 = new Community("90931842P", "Edificio Sorigue TCM", city1, "C/Ernest Lluch 32");
        Community community2 = new Community("58032469L", "Edificio Playa Badalona", city2, "C/Del Mar 58");

        log.info("Saving community " + community1.getName());
        communityRepository.save(community1);
        log.info("Saving community " + community2.getName());
        communityRepository.save(community2);

        Provider provider1 = new Provider("A82018474", "Telefónica Movistar", ProviderType.OPTICAL_FIBER);
        Provider provider2 = new Provider("B63785372", "Netflix", ProviderType.TELEVISION);

        providerRepository.save(provider1);
        providerRepository.save(provider2);

        Resident president1 = new Resident( "77777777F", "Jose", "Palomo", "Lopez", "8", "8", "B",  "934445525", "president2@gmail.com" , community2, true, "1234");
        Resident president2 = new Resident( "00000000P", "Antonio", "Padilla", "Garcia", "7", "7", "A", "934423525" ,  "president1@gmail.com", community1, true, "1234");
        Resident resident1 = new Resident( "47475225F", "Jordi", "Mas", "Martinez", "1", "2", "B",  "934445525", "jordi@api.com" , community1, false, "1234");
        Resident resident2 = new Resident( "47475229P", "Juan", "Eloy", "Marquez", "3", "5", "A", "934423525" ,  "eloy@api.com", community1, false, "1234");

        log.info("Saving president " + president1.getName());
        residentService.save(president1);

        log.info("Saving president " + president2.getName());
        residentService.save(president2);

        log.info("Saving resident " + resident1.getName());
        residentService.save(resident1);

        log.info("Saving resident " + resident2.getName());
        residentService.save(resident2);

        community1.addResident(president1);
        community1.addResident(resident1);
        community1.addResident(resident2);

        community2.addResident(president2);

        Calendar cal = Calendar.getInstance();

        // set Date portion to January 1, 1970
        cal.set( cal.YEAR, 2017 );
        cal.set( cal.MONTH, cal.JULY );
        cal.set( cal.DATE, 1 );

        Contract contract1 = new Contract("Fibra 200MB", new Date(cal.getTime().getTime()), true, 57.99, community1, provider1);

        contractRepository.save(contract1);
        community1.addContract(contract1);

        communityRepository.save(community1);
    }
}
