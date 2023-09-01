package com.microservices.departmentservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "departmentservice")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;
    private String departmentName;
    private String departmentCode;

}
