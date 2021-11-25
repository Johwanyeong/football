package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@SequenceGenerator(name = "SEQ_TEAM_NUM", sequenceName = "SEQ_TEAM_NUM", initialValue = 1, allocationSize = 1)
@Table(name = "TEAM")
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEAM_NUM")
    @Column(name = "TEAMNO")
    private Long teamno = 0L;

    @Column(name = "TEAMNAME")
    private String teamname = null;
}
