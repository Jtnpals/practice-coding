package com.example.restaurant.application;

import com.example.restaurant.domin.Review;
import com.example.restaurant.domin.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, Review review){
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }
}
