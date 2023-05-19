//package com.four.simple.user;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false, unique = true)
//    private long id;
//
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @Column(name = "username", nullable = false)
//    private String username;
//
//    @Column(name = "first_name", nullable = false)
//    private String firstname;
//
//    @Column(name = "last_name", nullable = false)
//    private String lastname;
//}
