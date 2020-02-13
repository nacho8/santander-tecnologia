package santander.tecnologia.challenge.persistence;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import santander.tecnologia.challenge.domain.MeetUp;


@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface MeetUpRepository extends CrudRepository<MeetUp, Long> {
	
	List<MeetUp> findAll();
	

}
