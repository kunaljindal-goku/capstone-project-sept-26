package dev.kunal.productcatalogservice.controllers;

import dev.kunal.productcatalogservice.clients.AuthClient;
import dev.kunal.productcatalogservice.controller.ProductController;
import dev.kunal.productcatalogservice.mappers.ProductMapper;
import dev.kunal.productcatalogservice.model.Product;
import dev.kunal.productcatalogservice.service.IProductService;
import dev.kunal.productcatalogservice.service.PitchService;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
@Import(ProductMapper.class)
public class ProductControllersTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;

    @MockitoBean
    private PitchService pitchService;

    @MockitoBean
    private AuthClient authClient;

    @Test
    void getProductById_existingProduct_returns200AndTheJson() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        product.setId(id);
        product.setName("Mascara");
        product.setPrice(new BigDecimal("9.99"));
        when(productService.getProductById(id)).thenReturn(product);

        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Mascara"))
                .andExpect(jsonPath("$.price").value(9.99));
    }

    @Test
    void createProduct_noToken_returns401AndNothingIsSaved() throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Mascara\"}"))
                .andExpect(status().isUnauthorized());

        // verify request getting rejected at filter level itself
        verify(productService, never()).createProduct(any());
    }


}
