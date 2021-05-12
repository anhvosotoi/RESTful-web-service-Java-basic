package com.bk.ngocanh.REST.devices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bk.ngocanh.DAO.devicesService;
import com.bk.ngocanh.Entities.devices;
import com.bk.ngocanh.Models.device.device;
import com.bk.ngocanh.Models.device.newdevice;

import org.springframework.beans.factory.annotation.Autowired;
//source     https://gpcoder.com/5677-java-web-services-jersey-jax-rs-rest/
// class và object java howkteam
@Path("/api/devices")
public class devicesREST {
    @Autowired
    private devicesService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<device> getalldevices() {
        List<device> tmpDevices = new ArrayList<device>();

        for(devices tmpdvcs : service.getall()) {
            tmpDevices.add(new device(tmpdvcs));
        }

        return tmpDevices;
    }

    @GET
    // 1 lần @Path là Path được nối thêm 
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public device getonedevice(@PathParam("id") int id) {
        device tmpdvc;
        tmpdvc = new device(service.get(id));

        return tmpdvc;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postdevice(newdevice ndv) {
        if(ndv.getDeviceName() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input!").build();
        }

        devices meodv = new devices();
// https://v1study.com/java-cac-thanh-phan-cua-mot-lop.html
        meodv.setCid(0);
        meodv.setDeviceName(ndv.getDeviceName());
        meodv.setDeviceType(ndv.isDeviceType());
        meodv.setDataType(ndv.isDataType());
        meodv.setBitValue(ndv.isBitValue());
        meodv.setDecimalValue(ndv.getDecimalValue());

        boolean kk = false;

        try {
            kk = service.create(meodv);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(kk == true) {
            return Response.status(Status.CREATED).build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST.getStatusCode()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatedevice(devices updatedvs) {
        if(updatedvs.getCid() == null || updatedvs.getDeviceName() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input!").build();
        }

        boolean kk = false;

        try {
            kk = service.save(updatedvs);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(kk) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST.getStatusCode()).build();
        }
    }
}
