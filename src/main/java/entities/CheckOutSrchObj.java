package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
public class CheckOutSrchObj {
    @Id
    private int cname;
    private int ctype;


   private Date startDate;

  private Date  endDate;

}
