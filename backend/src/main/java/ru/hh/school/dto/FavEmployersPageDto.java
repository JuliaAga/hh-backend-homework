package ru.hh.school.dto;

import ru.hh.school.entity.Employer;

import java.util.List;

public class FavEmployersPageDto {

    List<Employer> employers;
    Integer per_page;
    Integer page;

    public FavEmployersPageDto() {
    }

    public FavEmployersPageDto(List<Employer> employers, Integer per_page, Integer page) {
        this.employers = employers;
        this.per_page = per_page;
        this.page = page;
    }

    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
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
