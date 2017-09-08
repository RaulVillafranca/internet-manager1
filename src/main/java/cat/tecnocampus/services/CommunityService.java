package cat.tecnocampus.services;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.exception.CommunityException;

/**
 * Created by internet-manager on 11/04/17.
 */
public interface CommunityService {

    Iterable<Community> listAllCommunity();

    Community save(Community community);

    Community getCommunityById(Integer id) throws CommunityException;

    void addResident(Integer id, Resident resident);

    void addContract(Integer communityId, Contract contract);
}
