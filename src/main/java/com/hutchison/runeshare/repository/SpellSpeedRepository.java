package com.hutchison.runeshare.repository;

import com.hutchison.runeshare.model.entity.SpellSpeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellSpeedRepository extends JpaRepository<SpellSpeed, Long> {
    SpellSpeed findByNameRef(String spellSpeedRef);
}
