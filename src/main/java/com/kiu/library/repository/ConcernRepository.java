package com.kiu.library.repository;

import com.kiu.library.entity.ConcernEntity;
import com.kiu.library.entity.DatabaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcernRepository extends JpaRepository<ConcernEntity, Integer > {

    List<ConcernEntity> findAll();

    List<ConcernEntity> findByIsActiveOrderByIdDesc(@Param("isActive") Integer active);
//
//    @Query("select t from DatabaseEntity t order by t.id desc")
//    List<ConcernEntity> getLatestId(Pageable pageable);
//
    @Query(value = "SELECT count(id) FROM `concern`;", nativeQuery = true)
    Integer countAllResource();

}
