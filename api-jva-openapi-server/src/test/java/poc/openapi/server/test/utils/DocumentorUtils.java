package poc.openapi.server.test.utils;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esure.apidocs.BeanJsonDocumentor;
import com.esure.apidocs.ControllerDocumentor;
import com.esure.apidocs.InterfaceDocumentor;
import com.esure.apidocs.Scenario;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DocumentorUtils {

    public static void createControllerDocs(final JUnitRestDocumentation restDocumentation) throws IOException {
        new ControllerDocumentor(restDocumentation).document();
    }

    public static void createInterfaceDocs(final JUnitRestDocumentation restDocumentation) throws IOException {
        new InterfaceDocumentor(restDocumentation).document();
    }


    public static <T> void addBeanDocumentation(final JUnitRestDocumentation restDocumentation,
                                                final String title,
                                                final String name,
                                                final T bean,
                                                final String snippetPath) throws IOException {
        new BeanJsonDocumentor<>(
            restDocumentation,
            title,
            snippetPath,
            name,
            bean
        ).document();
    }


    public static <T> Scenario<String, T> createHttPostScenario(
            final String request,
            final int status,
            final T response,
            final String name,
            final String code) {
        return new Scenario<>(name,
                RequestMethod.GET,
                code,
                HttpStatus.valueOf(status),
                request,
                response);
    }
}
