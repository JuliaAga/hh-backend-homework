package ru.hh.school.dto;

import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;

import java.util.List;

public class VacancyDto {

    List<Vacancy> vacancies;
    Integer per_page;
    Integer page;

    public VacancyDto() {
    }

    public VacancyDto(List<Vacancy> vacancies, Integer per_page, Integer page) {
        this.vacancies = vacancies;
        this.per_page = per_page;
        this.page = page;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
