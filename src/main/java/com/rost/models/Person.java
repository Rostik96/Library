package com.rost.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    private int id;

    @Pattern(regexp = "[A-Z[А-Я]]\\w+", message = "First name must begin with a capital letter and contain at least 2 characters")
    private String firstName;

    @Pattern(regexp = "[A-Z[А-Я]]\\w+", message = "Last name must begin with a capital letter and contain at least 2 characters")
    private String lastName;

    @Pattern(regexp = "[A-Z[А-Я]]\\w+", message = "Patronymic must begin with a capital letter and contain at least 2 characters")
    private String patronymic;

    @Pattern(regexp = "\\d{4}", message = "Birth year must have four digits")
    private int birthYear;

    private String getFullName() {
        return String.format("%s %s %s", lastName, firstName, patronymic);
    }
}