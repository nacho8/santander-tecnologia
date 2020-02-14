package santander.tecnologia.challenge.persistence;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import santander.tecnologia.challenge.domain.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findAll();
	
	@Query("SELECT u FROM User u where u.userName = ?1")
	User findByUserName(String userName);

}
