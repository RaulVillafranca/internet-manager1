package cat.tecnocampus.respositories;

import cat.tecnocampus.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by internet-manager on 26/07/2017.
 */
public interface UserRepositoy extends CrudRepository<User, String> {
}
