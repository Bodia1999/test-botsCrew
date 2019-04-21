package com.example.demo.repository;

import com.example.demo.dto.response.LectorResponse;
import com.example.demo.entity.Degree;
import com.example.demo.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector,Long>, JpaSpecificationExecutor<Lector> {
    Long countAllByDegreeLike(Degree degree);


}
