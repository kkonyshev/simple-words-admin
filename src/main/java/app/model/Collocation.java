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
@Table(name="collocation", uniqueConstraints = {@UniqueConstraint(columnNames={"type", "value"})})
public class Collocation {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @Column(unique = true)
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
