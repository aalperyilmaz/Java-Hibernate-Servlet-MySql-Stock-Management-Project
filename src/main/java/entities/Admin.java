package entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid", nullable = false)
    private Integer aid;

    private String name;
    private String email;

    @Column(length = 32)
    private String password;
}
