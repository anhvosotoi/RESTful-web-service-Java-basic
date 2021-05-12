package com.bk.ngocanh.Models.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class signin {
    private String username;
    private String password;
    private boolean accountType;
}
