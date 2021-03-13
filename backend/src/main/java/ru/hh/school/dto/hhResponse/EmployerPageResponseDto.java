package ru.hh.school.dto.hhResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerPageResponseDto {
    List<EmplDto> items;
    Integer pages;
    Integer per_page;
    Integer page;

    public EmployerPageResponseDto() {
    }

    public EmployerPageResponseDto(List<EmplDto> items, Integer pages, Integer per_page, Integer page) {
        this.items = items;
        this.pages = pages;
        this.per_page = per_page;
        this.page = page;
    }

    public List<EmplDto> getItems() {
        return items;
    }

    public void setItems(List<EmplDto> items) {
        this.items = items;
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



