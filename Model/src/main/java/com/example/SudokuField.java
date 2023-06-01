package com.example;


import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {

    private int value;

    public SudokuField(int value) {
        this.value = value;
    }

    public SudokuField() {
        this.value = 0;
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int setValue) {
        this.value = setValue;
    }

    boolean isEmptyField;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Wartość:", value)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return new EqualsBuilder().append(value, ((SudokuField) obj).getFieldValue()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value).hashCode();
    }

    public boolean isEmptyField() {
        return isEmptyField;
    }

    public void setEmptyField() {
        isEmptyField = true;
    }

    @Override
    public int compareTo(SudokuField o) {
        if (o == null) {
            throw new SudokuFieldException.SudokuFieldNullPointerException(null);
        }
        return Integer.compare(this.getFieldValue(), o.getFieldValue());
    }

    @Override
    public Object clone() throws SudokuFieldException.SudokuFieldCloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new SudokuFieldException.SudokuFieldCloneNotSupportedException(null);
        }
    }
}

