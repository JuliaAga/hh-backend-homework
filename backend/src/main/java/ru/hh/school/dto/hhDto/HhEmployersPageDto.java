package ru.hh.school.dto.hhDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HhEmployersPageDto {
    List<HhEmplDto> items;
    Integer pages;
    Integer per_page;
    Integer page;

    public HhEmployersPageDto() {
    }

    public HhEmployersPageDto(List<HhEmplDto> items, Integer pages, Integer per_page, Integer page) {
        this.items = items;
        this.pages = pages;
        this.per_page = per_page;
        this.page = page;
    }

    public List<HhEmplDto> getItems() {
        return items;
    }

    public void setItems(List<HhEmplDto> items) {
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
