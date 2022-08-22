package com.kiu.library.repository;

import com.kiu.library.entity.DatabaseEntity;
import com.kiu.library.entity.ResourceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepository extends JpaRepository<DatabaseEntity, Integer > {

    List<DatabaseEntity> findAll();

    List<DatabaseEntity> findByIsActiveOrderByIdDesc(@Param("isActive") Integer active);

    @Query("select t from DatabaseEntity t order by t.id desc")
    List<DatabaseEntity> getLatestId(Pageable pageable);

    @Query(value = "SELECT count(id) FROM `kiu_edatabase` WHERE 1;", nativeQuery = true)
    Integer countAllResource();

}
