package in.codeislife.springprocedure.repository;

import in.codeislife.springprocedure.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
