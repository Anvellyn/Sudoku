package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    public void defConstructor_Test() {
        SudokuField sudokuField = new SudokuField();
        assertEquals(sudokuField.getFieldValue(), 0);
    }

    @Test
    public void paramConstructor_Test() {
        SudokuField sudokuField = new SudokuField(1);
        assertEquals(sudokuField.getFieldValue(), 1);
    }

    @Test
    public void setter_Test() {
        SudokuField sudokuField = new SudokuField();
        sudokuField.setFieldValue(9);
        assertEquals(sudokuField.getFieldValue(), 9);
    }

    @Test
    public void toStringTest() {
        SudokuField sudokuField = new SudokuField();
        assertNotNull(sudokuField.toString());
    }

    @Test
    public void equalsTest() {
        SudokuField sudokuField = new SudokuField();
        sudokuField.setFieldValue(9);
        SudokuField porownanie = new SudokuField();
        porownanie.setFieldValue(9);
        assertTrue(sudokuField.equals(porownanie));
    }

    @Test
    public void hashCodeTest() {
        SudokuField sudokuField = new SudokuField();
        SudokuField porownanie = new SudokuField();
        assertEquals(sudokuField.hashCode(), porownanie.hashCode());
    }
    @Test
    public void equalsMethodCallByObjectForItselfTest() {
        SudokuField sudokuField = new SudokuField(1);
        assertTrue(sudokuField.equals(sudokuField));
    }

    @Test
    public void equalsCaseEqualObjectsTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = sudokuField1;
        assertTrue(sudokuField1.equals(sudokuField2));
    }

    @Test
    public void equalsCaseObjectIsInstanceOfDifferentClass() {
        SudokuField sudokuField = new SudokuField(1);
        String str = "This is String Class object";
        assertFalse(sudokuField.equals(str));
    }

    @Test
    public void equalsCaseObjectIsNull() {
        SudokuField sudokuField = new SudokuField(1);
        SudokuField nullSudokuField = null;
        assertFalse(sudokuField.equals(nullSudokuField));
    }

    @Test
    public void equalsCaseEqualFieldsTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);
        assertTrue(sudokuField1.equals(sudokuField2));
        assertTrue(sudokuField2.equals(sudokuField1));
    }

    @Test
    public void equalsCaseEqualFieldsTestAndHashCode() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);
        assertTrue(sudokuField1.equals(sudokuField2));
        assertTrue(sudokuField2.equals(sudokuField1));
        assertEquals(sudokuField1.hashCode(), sudokuField2.hashCode());
    }

    @Test
    public void equalsCaseNotEqualFieldsTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(2);
        assertFalse(sudokuField1.equals(sudokuField2));
    }

    @Test
    public void equalsCaseChangedClassFieldsInitiallyEqualTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);
        assertTrue(sudokuField1.equals(sudokuField2));
        sudokuField2.setFieldValue(2);
        assertFalse(sudokuField2.equals(sudokuField1));
    }

    @Test
    public void equalsCaseChangedClassFieldsInitiallyNotEqualTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(2);
        assertFalse(sudokuField1.equals(sudokuField2));
        sudokuField2.setFieldValue(1);
        assertTrue(sudokuField2.equals(sudokuField1));
    }

    @Test
    public void hashCodeCaseEqualObjectFieldsTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);
        assertEquals(sudokuField1.hashCode(), sudokuField2.hashCode());
    }

    @Test
    public void hashCodeCaseChangedClassFieldsInitiallyNotEqualTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(2);
        sudokuField2.setFieldValue(1);
        assertEquals(sudokuField1.hashCode(), sudokuField2.hashCode());
    }

    @Test
    public void ShouldHashCodeNotChangeWhenCalledMultipleTimesForSameUnchangedObjectTest() {
        SudokuField sudokuField = new SudokuField(1);
        int hashCode = sudokuField.hashCode();
        assertEquals(sudokuField.hashCode(), hashCode);
    }

    @Test
    public void ShouldHashCodeChangeWhenCalledMultipleTimesForSameChangedObjectTest() {
        SudokuField sudokuField = new SudokuField(1);
        int hashCode = sudokuField.hashCode();
        sudokuField.setFieldValue(2);
        assertNotEquals(sudokuField.hashCode(), hashCode);
    }
    @Test
    public void checkClone() throws SudokuFieldException.SudokuFieldCloneNotSupportedException {
        SudokuField f1 = new SudokuField();
        f1.setFieldValue(140);
        SudokuField f2 = (SudokuField) f1.clone();
        assertEquals(f2.getFieldValue(), 140);
        assertTrue(f2.equals(f1));
        assertTrue(f1.equals(f2));
        assertEquals(f1.compareTo(f2), 0);
        assertEquals(f2.compareTo(f1), 0);
    }

    @Test
    public void checkCompare() {
        SudokuField field = new SudokuField(2);
        SudokuField field2 = new SudokuField(3);
        SudokuField field3 = new SudokuField(2);
        assertTrue(field2.compareTo(field) > 0);
        assertTrue(field.compareTo(field2) < 0);
        assertEquals(0, field3.compareTo(field));
    }


    @Test
    public void checkCompareException() {
        SudokuField field = new SudokuField(2);
        assertThrows(SudokuFieldException.SudokuFieldNullPointerException.class, ()->{field.compareTo(null);});
    }
}