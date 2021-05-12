package com.bk.ngocanh.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class users {
    @Id
    @Column(name = "uname")
    private String uname;

    @Column(name = "passwd")
    private String passwd;
    //acc_type - accType
    @Column(name = "acctype")
    private boolean accType;
}
