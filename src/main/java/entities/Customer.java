package entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cu_id;

    private String cu_name;
    private String cu_surname;
    private String cu_company_title;
    private long cu_code;
    private int cu_status;
    private int cu_tax_number;
    private String cu_tax_administration;
    @Column(length = 500)
    private String cu_address;
    private String cu_mobile;
    private String cu_phone;
    @Column(length = 500)
    private String cu_email;
    @Column(length = 32)
    private String cu_password;



}
