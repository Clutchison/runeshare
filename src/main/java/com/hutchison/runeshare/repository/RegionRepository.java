package com.hutchison.runeshare.repository;

import com.hutchison.runeshare.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByNameRef(String nameRef);
}
