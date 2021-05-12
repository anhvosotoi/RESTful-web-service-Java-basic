package com.bk.ngocanh.REST.accounts;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bk.ngocanh.DAO.usersService;
import com.bk.ngocanh.Entities.users;
import com.bk.ngocanh.Models.account.signin;
import com.bk.ngocanh.Models.account.signup;
import com.bk.ngocanh.Security.myJWT;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/accounts")
public class accountsREST {
    @Autowired
    private usersService service;

    // for admin signin
    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signin(signin signinacc) {
        if(signinacc.getUsername() == null || signinacc.getPassword() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input!").build();
        }

        users usr = null;
        boolean k = false;
        try {
            usr = service.get(signinacc.getUsername());
            if(signinacc.getPassword().equals(usr.getPasswd()) && signinacc.isAccountType() == usr.isAccType()) {
                k = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        myJWT meok = new myJWT();
        if(k) {
            return Response.status(Status.OK).entity("Bearer " + meok.yeahmtp()).build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Wrong username or password!").build();
        }
    }

    // for register new controller user
    @POST
    @Path("/newcontroller")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response regctrl(signup signupacc) {
        if(signupacc.getUsername() == null || signupacc.getPassword() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input!").build();
        }

        users usr = new users(signupacc.getUsername(), signupacc.getPassword(), false);
        boolean k = false;
        try {
            k = service.create(usr);
        } catch (Exception e) {
            //TODO: handle exception
        }

        myJWT meok = new myJWT();
        if(k) {
            return Response.status(Status.OK).entity("Bearer " + meok.yeahmtp()).build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Username existed!").build();
        }
    }

    // Get controller user token
    @GET
    @Path("/usertoken")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserToken(@QueryParam("username") String username) {
        if(username == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input!").build();
        }

        boolean k = false;
        users usr = null;

        try {
            usr = service.get(username);
            k = (username.equals(usr.getUname())) ? true : false;
        } catch (Exception e) {
            //TODO: handle exception
        }

        myJWT meok = new myJWT();
        if(k) {
            return Response.status(Status.OK).entity("Bearer " + meok.yeahmtp()).build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Username does not exist!").build();
        }
    }
}
