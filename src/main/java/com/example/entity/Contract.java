package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "SEQ_CONTRACT_NUM", sequenceName = "SEQ_CONTRACT_NUM", initialValue = 1, allocationSize = 1)
@Table(name = "CONTRACT")
public class Contract {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTRACT_NUM")
    @Column(name = "CONTRACTNO")
    private Long contractno = 0L;

    @CreationTimestamp
    @Column(updatable = false, name = "REGDATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contractdate = null;

    @ManyToOne
    @JoinColumn(name = "SCOUT")
    private Scout scout;
}
