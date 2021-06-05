package com.test.synsis.testsearchengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Client {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;
}
