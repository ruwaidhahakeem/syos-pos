package factories;

import models.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemFactoryTest {
    private ItemFactory itemFactory;
    @BeforeEach
    void setUp() {
        itemFactory= new ItemConcreteFactory();
    }

    @AfterEach
    void tearDown() {
        itemFactory = null;
    }

    @Test
    void CreateFreshProduce() {
        Item item = itemFactory.createItem("FP001", "Carrots", 1.99, "Organic", "BC001", "Fresh Produce");
        assertTrue(item instanceof FreshProduce);
        assertEquals("FP001", item.getItemCode());
        assertEquals("Carrots", item.getItemName());
        assertEquals(1.99, item.getPrice());
        assertEquals("Organic", item.getVariation());
        assertEquals("BC001", item.getBatchCode());
        assertEquals("Fresh Produce", item.getItemType());
    }

    @Test
    void CreateTinnedGoods() {
        Item item = itemFactory.createItem("TG001", "Beans", 300, "Long", "BC002", "Canned Items");
        assertInstanceOf(TinnedGoods.class, item);
        assertEquals("TG001", item.getItemCode());
        assertEquals("Beans", item.getItemName());
        assertEquals(300, item.getPrice());
        assertEquals("Long", item.getVariation());
        assertEquals("BC002", item.getBatchCode());
        assertEquals("Canned Items", item.getItemType());
    }


    @Test
    void testCreateUnknownType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            itemFactory.createItem("TG001", "Beans", 300, "Long", "BC002", "Not Categorized");
        });

        String expectedMessage = "Unknown item type: Not Categorized"; // Update to match the actual message
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void CreateItemWithUniqueItemCodeAndBatchCode() {
        Item item1 = itemFactory.createItem("TG001", "Beans", 300, "Long", "BC002", "Canned Items");
        assertNotNull(item1);

        Item item2 = itemFactory.createItem("TG001", "Beans", 300, "Long", "BC003", "Canned Items");
        assertNotNull(item2);
    }

    @Test
    void testNegativePrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            itemFactory.createItem("FP001", "Carrots", -300, "Organic", "BC001", "Fresh Produce");
        });

        String expectedMessage = "Price can't' be negative: -300";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void DuplicateItemCodeAndBatchCode() {
        itemFactory.createItem("TG001", "Beans", 300, "Long", "BC002", "Canned Items");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            itemFactory.createItem("TG001", "Beans", 300, "Long", "BC002", "Canned Items");
        });

        String expectedMessage = "Item with code TG001 and batch code BC002 already exists.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



//    @Test
//    void DuplicateItemCodeAndBatchCode() {
//        itemFactory.createItem("TG001", "Beans", 300, "Long", "BC002", "Canned Items");
//
//        Exception exception = assertThrows(DuplicateItemException.class, () -> {
//            itemFactory.createItem("TG001", "Beans", 300, "Long", "BC002", "Canned Items");
//        });
//
//        String expectedMessage = "Item with code TG001 and batch code BC002 already exists.";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }

//@Test
//void CreateSameBatchCode() {
//    // Create the first item
//    Item item1 = itemFactory.createItem("TG001", "Beans", 300, "Long", "BC002", "Canned Items");
//    assertTrue(item1 instanceof TinnedGoods);
//    assertEquals("TG001", item1.getItemCode());
//    assertEquals("Beans", item1.getItemName());
//    assertEquals(300, item1.getPrice());
//    assertEquals("Long", item1.getVariation());
//    assertEquals("BC002", item1.getBatchCode());
//    assertEquals("Canned Items", item1.getItemType());
//
//    // Check if the item was added to the database
//    assertTrue(itemDatabase.contains(item1));
//
//    // Attempt to create the second item with a similar batch code
//    Item item2 = itemFactory.createItem("TG-001", "Beans", 300, "Long", "BC002", "Canned Items");
//
//    // Depending on your implementation, handle the expected outcome
//    // If the system should throw an exception, check for that
//    try {
//        itemFactory.createItem("TG-001", "Beans", 300, "Long", "BC002", "Canned Items");
//        fail("Expected an exception for duplicate batch code");
//    } catch (DuplicateBatchCodeException e) {
//        // Test passes if this exception is thrown
//    }
//
//    // Or if the system silently prevents adding the second item, check the database content
//    assertFalse(itemDatabase.contains(item2));
//}
}


