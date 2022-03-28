package com.mycompany.lab7.services;


import com.mycompany.lab7.dataaccess.RoleDB;

import com.mycompany.lab7.models.Role;
import com.mycompany.lab7.models.User;
import java.util.List;


public class RoleService {
    private RoleDB roleDB = new RoleDB();

    
    public List<Role> getAll(String email) throws Exception {
        List<Role> roles = this.roleDB.getAll();
        return roles;
    }

}
