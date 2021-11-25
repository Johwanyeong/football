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
@SequenceGenerator(name = "SEQ_AGENT_NUM", sequenceName = "SEQ_AGENT_NUM", initialValue = 1, allocationSize = 1)
@Table(name = "AGENT")
public class Agent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AGENT_NUM")
    @Column(name = "AGENTNO")
    private Long agentno = 0L;

    @Column(name = "AGENTNAME")
    private String agentname = null;

}
