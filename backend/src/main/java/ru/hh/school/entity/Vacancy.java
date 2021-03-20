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
  @JoinColumn(name = "employer")
  private Employer employer;

  @OneToOne
  @JoinColumn(name = "salary")
  private Salary salary;

  String comment;

  @Column(name = "date_create")
  LocalDate dateCreate;

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
