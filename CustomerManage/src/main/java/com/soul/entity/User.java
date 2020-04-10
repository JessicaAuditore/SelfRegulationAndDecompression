package com.soul.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "sex")
    private String sex;

    @Basic
    @Column(name = "age")
    private String age;

    @Basic
    @Column(name = "question")
    private String question;

    @Basic
    @Column(name = "answer")
    private String answer;
}
