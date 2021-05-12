package com.bk.ngocanh.Jersey;


import com.bk.ngocanh.REST.accounts.accountsREST;
import com.bk.ngocanh.REST.devices.devicesREST;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
//Thi hành RESTful trong bộ chứa Servlet
//quét các class được định nghĩa để phân biệt đâu là RESTful
//https://openplanning.net/11199/huong-dan-lap-trinh-java-restful-web-service-cho-nguoi-moi-bat-dau
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        // register(RestSinhvien.class);
        register(ServerResponseFilter.class);

        // Stable
        register(accountsREST.class);
        register(devicesREST.class);
    }
}
