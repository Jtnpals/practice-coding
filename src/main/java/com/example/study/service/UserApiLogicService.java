package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository; //실질적으로는 엔티티 레포지토리 따로관리함

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse 리턴
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1. request data
        UserApiRequest userApiRequest = request.getData();
        // 2. user 생성
        User user = User.builder().account(userApiRequest
                .getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);
        // 3. 생성된 데이터 -> UserApiResponse 리턴
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        /*
        // id -> repository getOne, getById
        Optional<User> optional = userRepository.findById(id);

        // user -> userApiResponse return
        return optional.map(user -> response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));
        */

        return userRepository.findById(id).map(user -> response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data
        UserApiRequest userApiRequest = request.getData();
        // 2. id -> user 데이터를 찾음
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user ->{
            // 3. update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
            // 4. userApiResponse
        })
        .map(user -> userRepository.save(user)) //update
        .map(updateUser -> response(updateUser)) //userApiResponse
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // id -> repository -> user
        Optional<User> optional = userRepository.findById(id);
        // repository -> delete
        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user){
        //user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAd(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // 헤더 + data return

        return Header.OK(userApiResponse);

    }
}
