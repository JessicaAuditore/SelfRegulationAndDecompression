package com.soul.dao;

import com.soul.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAllByName(String name);

    List<User> findAllByNameLike(String name);
}