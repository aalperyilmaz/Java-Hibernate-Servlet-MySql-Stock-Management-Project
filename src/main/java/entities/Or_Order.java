package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Or_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "or_id", nullable = false)
    private Integer or_id;

   private int fis_no;
   private int or_size;
   private int status;
   private  int total;
   @Temporal(TemporalType.DATE)
   private Date date;


   @OneToOne(cascade = CascadeType.DETACH)
    private Customer customer;
   @OneToOne(cascade = CascadeType.DETACH)
   private  Product product;


}
