package tests;

import factories.ItemConcreteFactory;
import factories.ItemFactory;
import models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemFactoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    //tests

    //below are tests for every item type
    //items are NOT added to db, just verifies creation of item object
    @Test
    void freshProduceItem() {
        ItemFactory factory = new ItemConcreteFactory();
        Item item = factory.createItem("001", "Apple", 0.99, "Organic", "B001", "Fresh Produce");

        assertNotNull(item);
        assertTrue(item instanceof freshProduce);
        assertEquals("001", item.getItemCode());
        assertEquals("Apple", item.getItemName());
        assertEquals(0.99, item.getPrice());
        assertEquals("Organic", item.getVariation());
        assertEquals("B001", item.getBatchCode());
        assertEquals("Fresh Produce", item.getItemType());
    }

    @Test
    void packedGoodsItem() {
        ItemFactory factory = new ItemConcreteFactory();
        Item item = factory.createItem("002", "Canned Beans", 1.50, "500g", "B002", "Packed Goods");

        assertNotNull(item);
        assertTrue(item instanceof packedGoods);
        assertEquals("002", item.getItemCode());
        assertEquals("Canned Beans", item.getItemName());
        assertEquals(1.50, item.getPrice());
        assertEquals("500g", item.getVariation());
        assertEquals("B002", item.getBatchCode());
        assertEquals("Packed Goods", item.getItemType());
    }

    @Test
    void dairyAndMeatItem() {
        ItemFactory factory = new ItemConcreteFactory();
        Item item = factory.createItem("003", "Milk", 1.20, "1L", "B003", "Dairy And Meat");

        assertNotNull(item);
        assertTrue(item instanceof dairyAndMeat);
        assertEquals("003", item.getItemCode());
        assertEquals("Milk", item.getItemName());
        assertEquals(1.20, item.getPrice());
        assertEquals("1L", item.getVariation());
        assertEquals("B003", item.getBatchCode());
        assertEquals("Dairy And Meat", item.getItemType());
    }

    //check how the factory pattern handles null scenarios, where
    //invalid type is not defined in the factory, item should be null to pass
    @Test
    void invalidItemType() {
        ItemFactory factory = new ItemConcreteFactory();
        Item item = factory.createItem("004", "Invalid Item", 2.00, "Unknown", "B004", "Invalid Type");

        assertNull(item);

    }

    @Test
    void emptyValues(){
        //no item code
        ItemFactory factory =new ItemConcreteFactory();

        Item itemNoCode=factory.createItem("", "Item", 1.00, "Type", "B001", "Fresh Produce");
        assertNull(itemNoCode);

        Item itemNoName= factory.createItem("NN1", "", 35.00, "N/A", "N001", "Dairy And Meat");
        assertNull(itemNoName);

        //add more tests for other null values
    }


}


