package cat.cat.global.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }


    public Info apiInfo() {
        return new Info()
                .title("MBTIgram Springdoc")
                .description("Springdoc을 사용한 MBTIgram Swagger UI")
                .version("1.0.0");
    }

    // ★ 이게 반드시 필요함
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("v1") // swagger 그룹 이름
                .pathsToMatch("/**") // 문서화할 경로
                .build();
    }
}
