package repo;


import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.minal.springboot.database.hello.mySpringBootDatabaseApp.MySpringBootDatabaseAppApplication;
import com.minal.springboot.database.hello.mySpringBootDatabaseApp.model.mySpringBootDataModel;
import com.minal.springboot.database.hello.mySpringBootDatabaseApp.repository.mySpringBootRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MySpringBootDatabaseAppApplication.class)
@DataJpaTest
public class RepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private mySpringBootRepository myRepo;
	
	@Test
	public void retrieveByIdTest() {
		mySpringBootDataModel model1 = new mySpringBootDataModel ("Tim" , "FarmHouse" , 24);
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(myRepo.findById(model1.getId()).isPresent());
		
	}
	
}