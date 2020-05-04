package de.midorlo.relentless.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CellTypeTest {

    CellType ct1;
    CellType ct2;

    @BeforeMethod
    public void setup() {
        ct1 = new CellType("1");
        ct2 = new CellType("2");
    }

    @Test
    public void testTestEquals() {
        assertFalse(ct1.equals(ct2));
        ct1.setName(ct2.getName());
        assertTrue(ct1.equals(ct2));

    }
}