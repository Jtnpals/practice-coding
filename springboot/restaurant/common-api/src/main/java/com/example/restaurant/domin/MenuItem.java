package com.example.restaurant.domin;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class MenuItem {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long restaurantId;
    private String name;

    @Transient //DB에 안넣음
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;
}
