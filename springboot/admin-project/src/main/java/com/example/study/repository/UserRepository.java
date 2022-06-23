package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber); //하나의 폰으로 여러개 가입가능하니 그중 최근에걸 id의 역순으로 매칭
}
