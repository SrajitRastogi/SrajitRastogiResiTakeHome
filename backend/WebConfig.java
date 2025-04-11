@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("✅ Registering resource handler for /images/**");

        registry
            .addResourceHandler("/images/**")
            .addResourceLocations("file:/app/images/");
    }
}

