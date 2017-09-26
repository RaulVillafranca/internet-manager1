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

        City city3 = new City();
        city3.setName("Montgat");
        city3.setPostalcode("08390");
        city3.setProvince("Barcelona");

        City city4 = new City();
        city4.setName("Calella");
        city4.setPostalcode("08370");
        city4.setProvince("Barcelona");

        City city5 = new City();
        city5.setName("El Masnou");
        city5.setPostalcode("08320");
        city5.setProvince("Barcelona");




        /* Al guardar el objeto, ya nos creará el ID */
        cityRepository.save(city1);
        cityRepository.save(city2);
        cityRepository.save(city3);
        cityRepository.save(city4);
        cityRepository.save(city5);



        Community community1 = new Community("B78257573", "Edificio Sorigue TCM", city1, "C/Ernest Lluch 32");
        Community community2 = new Community("H80496565", "Edificio Playa Badalona", city2, "C/Del Mar 58");
        Community community3 = new Community("W6440470J", "Edificio de Montgat Centro", city3, "C/de la Vila 26");
        Community community4 = new Community("E22196943", "Edificio Calella Norte", city4, "C/Gran 53");

        log.info("Saving community " + community1.getName());
        communityRepository.save(community1);
        log.info("Saving community " + community2.getName());
        communityRepository.save(community2);
        log.info("Saving community " + community3.getName());
        communityRepository.save(community3);
        log.info("Saving community " + community4.getName());
        communityRepository.save(community4);

        Provider provider1 = new Provider("A94101482", "Telefónica Movistar", ProviderType.OPTICAL_FIBER);
        Provider provider2 = new Provider("G26281626", "Netflix", ProviderType.TELEVISION);
        Provider provider3 = new Provider("H50465996", "Jazztel Fibra", ProviderType.OPTICAL_FIBER);
        Provider provider4 = new Provider("J8211040D", "Orange Fibra", ProviderType.OPTICAL_FIBER);

        providerRepository.save(provider1);
        providerRepository.save(provider2);
        providerRepository.save(provider3);
        providerRepository.save(provider4);

        Resident president2 = new Resident( "89496621X", "Antonio", "Perez", "Garcia", "7", "7", "A", "934652221" ,  "president1@gmail.com", community1, true, "1234" );
        Resident president1 = new Resident( "09656748Z", "Jose", "Palomo", "Lopez", "8", "8", "B",  "934445525", "president2@gmail.com" , community2, true, "1234");
        Resident president3 = new Resident( "22173495T", "Alex", "Padilla", "Garcia", "2", "7", "A", "659954875" ,  "alexpadilla@gmail.com", community3, true, "1234" );
        Resident president4 = new Resident( "60168600W", "Marc", "Antón", "Marquez", "1", "7", "A", "934423855" ,  "marcanton@gmail.com", community4, true, "1234" );


        Resident resident1 = new Resident( "80828732Q", "Jordi", "Mas", "Brugueras", "1", "2", "B",  "934525525", "jordi@api.com" , community1, false, "1234");
        Resident resident2 = new Resident( "91725214H", "David", "Eloy", "Busquets", "3", "4", "A", "996574525" ,  "eloy@api.com", community1, false, "1234");
        Resident resident3 = new Resident( "10392892C", "Jordi", "Muñoz", "Cabanillas", "3", "3", "A", "652332145" ,  "jordi55@api.com", community1, false, "1234");
        Resident resident4 = new Resident( "86689536Y", "Laura", "Marquez", "Capdevila", "3", "1", "A", "663322558" ,  "laura63@api.com", community1, false, "1234");
        Resident resident5 = new Resident( "14464895B", "Mireia", "Ingles", "Llach", "3", "5", "A", "669988154" ,  "mireia63@api.com", community1, false, "1234");
        Resident resident6 = new Resident( "14069493W", "Ana", "Belmonte", "Llorach", "2", "6", "A", "632555897" ,  "ana36@api.com", community2, false, "1234");
        Resident resident7 = new Resident( "35229570X", "Sandra", "Blanc", "Monmany", "2", "7", "A", "662555587" ,  "Sandrablanc@api.com", community2, false, "1234");
        Resident resident8 = new Resident( "03741301Y", "Pere", "Martí", "Múnich", "1", "1", "A", "663255874" ,  "peremart78@api.com", community2, false, "1234");
        Resident resident9 = new Resident( "32836346M", "Joan", "Martinez", "Andreu", "3", "2", "A", "662145589" ,  "jperez99@api.com", community2, false, "1234");
        Resident resident10 = new Resident( "81017079Q", "Sergi", "Perez", "Marquez", "1", "3", "A", "665214458" ,  "sergipm@api.com", community3, false, "1234");
        Resident resident11 = new Resident( "50417841R", "Lucas", "Ivan", "Perez", "1", "2", "A", "665211258" ,  "lucas85@api.com", community3, false, "1234");
        Resident resident12 = new Resident( "66112282R", "Ariadna", "Perez", "Martín", "1", "1", "A", "665889587" ,  "perez36@api.com", community4, false, "1234");


        log.info("Saving president " + president1.getName());
        residentService.save(president1);
        log.info("Saving president " + president2.getName());
        residentService.save(president2);
        log.info("Saving president " + president3.getName());
        residentService.save(president3);
        log.info("Saving president " + president4.getName());
        residentService.save(president4);

        log.info("Saving resident " + resident1.getName());
        residentService.save(resident1);
        log.info("Saving resident " + resident2.getName());
        residentService.save(resident2);
        log.info("Saving resident " + resident3.getName());
        residentService.save(resident3);
        log.info("Saving resident " + resident4.getName());
        residentService.save(resident4);
        log.info("Saving resident " + resident5.getName());
        residentService.save(resident5);
        log.info("Saving resident " + resident6.getName());
        residentService.save(resident6);
        log.info("Saving resident " + resident7.getName());
        residentService.save(resident7);
        log.info("Saving resident " + resident8.getName());
        residentService.save(resident8);
        log.info("Saving resident " + resident9.getName());
        residentService.save(resident9);
        log.info("Saving resident " + resident10.getName());
        residentService.save(resident10);
        log.info("Saving resident " + resident11.getName());
        residentService.save(resident11);
        log.info("Saving resident " + resident12.getName());
        residentService.save(resident12);

        community1.addResident(president1);
        community2.addResident(president2);
        community1.addResident(president3);
        community2.addResident(president4);

        community1.addResident(resident1);
        community1.addResident(resident2);
        community1.addResident(resident3);
        community1.addResident(resident4);
        community1.addResident(resident5);
        community1.addResident(resident6);
        community1.addResident(resident7);
        community1.addResident(resident8);
        community1.addResident(resident9);
        community1.addResident(resident10);
        community1.addResident(resident11);
        community1.addResident(resident12);



        Calendar cal = Calendar.getInstance();

        // set Date portion to January 1, 1970
        cal.set( cal.YEAR, 2017 );
        cal.set( cal.MONTH, cal.JULY );
        cal.set( cal.DATE, 1 );

        Contract contract1 = new Contract("Fibra 200MB", new Date(cal.getTime().getTime()), true, 57.99, community1, provider1);
        Contract contract2 = new Contract("Fibra 300MB", new Date(cal.getTime().getTime()), true, 39.99, community2, provider1);
        Contract contract3 = new Contract("Fibra 100MB", new Date(cal.getTime().getTime()), true, 75.00, community3, provider3);
        Contract contract4 = new Contract("Fibra 250MB ", new Date(cal.getTime().getTime()), true, 80.95, community4, provider4);

        contractRepository.save(contract1);
        community1.addContract(contract1);
        contractRepository.save(contract2);
        community1.addContract(contract2);
        contractRepository.save(contract3);
        community1.addContract(contract3);
        contractRepository.save(contract4);
        community1.addContract(contract4);

        communityRepository.save(community1);
        communityRepository.save(community2);
        communityRepository.save(community3);
        communityRepository.save(community4);
    }
}
