package com.test.synsis.testsearchengine.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Sales {

    @EmbeddedId
    private SalesKey id;

    @Column
    private Integer count;

    @Column
    private Double totalCost;

    public Sales(SalesKey id, Integer count, Double totalCost) {
        this.id = id;
        this.count = count;
        this.totalCost = totalCost;
    }

    public Sales() {
    }

    public static SalesBuilder builder() {
        return new SalesBuilder();
    }

    public SalesKey getId() {
        return this.id;
    }

    public Integer getCount() {
        return this.count;
    }

    public Double getTotalCost() {
        return this.totalCost;
    }

    public void setId(SalesKey id) {
        this.id = id;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Sales)) return false;
        final Sales other = (Sales) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$count = this.getCount();
        final Object other$count = other.getCount();
        if (this$count == null ? other$count != null : !this$count.equals(other$count)) return false;
        final Object this$totalCost = this.getTotalCost();
        final Object other$totalCost = other.getTotalCost();
        if (this$totalCost == null ? other$totalCost != null : !this$totalCost.equals(other$totalCost)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Sales;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $count = this.getCount();
        result = result * PRIME + ($count == null ? 43 : $count.hashCode());
        final Object $totalCost = this.getTotalCost();
        result = result * PRIME + ($totalCost == null ? 43 : $totalCost.hashCode());
        return result;
    }

    public String toString() {
        return "Sales(id=" + this.getId() + ", count=" + this.getCount() + ", totalCost=" + this.getTotalCost() + ")";
    }

    public static class SalesBuilder {
        private SalesKey id;
        private Integer count;
        private Double totalCost;

        SalesBuilder() {
        }

        public Sales.SalesBuilder id(SalesKey id) {
            this.id = id;
            return this;
        }

        public Sales.SalesBuilder count(Integer count) {
            this.count = count;
            return this;
        }

        public Sales.SalesBuilder totalCost(Double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public Sales build() {
            return new Sales(id, count, totalCost);
        }

        public String toString() {
            return "Sales.SalesBuilder(id=" + this.id + ", count=" + this.count + ", totalCost=" + this.totalCost + ")";
        }
    }
}
