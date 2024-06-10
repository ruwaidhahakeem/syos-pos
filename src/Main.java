import dao.ItemDAO;
import dao.ItemDAOImpl;
import factories.ItemFactory;
import factories.ItemConcreteFactory;
import models.Item;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItemFactory itemFactory = new ItemConcreteFactory();
        ItemDAO itemDAO = new ItemDAOImpl();

        while (true) {
            System.out.println("1. Add Item");
            System.out.println("2. View Item");
            System.out.println("3. Update Item");
            System.out.println("2. Delete Item");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addItem(scanner, itemFactory, itemDAO);
                    break;
                case 2:
                    viewItem(scanner, itemDAO);
                    break;
                case 3:
                    updateItem(scanner);
                    break;
                case 4:
                    deleteItem(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addItem(Scanner scanner, ItemFactory itemFactory, ItemDAO itemDAO) {
        String itemCode;
        while (true) {
            System.out.print("Enter item code: ");
            itemCode = scanner.nextLine();
            if (itemCode.isEmpty()) {
                System.out.println("Please enter a valid Item Code.");
            } else if (itemDAO.getItem(itemCode) != null) {
                System.out.println("Error: Item with code " + itemCode + " already exists. Please enter a different code.");
            } else {
                break;
            }
        }
        // Proceed with other inputs
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter item variation: ");
        String variation = scanner.nextLine();
        System.out.print("Enter item batch code: ");
        String batchCode = scanner.nextLine();

        String itemType = "";
        while (true) {
            System.out.println("Select item type:");
            System.out.println("1. Fresh Produce");
            System.out.println("2. Canned Items");
            int typeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (typeChoice) {
                case 1:
                    itemType = "Fresh Produce";
                    break;
                case 2:
                    itemType = "Canned Items";
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }
            break;
        }

        Item newItem = itemFactory.createItem(itemCode, itemName, price, variation, batchCode, itemType);
        itemDAO.addItem(newItem);
        System.out.println("Item added successfully.");
    }
    private static void viewItem(Scanner scanner, ItemDAO itemDAO) {
        System.out.print("Enter item code: ");
        String itemCode = scanner.nextLine();

        Item item = itemDAO.getItem(itemCode);
        if (item != null) {
            System.out.println("Item found - " + item);
            item.display();
        } else {
            System.out.println("Item not found.");
        }
    }
    private static void updateItem(Scanner scanner) {
        System.out.print("Enter item code to update: ");
        String itemCode = scanner.nextLine();

        ItemDAO itemDAO = new ItemDAOImpl();

        try {
            Item item = itemDAO.getItem(itemCode);
            if (item != null) {
                item.display(); // Display current item details

                System.out.println("Select field to update:");
                System.out.println("1. Item Code");
                System.out.println("2. Item Name");
                System.out.println("3. Item Price");
                System.out.println("4. Item Variation");
                System.out.println("5. Item Batch Code");
                System.out.println("6. Item Type");
                int updateChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (updateChoice) {
                    case 1:
                        System.out.print("Enter new item code: ");
                        String newCode = scanner.nextLine();
                        item.setItemCode(newCode);
                        break;
                    case 2:
                        System.out.print("Enter new item name: ");
                        String newName = scanner.nextLine();
                        item.setItemName(newName);
                        break;
                    case 3:
                        System.out.print("Enter new item price: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        item.setPrice(newPrice);
                        break;
                    case 4:
                        System.out.print("Enter new item variation: ");
                        String newVariation = scanner.nextLine();
                        item.setVariation(newVariation);
                        break;
                    case 5:
                        System.out.print("Enter new item batch code: ");
                        String newBatchCode = scanner.nextLine();
                        item.setBatchCode(newBatchCode);
                        break;
                    case 6:
                        System.out.print("Enter new item type: ");
                        String newItemType = scanner.nextLine();
                        item.setItemType(newItemType);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }

                itemDAO.updateItem(item);
                System.out.println("Item updated successfully.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating item: " + e.getMessage());
        }
    }
    private static void deleteItem(Scanner scanner) {
        System.out.print("Enter item code to delete: ");
        String itemCode = scanner.nextLine();

        ItemDAO itemDAO = new ItemDAOImpl();

        try {
            Item item = itemDAO.getItem(itemCode);
            if (item != null) {
                itemDAO.deleteItem(itemCode);
                System.out.println("Item deleted successfully.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }
    }
}
