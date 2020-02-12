package santander.tecnologia.challenge.persistence;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import santander.tecnologia.challenge.domain.MeetUpUsers;


@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface MeetUpUsersRepository extends CrudRepository<MeetUpUsers, Long> {
	
	List<MeetUpUsers> findAll();

}
