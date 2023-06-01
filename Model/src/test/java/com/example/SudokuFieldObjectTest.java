package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldObjectTest {
    private List<SudokuField> createList() {
        List<SudokuField> list = new ArrayList<>();
        list.add(new SudokuField(1));
        list.add(new SudokuField(2));
        list.add(new SudokuField(3));
        list.add(new SudokuField(4));
        list.add(new SudokuField(5));
        list.add(new SudokuField(6));
        list.add(new SudokuField(7));
        list.add(new SudokuField(8));
        list.add(new SudokuField(9));
        return list;
    }
    private List<SudokuField> createBadList() {
        List<SudokuField> list = new ArrayList<>();
        list.add(new SudokuField(1));
        list.add(new SudokuField(2));
        list.add(new SudokuField(3));
        list.add(new SudokuField(4));
        list.add(new SudokuField(5));
        list.add(new SudokuField(6));
        list.add(new SudokuField(7));
        list.add(new SudokuField(8));
        list.add(new SudokuField(9));
        list.add(new SudokuField(10));
        return list;
    }
    @Test
    public void paramConstructor_Test() {
        SudokuField[] array = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            array[i] = new SudokuField(i);
        }
        SudokuFieldObject sudokuFieldObject = new SudokuFieldObject(Arrays.asList(array));
    }

    @Test
    public void verifyValidTest() {

        SudokuRow sudokuRow = new SudokuRow(createList());
        assertTrue(sudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        List<SudokuField> list = createList();
        list.set(2, new SudokuField(6));
        SudokuRow sudokuRow = new SudokuRow(list);
        assertFalse(sudokuRow.verify());
    }
    @Test
    public void verify_ReturnTrue_Test() {
        SudokuField[] array = new SudokuField[9];
        array[0] = new SudokuField(0);
        array[1] = new SudokuField(0);
        array[2] = new SudokuField(0);
        array[3] = new SudokuField(1);
        array[4] = new SudokuField(2);
        array[5] = new SudokuField(3);
        array[6] = new SudokuField(4);
        array[7] = new SudokuField(5);
        array[8] = new SudokuField(6);
        SudokuFieldObject object = new SudokuFieldObject(Arrays.asList(array));
        assertTrue(object.verify());
    }

    @Test
    public void verify_ReturnFalse_Test() {
        SudokuField[] array = new SudokuField[9];
        array[0] = new SudokuField(0);
        array[1] = new SudokuField(0);
        array[2] = new SudokuField(0);
        array[3] = new SudokuField(1);
        array[4] = new SudokuField(1);
        array[5] = new SudokuField(3);
        array[6] = new SudokuField(3);
        array[7] = new SudokuField(4);
        array[8] = new SudokuField(5);
        SudokuFieldObject object = new SudokuFieldObject(Arrays.asList(array));
        assertFalse(object.verify());
    }

    @Test
    public void exception_Test() {
        boolean thrown = false;

        try { SudokuRow sudokuRow = new SudokuRow(createBadList());}
        catch (SudokuFieldObjectException.BadListException e) {
            thrown = true;
        }

        assertTrue(thrown);}

    @Test
    public void toStringTest() {
        SudokuField[] array = new SudokuField[9];
        array[0] = new SudokuField(0);
        array[1] = new SudokuField(0);
        array[2] = new SudokuField(0);
        array[3] = new SudokuField(1);
        array[4] = new SudokuField(1);
        array[5] = new SudokuField(3);
        array[6] = new SudokuField(3);
        array[7] = new SudokuField(4);
        array[8] = new SudokuField(5);
        SudokuColumn sudokuColumn = new SudokuColumn(Arrays.asList(array));
        assertNotNull(sudokuColumn.toString());
    }

    @Test
    public void equalsTest() {
        SudokuField[] array = new SudokuField[9];
        array[0] = new SudokuField(0);
        array[1] = new SudokuField(0);
        array[2] = new SudokuField(0);
        array[3] = new SudokuField(1);
        array[4] = new SudokuField(1);
        array[5] = new SudokuField(3);
        array[6] = new SudokuField(3);
        array[7] = new SudokuField(4);
        array[8] = new SudokuField(5);
        SudokuColumn sudokuColumn = new SudokuColumn(Arrays.asList(array));
        SudokuField[] porownanie = new SudokuField[9];
        porownanie[0] = new SudokuField(0);
        porownanie[1] = new SudokuField(0);
        porownanie[2] = new SudokuField(0);
        porownanie[3] = new SudokuField(1);
        porownanie[4] = new SudokuField(1);
        porownanie[5] = new SudokuField(3);
        porownanie[6] = new SudokuField(3);
        porownanie[7] = new SudokuField(4);
        porownanie[8] = new SudokuField(5);
        SudokuColumn sudokuColumnPorownanie = new SudokuColumn(Arrays.asList(porownanie));
        assertTrue(sudokuColumn.equals(sudokuColumnPorownanie));
    }

    @Test
    public void hashCodeTest() {
        SudokuField[] array = new SudokuField[9];
        SudokuColumn sudokuColumn = new SudokuColumn(Arrays.asList(array));
        SudokuField[] porownanie = new SudokuField[9];
        SudokuColumn sudokuColumnPorownanie = new SudokuColumn(Arrays.asList(porownanie));
        assertEquals(sudokuColumn.hashCode(), sudokuColumnPorownanie.hashCode());
    }

    @Test
    public void equalsMethodCallByObjectForItselfTest() {
        List<SudokuField> array = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            array.set(i,new SudokuField(i));
        }
        SudokuFieldObject sudokuFieldObject = new SudokuFieldObject(array);
        assertTrue(sudokuFieldObject.equals(sudokuFieldObject));
    }

    @Test
    public void equalsCaseEqualObjectsTest() {
        List<SudokuField> array1 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            array1.set(i,new SudokuField(i));
        }
        SudokuFieldObject sudokuFieldObject1 = new SudokuFieldObject(array1);
        SudokuFieldObject sudokuFieldObject2 = sudokuFieldObject1;
        assertTrue(sudokuFieldObject1.equals(sudokuFieldObject2));
    }

    @Test
    public void equalsCaseObjectIsNull() {
        List<SudokuField> array = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            array.set(i,new SudokuField(i));
        }
        SudokuFieldObject sudokuFieldObject = new SudokuFieldObject(array);
        SudokuFieldObject nullSudokuFieldObject = null;
        assertFalse(sudokuFieldObject.equals(nullSudokuFieldObject));
    }

    @Test
    public void equalsCaseObjectIsInstanceOfDifferentClass() {
        List<SudokuField> array = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            array.set(i,new SudokuField(i));
        }
        SudokuFieldObject sudokuFieldObject = new SudokuFieldObject(array);
        SudokuFieldObject nullSudokuFieldObject = null;
        String str = "This is String Class object";
        assertFalse(sudokuFieldObject.equals(str));
    }

    @Test
    public void equalsCaseEqualFieldsTest() {
        List<SudokuField> array1 = Arrays.asList(new SudokuField[9]);
        List<SudokuField> array2 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            array1.set(i,new SudokuField(i));
            array2.set(i,new SudokuField(i));
        }
        SudokuFieldObject sudokuFieldObject1 = new SudokuFieldObject(array1);
        SudokuFieldObject sudokuFieldObject2 = new SudokuFieldObject(array2);
        assertTrue(sudokuFieldObject1.equals(sudokuFieldObject2));
    }

    @Test
    public void equalsCaseNotEqualFieldsTest() {
        List<SudokuField> array1 = Arrays.asList(new SudokuField[9]);
        List<SudokuField> array2 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            array1.set(i,new SudokuField(i));
            array2.set(i,new SudokuField(i + 1));
        }
        SudokuFieldObject sudokuFieldObject1 = new SudokuFieldObject(array1);
        SudokuFieldObject sudokuFieldObject2 = new SudokuFieldObject(array2);
        assertFalse(sudokuFieldObject1.equals(sudokuFieldObject2));
    }

    @Test
    public void hashCodeTest2() {
        List<SudokuField> array1 = Arrays.asList(new SudokuField[9]);
        List<SudokuField> array2 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            array1.set(i,new SudokuField(i));
            array2.set(i,new SudokuField(i));
        }
        SudokuFieldObject sudokuFieldObject1 = new SudokuFieldObject(array1);
        SudokuFieldObject sudokuFieldObject2 = new SudokuFieldObject(array2);
        assertEquals(sudokuFieldObject1.hashCode(), sudokuFieldObject2.hashCode());
    }

}