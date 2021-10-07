package entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class CashIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_id", nullable = false)
    private Integer cash_id;
    private int cu_id;
    private int or_id;
    private int cash_status;
    private String payInDetail;
    private int payInTotal;

    @Temporal(TemporalType.DATE)
    private Date pdate = new Date();;

    @OneToOne
    private Or_Order order;


}
