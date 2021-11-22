package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "USER")
public class Member {
    
    @Id
    @Column(name = "USERID")
    private String userid = null;

    @Column(name = "USERPW")
    private String userpw = null;

    @Column(name = "USERNAME")
    private String username = null;

    @Column(name = "USERROLE", updatable = false)
    private String userrole = null;

}
