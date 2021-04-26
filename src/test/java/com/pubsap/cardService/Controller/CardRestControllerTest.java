package com.pubsap.cardService.Controller;

import com.pubsap.cardService.Exeption.NoRecordFoundException;
import com.pubsap.cardService.Model.Creditcard;
import com.pubsap.cardService.Service.CardService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = CardRestController.class)
public class CardRestControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    List<Creditcard> creditCardList;

    @Test
    @WithMockUser(value = "pubsap")
    public void listAllCards() throws Exception {
        Mockito.when(
                cardService.findAll()).thenReturn(creditCardList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/listCards").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "pubsap")
    public void listAllCardsEmpty() throws Exception {
        Mockito.when(
                cardService.findAll()).thenThrow(NoRecordFoundException.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/listCards").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    @WithMockUser(value = "pubsap")
    public void saveCardValid() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/v1/addCard").contentType(MediaType.APPLICATION_JSON).content(
                       "    {\n" +
                               "        \"cardnumber\": \"33\",\n" +
                               "        \"cardname\": \"Dangote\",\n" +
                               "        \"cardlimit\": \"500\"\n" +
                               "    }")).andReturn().getResponse();
        assertEquals(response.getStatus(),(HttpStatus.CREATED.value()));
    }



}