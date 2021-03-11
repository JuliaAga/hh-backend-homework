package ru.hh.school.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "fav_employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hh_id")
    private Long hhId;

    private String name;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "area")
    private Area area;

    String comment;
    LocalDateTime date_create;
    Popularity popularity;
    Integer views_count;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate_create() {
        return date_create;
    }

    public void setDate_create(LocalDateTime date_create) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer that = (Employer) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, area);
    }
}
