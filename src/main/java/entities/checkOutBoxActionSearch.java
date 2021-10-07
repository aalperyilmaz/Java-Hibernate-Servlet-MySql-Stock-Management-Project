package entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class checkOutBoxActionSearch {
    @Id
    private int cashincustomer_id;
    private int cu_id;
    private String cu_name;
    private String cu_surname;
    private String cu_company_title;
    private int cu_code;
    private int cu_mobile;
    private String cu_email;
}
