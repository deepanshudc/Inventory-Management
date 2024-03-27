package com.project.Inventory.Management.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Inventory.Management.App.entity.Authorities;

public interface AuthorityRepository extends JpaRepository<Authorities, String> {

}
