package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component // Autowired를 받기 위해 지정
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)//있을수도 없을수도
    protected JpaRepository<Entity, Long> baseRepository;

}
