package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class CashOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cout_id", nullable = false)
    private Integer cout_id;

    private String payOutDetail;
    private int payOutTotal;
    private String payOutTitle;
    private int payOutType;
    @Temporal(TemporalType.DATE)
    private Date odate=new Date();

}
