package com.test.synsis.testsearchengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Data
@Embeddable
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"productId", "yearId", "clientId", "countryId"})})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesKey implements Serializable {
    @ManyToOne
    private Product productId;

    @ManyToOne
    private Year yearId;

    @ManyToOne
    private Client clientId;

    @ManyToOne
    private Country countryId;
}
