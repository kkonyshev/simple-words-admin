package app.model;

import app.ObjectPrinter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    public List<Word> words = new ArrayList<>();

    @Column
    public Integer viewCount = 0;

    @Override
    public String toString() {
        return new ObjectPrinter().merge(id, type, words);
    }
}
