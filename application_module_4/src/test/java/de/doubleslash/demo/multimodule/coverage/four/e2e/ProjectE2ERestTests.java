package de.doubleslash.demo.multimodule.coverage.four.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.doubleslash.demo.multimodule.coverage.four.models.Project;
import de.doubleslash.demo.multimodule.coverage.four.repositories.ProjectRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectE2ERestTests {

    private final String URL = "/project/";

    @Autowired
    private TestRestTemplate systemUnderTest;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @After
    public void tearDown() {
        projectRepository.deleteAll();
    }

    @Test
    public void should_ReturnPersistedProject_When_PostWithProject() throws Exception {
        // given
        Project project = new Project();
        project.setName("Test-project");
        Project createdProject = projectRepository.save(project);

        // when
        Project actualProject = systemUnderTest.postForObject(URL, project, Project.class);

        // then
        assertEquals(actualProject.getId(), createdProject.getId());
    }

}
