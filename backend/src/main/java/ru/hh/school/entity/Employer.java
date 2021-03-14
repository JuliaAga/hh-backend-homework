package ru.hh.school.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "fav_employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hh_id")
    private Integer hhId;

    private String name;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "area")
    private Area area;

    String comment;
    LocalDate date_create;

    @Enumerated(EnumType.STRING)
    Popularity popularity;

    Integer views_count;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate_create() {
        return date_create;
    }

    public void setDate_create(LocalDate date_create) {
        this.date_create = date_create;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public Integer getViews_count() {
        return views_count;
    }

    public void setViews_count(Integer views_count) {
        this.views_count = views_count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getHhId() {
        return hhId;
    }

    public void setHhId(Integer hhId) {
        this.hhId = hhId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return hhId.equals(employer.hhId) &&
                name.equals(employer.name) &&
                Objects.equals(description, employer.description) &&
                Objects.equals(area, employer.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hhId, name, description, area);
    }
}
