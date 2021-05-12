package com.bk.ngocanh.JpaRepo;

import com.bk.ngocanh.Entities.devices;

import org.springframework.data.jpa.repository.JpaRepository;
// tính kế thừa trong java extend 
// https://viettuts.vn/java/tinh-ke-thua-trong-java
// class devicesRepo kế thừa class JpaRepository  
public interface devicesRepo extends JpaRepository<devices, Integer> {

}
