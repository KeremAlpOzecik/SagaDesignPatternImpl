package com.keremalp.hgsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hgs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hgs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerNumber;
    private Long accountNumber;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String address;
    private String phoneNumber;
    private String carPlate;
    @Enumerated(EnumType.STRING)
    private HgsStatus hgsStatus;

    public enum HgsStatus {
        CREATE,
        NOT_CREATE
    }
}
