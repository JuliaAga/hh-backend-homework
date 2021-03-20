package ru.hh.school.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "hh_id")
    private Integer hhId;

    private String name;

    public Area() {
    }

    public Area(Integer hhId, String name) {
        this.hhId = hhId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHhId() {
        return hhId;
    }

    public void setHhId(Integer hhId) {
        this.hhId = hhId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return hhId.equals(area.hhId) &&
                name.equals(area.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hhId, name);
    }
}
