package com.test.synsis.testsearchengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Year {

    @Id
//    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int sortId;
}
