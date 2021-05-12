package com.bk.ngocanh.DAO;

import com.bk.ngocanh.Entities.users;
import com.bk.ngocanh.JpaRepo.usersRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class usersService {
    @Autowired
    private usersRepo repo;

    // get user
    public users get(String username) {
        return repo.findById(username).get();
    }

    // Change password
    public boolean save(users usr) {
        users temp_user = null;

        temp_user = get(usr.getUname());

        if(temp_user != null) {
            repo.save(usr);
            return true;
        } else {
            return false;
        }
    }

    // create devices user
    public boolean create(users usr) {
        users temp_user = null;

        try {
            temp_user = get(usr.getUname());
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(temp_user == null) {
            repo.save(usr);
            return true;
        } else {
            return false;
        }
    }

    // delete devices user
    public boolean delete(String username) {
        users temp_user = null;

        temp_user = get(username);

        if(temp_user != null) {
            repo.deleteById(username);
            return true;
        } else {
            return false;
        }
    }
}
