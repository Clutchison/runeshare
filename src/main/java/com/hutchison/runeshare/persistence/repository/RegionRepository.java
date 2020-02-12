package com.hutchison.runeshare.persistence.repository;

import com.hutchison.runeshare.persistence.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
