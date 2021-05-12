package com.bk.ngocanh.Models.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class newdevice {
    private String deviceName;
    private boolean deviceType;
    private boolean dataType;
    private boolean bitValue;
    private double decimalValue;
}
