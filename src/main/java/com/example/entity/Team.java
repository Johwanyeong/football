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
@Table(name = "TEAM")
public class Team {
    
    @Id
    @Column(name = "TEAMNO")
    private Long teamno = 0L;

    @Column(name = "TEAMNAME")
    private String teamname = null;
}
