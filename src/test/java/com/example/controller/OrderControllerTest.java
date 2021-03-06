package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource("classpath:application-test.properties")
public class OrderControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getOrders() throws Exception {
        willReturn(List.of(createOrder())).given(orderRepository).findAll();
        mockMvc.perform(get("/product-factory-app/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        " \"id\" : 1, \n" +
                        " \"fio\" : \"Пупкин Василий Иванович\", \n" +
                        " \"companyName\" : \"Пивной бар №1\", \n" +
                        " \"address\" : \"г. Минск, ул. Пивная, 1\", \n" +
                        " \"accountNumber\" : \"1111 2222 3333 4444\", \n" +
                        " \"basketList\" : \n" +
                        "[\n" +
                        "{\n" +
                        " \"productDTO\": \n" +
                        "{\n" +
                        " \"productId\":0, \n" +
                        " \"productName\":\"keg\", \n" +
                        " \"material\":\"steel\", \n" +
                        " \"weight\":7.1, \n" +
                        " \"cost\":100.0 \n" +
                        "},\n" +
                        " \"numberOfProduct\":100 \n" +
                        "}\n" +
                        "],\n" +
                        " \"totalCost\" : 10000.00 \n" +
                        "  }\n" +
                        "]"));

    }
    @Test
    public void getOrder() throws Exception{
        willReturn(createOrder()).given(orderRepository).getOne((long)1);
        mockMvc.perform(get("/product-factory-app/orders/1").header("orderId", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("  {\n" +
                        " \"id\" : 1, \n" +
                        " \"fio\" : \"Пупкин Василий Иванович\", \n" +
                        " \"companyName\" : \"Пивной бар №1\", \n" +
                        " \"address\" : \"г. Минск, ул. Пивная, 1\", \n" +
                        " \"accountNumber\" : \"1111 2222 3333 4444\", \n" +
                        " \"basketList\" : \n" +
                        "[\n" +
                        "{\n" +
                        " \"productDTO\": \n" +
                        "{\n" +
                        " \"productId\":0, \n" +
                        " \"productName\":\"keg\", \n" +
                        " \"material\":\"steel\", \n" +
                        " \"weight\":7.1, \n" +
                        " \"cost\":100.0 \n" +
                        "},\n" +
                        " \"numberOfProduct\":100 \n" +
                        "}\n" +
                        "],\n" +
                        " \"totalCost\" : 10000.00 \n" +
                        "  }\n"));
    }
}
