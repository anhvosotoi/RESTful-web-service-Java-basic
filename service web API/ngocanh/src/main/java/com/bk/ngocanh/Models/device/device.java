package com.bk.ngocanh.Models.device;

import com.bk.ngocanh.Entities.devices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class device {
    private Integer cid;
    private String deviceName;
    private boolean deviceType;
    private boolean dataType;
    private boolean bitValue;
    private double decimalValue;

    public device(devices dvcs) {
        this.cid = dvcs.getCid();
        this.deviceName = dvcs.getDeviceName();
        this.deviceType = dvcs.isDeviceType();
        this.dataType = dvcs.isDataType();
        this.bitValue = dvcs.isBitValue();
        this.decimalValue = dvcs.getDecimalValue();
    }
}