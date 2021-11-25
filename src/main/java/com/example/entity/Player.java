package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@SequenceGenerator(name = "SEQ_PLAYER_NUM", sequenceName = "SEQ_PLAYER_NUM", initialValue = 1, allocationSize = 1)
@Table(name = "PLAYER")
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PLAYER_NUM")
    @Column(name = "PLAYERNO")
    private Long playerno = null;
    
    @Column(name = "PLAYERNAME")
    private String playername = null;

    @Column(name = "PLAYERAGE")
    private Long playerage = 0L;

    @Column(name = "PLAYERHEIGHT")
    private Long playerheight = 0L;

    @Column(name = "PLAYERWEIGHT")
    private Long playerweight = 0L;

    @Column(name = "PLAYERPOSITION")
    private String playerposition = null;

    @Column(name = "PLAYERCOUNTRY")
    private String playercountry = null;

    @Column(name = "PLAYERPRICE")
    private Long playerprice = 0L;

    @Lob
    @Column(name = "IMAGE")
    private byte[] image = null;

    @Column(name = "IMAGENAME")
    private String imagename = null;

    @Column(name = "IMAGETYPE")
    private String imagetype = null;
}
