package ru.hh.school.dto.hhDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.SalaryDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HhVacancyDto {
    private Integer id;
    private String name;
    private String description;
    private AreaDto area;
    private HhEmplDto employer;
    private SalaryDto salary;

    public HhVacancyDto() {
    }

    public HhVacancyDto(Integer id, String name, String description, AreaDto area, HhEmplDto employer, SalaryDto salary) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
        this.employer = employer;
        this.salary = salary;
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

    public AreaDto getArea() {
        return area;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

    public HhEmplDto getEmployer() {
        return employer;
    }

    public void setEmployer(HhEmplDto employer) {
        this.employer = employer;
    }

    public SalaryDto getSalary() {
        return salary;
    }

    public void setSalary(SalaryDto salary) {
        this.salary = salary;
    }
}
