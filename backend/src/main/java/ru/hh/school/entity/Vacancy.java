package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "fav_vacancies")
public class Vacancy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "hh_id")
  private Long hhId;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area")
  private Area area;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="employer")
  private Employer employer;

  @OneToOne
  @JoinColumn(name = "salary")
  private Salary salary;

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

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public Long getHhId() {
    return hhId;
  }

  public void setHhId(Long hhId) {
    this.hhId = hhId;
  }

  public Salary getSalary() {
    return salary;
  }

  public void setSalary(Salary salary) {
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vacancy that = (Vacancy) o;
    return id.equals(that.id) &&
            name.equals(that.name) &&
            Objects.equals(salary, that.salary) &&
            Objects.equals(area, that.area) &&
            Objects.equals(employer, that.employer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, salary, area, employer);
  }
}
