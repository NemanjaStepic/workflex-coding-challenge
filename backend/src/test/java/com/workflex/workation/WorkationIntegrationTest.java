package com.workflex.workation;
import com.workflex.workation.repository.WorkationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
@TestPropertySource(properties={"spring.datasource.url=jdbc:h2:mem:workationtest;DB_CLOSE_DELAY=-1","spring.jpa.hibernate.ddl-auto=create-drop"})
class WorkationIntegrationTest {
 @Autowired MockMvc mvc;
 @Autowired WorkationRepository repository;
 @Test void csvIsImportedOnStartup(){assertThat(repository.count()).isEqualTo(5);}
 @Test void endpointReturnsImportedWorkations() throws Exception {
  mvc.perform(get("/workflex/workation")).andExpect(status().isOk())
   .andExpect(jsonPath("$.length()").value(5))
   .andExpect(jsonPath("$[0].workationId").value("w1"))
   .andExpect(jsonPath("$[0].employee").value("Steffen Jacobs"))
   .andExpect(jsonPath("$[0].risk").value("HIGH"));
 }
}
