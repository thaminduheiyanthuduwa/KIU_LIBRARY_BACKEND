package com.kiu.library.repository;

import com.kiu.library.entity.BookRequirementEntity;
import com.kiu.library.entity.DocumentEntity;
import com.kiu.library.entity.ResourceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<BookRequirementEntity, Integer > {

    List<BookRequirementEntity> findAll();

    List<BookRequirementEntity> findByIsActiveOrderByIdDesc(@Param("isActive") Integer active, Pageable pageable);

    @Query("select t from BookRequirementEntity t order by t.id desc")
    List<BookRequirementEntity> getLatestId(Pageable pageable);

    @Query(value = "SELECT count(id) FROM `kiu_eresource` WHERE 1;", nativeQuery = true)
    Integer countAllResource();

}
