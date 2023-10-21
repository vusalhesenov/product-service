package az.work.productservice;

import az.work.productservice.dto.ProductRequest;
import az.work.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6-jammy");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry properties){
        properties.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
    }
    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String stringProductRequest = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringProductRequest))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1,productRepository.findAll().size());

    }

    private ProductRequest getProductRequest() {
        return ProductRequest
                .builder()
                .name("Test")
                .description("Test")
                .price(BigDecimal.valueOf(120))
                .build();
    }

}
