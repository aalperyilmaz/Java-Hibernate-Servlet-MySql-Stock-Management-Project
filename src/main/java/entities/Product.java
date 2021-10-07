package entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int pid;



private int buyprice;
private int selprice;
private int pcode;
private int ptax;
private int psection;
private int size;

@Column(length = 255)
private String pdetail;
    @Column(length = 255)
private String ptitle;



}
