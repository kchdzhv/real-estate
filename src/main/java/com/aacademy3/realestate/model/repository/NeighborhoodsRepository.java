package com.aacademy3.realestate.model.repository;

import com.aacademy3.realestate.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NeighborhoodsRepository extends JpaRepository<Neighborhood, Long> {

    Optional<Neighborhood> findByName(String name);
}
