package com.rost.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    private int id;

    public Person() {} //must have

    @Pattern(regexp = "[A-Z]\\w+", message = "First name must begin with a capital letter and contain at least 2 characters")
    private String firstName;

    @Pattern(regexp = "[A-Z]\\w+", message = "Last name must begin with a capital letter and contain at least 2 characters")
    private String lastName;

    @Pattern(regexp = "[A-Z]\\w+", message = "Patronymic must begin with a capital letter and contain at least 2 characters")
    private String patronymic;

    @Min(value = 1910, message = "Birth year should be correct")
    @Max(value = 2009, message = "Birth year should be correct")
    private int birthYear;

    public String getFullName() {
        return String.format("%s %s %s", lastName, firstName, patronymic);
    }
}