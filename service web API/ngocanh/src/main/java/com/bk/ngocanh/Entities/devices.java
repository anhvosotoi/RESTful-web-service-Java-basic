//Hibernate, ánh xạ tới bảng, cột
//Source: https://huongdanjava.com/vi/co-ban-ve-hibernate.html
package com.bk.ngocanh.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//https://huongdanjava.com/vi/co-ban-ve-jpa.html
// lombok: tạo class nhanh, khỏi mất công set, get, tạo hàm dựng contructor 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// https://gpcoder.com/6282-tong-quan-ve-jpa-java-persistence-api/

@Table(name = "devices")
//class theo sau mapping tới bảng, không nhất thiết giống tên, 
@Getter
@Setter
// hàm dựng không đối số
@NoArgsConstructor
// all đối số
@AllArgsConstructor
public class devices {
    // theo sau đây là khóa chính
    @Id
    // định kiểu tăng khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
// https://hocspringmvc.net/id-generatedvaluestrategy-generationtype-identity/
    @Column(name = "devicename")
    // biến private nên cần get, set
    private String deviceName;

    @Column(name = "devicetype")
    private boolean deviceType;

    @Column(name = "datatype")
    private boolean dataType;

    @Column(name = "bitvalue")
    private boolean bitValue;

    @Column(name = "decimalvalue")
    private double decimalValue;
}
