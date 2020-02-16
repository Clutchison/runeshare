package com.hutchison.runeshare.persistence.repository;

import com.hutchison.runeshare.persistence.entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsRepository extends JpaRepository<Assets, Long> {
}
