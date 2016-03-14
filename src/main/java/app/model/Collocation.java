package app.model;

import javax.persistence.*;

/**
 *
 * Created by ka on 06/03/16.
 */
@Entity
public class Collocation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @Column
    public String value;

    @Column
    @Enumerated(EnumType.STRING)
    public CollocationType type;
}
