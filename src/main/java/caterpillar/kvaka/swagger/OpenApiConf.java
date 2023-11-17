package caterpillar.kvaka.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Inventors and Inventions API",
                description = "Tables of inventors and their inventions", version = "1.0.0",
                contact = @Contact(
                        name = "Khodakovskiy Vadim",
                        email = "vadim.khodakovskiy@gmail.com")
        )
)

public class OpenApiConf {
}
