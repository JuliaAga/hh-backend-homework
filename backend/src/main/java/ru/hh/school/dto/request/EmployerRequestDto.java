package ru.hh.school.dto.request;

public class EmployerRequestDto {
    private Integer employer_id;
    private String comment;


    public Integer getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(Integer employer_id) {
        this.employer_id = employer_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
