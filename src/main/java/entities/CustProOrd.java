package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class CustProOrd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cu_id;

    private String cu_name;
    private String cu_surname;
    private int fis_no;
    private String ptitle;
    private int or_size;
    private int selprice;
    private int or_id;

}
