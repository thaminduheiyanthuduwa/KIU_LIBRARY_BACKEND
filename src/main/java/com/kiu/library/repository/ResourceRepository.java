package com.kiu.library.repository;

import com.kiu.library.entity.ResourceEntity;
import com.kiu.library.model.resouceModel.SaveResourceRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer > {

    List<ResourceEntity> findAll();

    List<ResourceEntity> findByIsActive(@Param("isActive") Integer active, Pageable pageable);

    @Query("select t from ResourceEntity t order by t.id desc")
    List<ResourceEntity> getLatestId(Pageable pageable);

    @Query(value = "SELECT count(id) FROM `kiu_eresource` WHERE 1;", nativeQuery = true)
    Integer countAllResource();

}
