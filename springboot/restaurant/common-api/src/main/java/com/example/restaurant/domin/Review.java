package com.example.restaurant.domin;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private int score;
    @NotEmpty
    private String description;
    @Setter
    private Long restaurantId;
}
