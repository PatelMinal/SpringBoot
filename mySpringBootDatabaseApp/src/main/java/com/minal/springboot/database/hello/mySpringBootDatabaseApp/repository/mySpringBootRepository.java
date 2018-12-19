package com.minal.springboot.database.hello.mySpringBootDatabaseApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minal.springboot.database.hello.mySpringBootDatabaseApp.model.*;

@Repository
public interface mySpringBootRepository extends JpaRepository<mySpringBootDataModel,Long> {

}
