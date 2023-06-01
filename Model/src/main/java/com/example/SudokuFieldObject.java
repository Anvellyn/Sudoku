package com.example;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuFieldObject implements Serializable, Cloneable {
    private List<SudokuField> objectField;


    public SudokuFieldObject(List<SudokuField> array) {
        if (array.size() != 9) {
            throw new SudokuFieldObjectException.BadListException(null);
        }
            this.objectField = array;
    }

    public boolean verify() {
        int[] tab = new int[10];
        for (int i = 0; i < 9; i++) {
            tab[objectField.get(i).getFieldValue()]++;
        }
        for (int i = 1; i < 10; i++) {
            if (tab[i] > 1) {
                return false;
            }
        }
        return true;
    }

    public SudokuField get(int index) {
        return objectField.get(index);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Wartość:", objectField)
                .toString();
    }

   @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SudokuFieldObject)) {
            return false;
        }
        return new EqualsBuilder()
                .append(objectField, ((SudokuFieldObject) obj).objectField).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(objectField).hashCode();
    }
}
