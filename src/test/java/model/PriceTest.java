package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PriceTest {
    Sandwich sandwich1;
    Sandwich sandwich2;
    Sandwich sandwich3;
    Burger singlePattyBurger;
    Burger doublePattyBurger;


    @Before
    public void setUp(){
        ArrayList<Addons> otl = new ArrayList<>();
        otl.add(Addons.ONIONS); otl.add(Addons.TOMATOES); otl.add(Addons.LETTUCE);
        sandwich1 = new Sandwich(Bread.BRIOCHE, Protein.CHICKEN, otl, 1); //8.99 + 0.30 + 0.30 + 0.30

        ArrayList<Addons> oac = new ArrayList<>();
        oac.add(Addons.ONIONS); oac.add(Addons.AVOCADO); oac.add(Addons.CHEESE);
        sandwich2 = new Sandwich(Bread.WHEAT_BREAD, Protein.ROAST_BEEF, oac, 1); //10.99 + 0.30 + 0.50 + 1.00

        ArrayList<Addons> cheese = new ArrayList<>();
        cheese.add(Addons.CHEESE);
        sandwich3 = new Sandwich(Bread.BAGEL, Protein.SALMON, cheese, 1); //9.99 + 1.00

        singlePattyBurger = new Burger(Bread.BRIOCHE, Protein.BEEF_PATTY, otl, false, 1); //6.99 + 0.30 + 0.30 + 0.30

        doublePattyBurger = new Burger(Bread.PRETZEL, Protein.BEEF_PATTY, oac, true, 1); //6.99 + 2.50 + 0.30 + 0.50 + 1.00
    }

    @Test
    public void testPriceOfSandwich1(){
        double price = sandwich1.price();
        double expected = 8.99 + 0.30 + 0.30 + 0.30;
        assertEquals(expected, price, 0.01);
    }

    @Test
    public void testPriceOfSandwich2(){
        double price = sandwich2.price();
        double expected = 10.99 + 0.30 + 0.50 + 1.00;
        assertEquals(expected, price, 0.01);
    }

    @Test
    public void testPriceOfSandwich3(){
        double price = sandwich3.price();
        double expected = 9.99 + 1.00;
        assertEquals(expected, price, 0.01);
    }

    @Test
    public void testPriceOfSinglePattyBurger(){
        double price = singlePattyBurger.price();
        double expected = 6.99 + 0.30 + 0.30 + 0.30;
        assertEquals(expected, price, 0.01);
    }

    @Test
    public void testPriceOfDoublePattyBurger(){
        double price = doublePattyBurger.price();
        double expected = 6.99 + 2.50 + 0.30 + 0.50 + 1.00;
        assertEquals(expected, price, 0.01);
    }

}