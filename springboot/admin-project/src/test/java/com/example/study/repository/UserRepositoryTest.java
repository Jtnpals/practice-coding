package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired // dependency injection 기본적으로 singleton
    private UserRepository userRepository;

    @Test
    public void create() {
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-3333-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

//        User user = new User();
//        user.setAccount(account);
//        user.setPassword(password);
//        user.setStatus(status);
//        user.setEmail(email);
//        user.setPhoneNumber(phoneNumber);
//        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        User user = User.builder().account(account).password(password).status(status).email(email).phoneNumber(phoneNumber).build();

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");

        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("---- 주문 묶음 ----");
                System.out.println(orderGroup.getRevName());
                System.out.println(orderGroup.getTotalPrice());
                System.out.println(orderGroup.getRevAddress());

                System.out.println("---- 주문 상세 ----");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("파트너사 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());

                    System.out.println(orderDetail.getStatus());
                    System.out.println(orderDetail.getArrivalDate());


                });
            });
        }
        Assert.assertNotNull(user);
    }

    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectedUser -> {
            selectedUser.setAccount("PPPP");
            selectedUser.setUpdatedAt(LocalDateTime.now());
            selectedUser.setCreatedBy("update method()");

            userRepository.save(selectedUser);//동일한 아이디가 존재하면 생성이아닌 업데이트를 해줌
        });

    }

    @Test
    @Transactional //데이터베이스 다시 롤백해줘서 데이터베이스에 영향을 주지않음
    public void delete() {
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectedUser -> {
            userRepository.delete(selectedUser);
        });

        Optional<User> deleteUer = userRepository.findById(3L);

        Assert.assertFalse(deleteUer.isPresent()); //false
    }
}
