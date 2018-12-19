package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.minal.springboot.database.hello.mySpringBootDatabaseApp.MySpringBootDatabaseAppApplication;
import com.minal.springboot.database.hello.mySpringBootDatabaseApp.model.mySpringBootDataModel;
import com.minal.springboot.database.hello.mySpringBootDatabaseApp.repository.mySpringBootRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootDatabaseAppApplication.class})
@AutoConfigureMockMvc

public class IntegrationTest{

@Autowired
private MockMvc mvc;

@Autowired
private mySpringBootRepository repository;

@Before 
public void clearDB() {
	repository.deleteAll();
	//clear repository - helps stop tests from interacting and leaves us a clean test environment
}

@Test
public void findingAndRetrivevingPersonFromDatabase()
	throws Exception { 
		repository.save(new mySpringBootDataModel("Tim","London", 10));
		mvc.perform(get("/api/person")
		 .contentType(MediaType.APPLICATION_JSON))
	     .andExpect(status().isOk())
	     .andExpect(content()
	     .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
         .andExpect(jsonPath("$[0].name", is("Tim")));
         
         
    
}

@Test
public void addAPersonToDatabaseTest() throws Exception {
	mvc.perform(MockMvcRequestBuilders.post("/api/person").contentType(MediaType.APPLICATION_JSON)
			.content("{\"name\" : \"Julie\",\"address\" : \"London\", \"age\": 40}"))
	.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.name", is("Julie")));
}



@Test
public void deleteAPersonFromDatabaseTest() throws Exception {
	mySpringBootDataModel Julie = new mySpringBootDataModel("Julie", "London", 40);
	repository.save(Julie);
	mvc.perform(MockMvcRequestBuilders.delete("/api/person/"+ Julie.getId())).andExpect(status().isOk());
	
}
	
}
	
 
