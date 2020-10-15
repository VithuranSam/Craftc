package com.squadron.craftcreatures;

import com.squadron.craftcreatures.Employee.EmployeeActivity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void cal_tax_test(){
        int result = EmployeeActivity.cal_tax(200000);
        assertEquals( 190000,result);

        int result2 = EmployeeActivity.cal_tax(100000);
        assertEquals( 100000,result2);
    }


}