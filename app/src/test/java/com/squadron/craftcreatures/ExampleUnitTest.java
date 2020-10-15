package com.squadron.craftcreatures;

import com.squadron.craftcreatures.Crafts.CraftActivity;
import com.squadron.craftcreatures.Delivery.DeliveryActivity;
import com.squadron.craftcreatures.Makers.MakerActivity;
import com.squadron.craftcreatures.Employee.EmployeeActivity;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private MakerActivity makerActivity;
    private CraftActivity craftActivity;
    private DeliveryActivity deliveryActivity;


    public void setUp(){
        makerActivity = new MakerActivity();
        craftActivity = new CraftActivity();
        deliveryActivity = new DeliveryActivity();
    }


    //Maker
    @Test
    public void MakerCalculation_isCorrect(){
        double buying = makerActivity.calculate(30,20);
        assertEquals(600,buying,0.001);
    }

    @Test
    public void MakerCalculation_isWrong(){
        double buying = makerActivity.calculate(20,20);
        assertEquals(600,buying,0.001);
    }



    //Craft
    @Test
    public void CraftCalculation_isCorrect(){
        int profit_craft = craftActivity.calculate(200,100);
        assertEquals(100,profit_craft);
    }
    @Test
    public void CraftCalculation_isWrong(){
        int profit_craft = craftActivity.calculate(200,100);
        assertEquals(300,profit_craft,0.001);
    }

    //Delivery
    @Test
    public void DeliveyCalculation_isCorrect(){
        int crashselling = deliveryActivity.calculate(20,100);
        assertEquals(2000,crashselling,0.001);
    }
    @Test
    public void DeliveyCalculation_isWrong() {
        int crashselling = deliveryActivity.calculate(20, 100);
        assertEquals(3000, crashselling, 0.001);
    }


    //Employee
    @Test
    public void cal_tax_test(){
        int result = EmployeeActivity.cal_tax(200000);
        assertEquals( 190000,result);

        int result2 = EmployeeActivity.cal_tax(100000);
        assertEquals( 100000,result2);
}


}