package ru.hh.school.entity;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "hh_id")
    private Long hhId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHhId() {
        return hhId;
    }

    public void setHhId(Long hhId) {
        this.hhId = hhId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
