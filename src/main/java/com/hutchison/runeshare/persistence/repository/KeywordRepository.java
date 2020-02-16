package com.hutchison.runeshare.persistence.repository;

import com.hutchison.runeshare.persistence.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    @Query("SELECT k from Keyword k WHERE k.nameRef IN :keyword_refs")
    Set<Keyword> findAllByNameRef(@Param(value = "keyword_refs") List<String> keywordRefs);
}
