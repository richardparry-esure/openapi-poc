package poc.openapi.server.test.utils;

import static poc.openapi.server.test.utils.DocumentorUtils.createControllerDocs;
import static poc.openapi.server.test.utils.DocumentorUtils.createInterfaceDocs;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.esure.apidocs.ScenariosDocumentor;

import poc.openapi.server.OpenApiServerApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OpenApiServerApplication.class)
@AutoConfigureMockMvc
@AutoConfigureWireMock
@ActiveProfiles("local")
public abstract class ApplicationCT {

    @ClassRule
    public final static JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/snippets");
    protected final static ScenariosDocumentor scenariosDocumentor = new ScenariosDocumentor(restDocumentation);

    @Before
    public void setUp() throws Exception {
        createControllerDocs(restDocumentation);
        createInterfaceDocs(restDocumentation);
    }
}
