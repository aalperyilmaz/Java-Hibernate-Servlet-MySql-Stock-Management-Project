package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
public class OrderMngrObj {

    private int fis_no;
    private int or_size;

    private String customer;

    private  String product;

}
