package com.distributed.tracing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distributed.tracing.bean.ServiceOneBean;

@Repository
public interface ServiceJPARepository extends JpaRepository<ServiceOneBean, Integer> {

}
