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
@SequenceGenerator(name = "SEQ_REVIEW_NUM", sequenceName = "SEQ_REVIEW_NUM", initialValue = 1, allocationSize = 1)
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REVIEW_NUM")
    @Column(name = "REVIEWNO")
    private Long reviewno = 0L;

    @Column(name = "CONTENT")
    private String content = null;

    @Column(name = "RATING")
    private long rating =  0L;

    @CreationTimestamp
    @Column(updatable = false, name = "REGDATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewdate = null;
    
    @ManyToOne
    @JoinColumn(name = "PLAYER")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "MEMBER")
    private Member member;

}
