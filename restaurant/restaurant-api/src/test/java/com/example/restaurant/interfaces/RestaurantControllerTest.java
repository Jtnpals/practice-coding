package com.example.restaurant.interfaces;

import com.example.restaurant.domin.RestaurantRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepositoryImpl restaurantRepository;

    @Test
    public void list() throws Exception {
       mvc.perform(get("/restaurants"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
               .andExpect(content().string(containsString("\"id\":1004")));
   }

   @Test
    public void detail() throws Exception {
        mvc.perform(get("/restaurant/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
                .andExpect(content().string(containsString("\"id\":1004")));

       mvc.perform(get("/restaurant/2020"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("\"name\":\"Cyber food\"")))
               .andExpect(content().string(containsString("\"id\":2020")));
   }
}
