package ru.hh.school.entity;

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

    @Column(name = "date_create")
    LocalDate dateCreate;

    @Enumerated(EnumType.STRING)
    Popularity popularity;

    @Column(name = "views_count")
    Integer viewsCount;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
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
