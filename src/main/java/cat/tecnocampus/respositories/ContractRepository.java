package cat.tecnocampus.respositories;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by internet-manager on 10/07/2017.
 */
public interface ContractRepository extends JpaRepository<Contract, Integer>, JpaSpecificationExecutor {
    Iterable<Contract> findByCommunity(Community community);
    Long countByCommunity(Community community);
}
