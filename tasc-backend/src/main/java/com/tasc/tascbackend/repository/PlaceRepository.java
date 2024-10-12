package com.tasc.tascbackend.repository;


import com.tasc.tascbackend.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
