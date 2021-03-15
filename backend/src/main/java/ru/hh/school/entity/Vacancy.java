package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "fav_vacancies")
public class Vacancy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Column(name = "hh_id")
  private Integer hhId;

  private String name;

  @ManyToOne()
  @JoinColumn(name = "area")
  private Area area;

  @ManyToOne()
  @JoinColumn(name="employer")
  private Employer employer;

  @OneToOne
  @JoinColumn(name = "salary")
  private Salary salary;

  String comment;
  LocalDate date_create;
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

  public Integer getHhId() {
    return hhId;
  }

  public void setHhId(Integer hhId) {
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
    Vacancy vacancy = (Vacancy) o;
    return hhId.equals(vacancy.hhId) &&
            name.equals(vacancy.name) &&
            Objects.equals(area, vacancy.area) &&
            Objects.equals(employer, vacancy.employer) &&
            Objects.equals(salary, vacancy.salary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hhId, name, area, employer, salary);
  }
}
