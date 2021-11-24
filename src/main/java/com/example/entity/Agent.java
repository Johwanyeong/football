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
@Table(name = "AGENT")
public class Agent {
    
    @Id
    @Column(name = "AGENTNO")
    private Long agentno = 0L;

    @Column(name = "AGENTNAME")
    private String agentname = null;

}
