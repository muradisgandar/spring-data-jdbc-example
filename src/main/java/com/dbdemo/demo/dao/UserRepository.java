//package com.dbdemo.demo.dao;
//
//import com.dbdemo.demo.model.UserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.query.Procedure;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//public interface UserRepository extends CrudRepository<UserEntity,Long> {
//
//    @Transactional
//    @Procedure(name = "getUsers")
//    @Query(value = "CALL getUsers();", nativeQuery = true)
//    List<UserEntity> getUsers();
//
//    @Transactional
//    @Procedure(procedureName = "getUserForStatus")
//    List<UserEntity> getUserForStatus(@Param("userStatus") String status);
//
//}
