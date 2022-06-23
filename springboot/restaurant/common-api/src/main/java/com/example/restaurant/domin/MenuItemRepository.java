package com.example.restaurant.domin;

import com.example.restaurant.domin.MenuItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByRestaurantId(Long id);
    void deleteById(Long id);
}
