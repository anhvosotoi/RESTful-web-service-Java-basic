package com.bk.ngocanh.DAO;

//https://niithanoi.edu.vn/tat-tan-tat-ve-list-trong-java.html
import java.util.ArrayList;
import java.util.List;

import com.bk.ngocanh.Entities.devices;
import com.bk.ngocanh.JpaRepo.devicesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// rollback nếu 1 trong các thao tác thất bại
@Transactional
// devicesService là class chỉ gồm các phương thức, như cái tên của nó
public class devicesService {
 //https://openplanning.net/11645/vi-du-crud-restful-web-service-voi-spring-boot
    @Autowired
 // findAll, findById là các method của Repo, chức năng thì như cái tên       
    private devicesRepo repo;
//https://viblo.asia/p/lam-the-nao-de-goi-mot-restful-web-service-ma-khong-can-code-implement-trong-java-07LKXB3plV4
    // get all devices
    public List<devices> getall() {
        return repo.findAll();
    }

    // get device
    public devices get(Integer id) {
        return repo.findById(id).get();
    }

    // update device
    public boolean save(devices dvs) {
        devices temp_device = null;

        try {
            temp_device = get(dvs.getCid());
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(temp_device != null) {
            repo.save(dvs);
            return true;
        } else {
            return false;
        }
    }

    // create device
    public boolean create(devices dvs) {
        devices temp_device = null;

        try {
            temp_device = get(dvs.getCid());
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(temp_device == null) {
            repo.save(dvs);
            return true;
        } else {
            return false;
        }
    }

    // delete device
    public boolean delete(Integer id) {
        devices temp_device = null;

        temp_device = get(id);

        if(temp_device != null) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
