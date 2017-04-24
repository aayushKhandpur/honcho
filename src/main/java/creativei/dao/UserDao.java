package creativei.dao;

import creativei.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 21-09-2016.
 */

@Transactional
public interface UserDao extends CrudRepository<User, Long>{
    /**
     * Return the user having the passed email or null if no user is found.
     *
     * @param email the user email.
     */
    public List<User> findByName(String email);

}

