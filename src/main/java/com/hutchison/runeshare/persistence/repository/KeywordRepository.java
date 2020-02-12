package com.hutchison.runeshare.persistence.repository;

import com.hutchison.runeshare.persistence.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
