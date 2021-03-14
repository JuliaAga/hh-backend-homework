package ru.hh.school.dto;

import ru.hh.school.entity.Employer;

import java.util.List;

public class EmployerDto {

    List<Employer> employerList;
    Integer pages;
    Integer per_page;
    Integer page;

    public EmployerDto() {
    }

    public EmployerDto(List<Employer> employerList, Integer pages, Integer per_page, Integer page) {
        this.employerList = employerList;
        this.pages = pages;
        this.per_page = per_page;
        this.page = page;
    }

    public List<Employer> getEmployerList() {
        return employerList;
    }

    public void setEmployerList(List<Employer> employerList) {
        this.employerList = employerList;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
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
