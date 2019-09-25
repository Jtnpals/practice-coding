package com.example.restaurant.domin;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTest {
    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant(1004L, "Restaurant", "Seoul");

        assertThat(restaurant.getName(), is("Restaurant"));
        assertThat(restaurant.getAddress(), is("Seoul"));
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1004L,"Restaurant", "Seoul");

        assertThat(restaurant.getInformation(), is("Restaurant in Seoul"));
    }
}
