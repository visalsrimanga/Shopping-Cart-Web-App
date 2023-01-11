package lk.ilabs.user.repository;

import lk.ilabs.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
