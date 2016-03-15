package app.model;

import app.ObjectPrinter;

import javax.persistence.*;

/**
 *
 * Created by ka on 06/03/16.
 */
@Entity
@Table(name="word", uniqueConstraints = {@UniqueConstraint(columnNames={"type", "value"})})
public class Word {
  @Id
  @Column(unique = true)
  @GeneratedValue(strategy = GenerationType.TABLE)
  public Long id;

  @Column(unique = true)
  public String value;

  @Column
  @Enumerated(EnumType.STRING)
  public WordType type;

  @Column
  public Integer viewCount = 0;

  @Override
  public String toString() {
    return new ObjectPrinter().merge(id, type);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Word)) {
      return false;
    }
    Word word = (Word) obj;
    if (!word.type.equals(type)) {
      return false;
    }
    if (!word.value.equalsIgnoreCase(value)) {
      return false;
    }
    if (!word.id.equals(id)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return id.hashCode()+type.hashCode()+value.hashCode();
  }
}
