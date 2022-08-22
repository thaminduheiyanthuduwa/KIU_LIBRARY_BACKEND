package com.kiu.library.repository;

import com.kiu.library.entity.DatabaseEntity;
import com.kiu.library.entity.DocumentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer > {

    List<DocumentEntity> findAll();

    List<DocumentEntity> findByIsActiveOrderByIdDesc(@Param("isActive") Integer active);

    @Query("select t from DocumentEntity t order by t.id desc")
    List<DocumentEntity> getLatestId(Pageable pageable);

    @Query(value = "SELECT count(id) FROM `kiu_edocument` WHERE 1;", nativeQuery = true)
    Integer countAllResource();

}
