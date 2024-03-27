package com.project.Inventory.Management.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Inventory.Management.App.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

}
