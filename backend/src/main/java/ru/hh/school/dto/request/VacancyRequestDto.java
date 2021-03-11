package ru.hh.school.dto.request;

public class VacancyRequestDto {
    private Long vacancy_id;
    private String comment;

    public Long getVacancy_id() {
        return vacancy_id;
    }

    public void setVacancy_id(Long vacancy_id) {
        this.vacancy_id = vacancy_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
