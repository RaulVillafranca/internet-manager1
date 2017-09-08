package cat.tecnocampus.respositories;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Resident;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vil883 on 23/04/2017.
 */
public interface ResidentRepository extends CrudRepository<Resident, Integer> {
    Resident findByEmail(String email);

    List<Resident> findByCommunity(Community community);

    Long countByCommunity(Community community);
}