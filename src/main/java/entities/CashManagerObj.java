package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class CashManagerObj {
    @Id

    private int cu_id;
    private int fis_no;
    private int payInTotal;
    private String payInDetail;

}
