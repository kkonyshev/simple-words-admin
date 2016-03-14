package app.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

/**
 * Created by ka on 06/03/16.
 */
@Entity
public class Stock {
    //@Id
    //@GeneratedValue(strategy = GenerationType.TABLE)
    //public Long id;

    @Id
    @Column
    public String sku;

    @Column
    public String name;

    @Column
    public Integer amount;
}
