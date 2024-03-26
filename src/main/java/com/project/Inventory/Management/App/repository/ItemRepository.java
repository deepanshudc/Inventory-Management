package com.project.Inventory.Management.App.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Inventory.Management.App.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    @Query("select u from Item u where u.SKU = ?1")
	Optional<Item> findBySKU(String itemSKU);

	void deleteBySKU(String sKU);


}
