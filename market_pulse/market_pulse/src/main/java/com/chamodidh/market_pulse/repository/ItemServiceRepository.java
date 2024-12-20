package com.chamodidh.market_pulse.repository;

import com.chamodidh.market_pulse.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemServiceRepository extends JpaRepository<Item,Long> {
    @Query(value = "SELECT * FROM items " +
            "WHERE LOWER(item_name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(brand_name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(description) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            nativeQuery = true)
    List<Item> searchItemsByKeyword(@Param("keyword") String keyword);
}
