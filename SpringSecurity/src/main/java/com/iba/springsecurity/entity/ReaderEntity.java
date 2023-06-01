package com.iba.springsecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Table(name="reader")
@Entity
public class ReaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min=2, max=100, message = "Full name can have minimum 2 symbols and maximum 100")
    @Column(name = "FIO")
    @Getter
    @Setter
    private String FIO;

    @Min(value = 1900, message = "Year of birth must be more than 1900")
    @Column(name = "yearOfBirth")
    @Getter
    @Setter
    private int yearOfBirth;

    @Column(name = "phone_number")
    @Size(min=7, max=25, message = "Number can have minimum 7 symbols and maximum 20")
    @Getter
    @Setter
    private String phone_number;

    public ReaderEntity(){};

    public ReaderEntity(String FIO, int yearOfBirth, String phone_number) {
        this.FIO = FIO;
        this.yearOfBirth = yearOfBirth;
        this.phone_number = phone_number;
    };

    @Override
    public String toString() {
        return "ReaderEntity{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
