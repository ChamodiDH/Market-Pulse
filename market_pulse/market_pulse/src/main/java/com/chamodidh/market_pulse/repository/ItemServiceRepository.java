package com.chamodidh.market_pulse.repository;

import com.chamodidh.market_pulse.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemServiceRepository extends JpaRepository<Item,Long> {
}
