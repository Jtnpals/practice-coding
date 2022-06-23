package com.example.study.model.network.response;

import com.example.study.model.enumclass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserApiResponse {
    private Long id;
    private String account;
    private String password; //암호화해서 내보낼거라 리퀘스트 리스폰스 따로관리하는게좋음
    private UserStatus status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAd;
    private LocalDateTime unregisteredAt;
}
