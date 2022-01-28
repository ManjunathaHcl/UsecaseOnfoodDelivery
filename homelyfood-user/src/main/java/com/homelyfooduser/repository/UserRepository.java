package com.homelyfooduser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import com.homelyfooduser.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
