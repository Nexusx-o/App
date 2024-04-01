package com.registrationlogin.app.repository;

import com.registrationlogin.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //Custom Query method to find a user by username
    User findByUsername(String username);
}
