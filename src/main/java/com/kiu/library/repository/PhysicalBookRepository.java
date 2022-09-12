package com.kiu.library.repository;

import com.kiu.library.entity.BookRequirementEntity;
import com.kiu.library.entity.PhysicalBookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalBookRepository extends JpaRepository<PhysicalBookEntity, Integer > {

    List<PhysicalBookEntity> findAll();

    List<PhysicalBookEntity> findByStatusOrderByIdDesc(@Param("status") Integer status);

    @Query(value = "SELECT count(id) FROM `physical_book`;", nativeQuery = true)
    Integer countAllResource();

    @Query("select t from PhysicalBookEntity t order by t.id desc")
    List<PhysicalBookEntity> getLatestId(Pageable pageable);


}
