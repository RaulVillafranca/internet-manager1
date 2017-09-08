package cat.tecnocampus.services;

import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.exception.CommunityException;
import cat.tecnocampus.exception.ResidentException;

/**
 * Created by vil883 on 23/04/2017.
 */
public interface ResidentService {

    Iterable<Resident> listAllResident();

    Resident save(Resident resident);

    Resident getResidentById(Integer id) throws ResidentException;

    Long getResidentCount();

    Long getResidentCountByCommunity(Integer id) throws CommunityException;
}
