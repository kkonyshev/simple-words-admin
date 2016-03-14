package app.model;

import javax.persistence.*;

/**
 *
 * Created by ka on 06/03/16.
 */
@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @Column
    public String value;

    @Column
    @Enumerated(EnumType.STRING)
    public WordType type;
}