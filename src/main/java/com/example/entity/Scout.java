package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(name = "SEQ_SCOUT_NUM", sequenceName = "SEQ_SCOUT_NUM", initialValue = 1, allocationSize = 1)
@Table(name = "SCOUT")
public class Scout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCOUT_NUM")
    @Column(name = "SCOUTNO")
    private Long scoutno = 0L;

    @ManyToOne
    @JoinColumn
    private Player player;

    @ManyToOne
    @JoinColumn
    private Member member;

}
