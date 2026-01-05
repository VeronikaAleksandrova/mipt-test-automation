package com.aleksandrovaveronika.task12.delivery;
import java.util.Scanner;

public class DeliveryDataEntry {
    public static void main(String[] args) {
        int deliveryDistance;
        CargoDimensions cargoDimensions;
        boolean cargoFragility;
        DeliveryWorkload deliveryWorkload;
        double totalDeliveryCost;

        // Ввод данных для расчета доставки из консоли
        try (Scanner scanner = new Scanner(System.in)) {
            // Ввод расстояния доставки
            System.out.print("Enter information about delivery distance in km: ");
            deliveryDistance = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            // Ввод габаритов груза
            System.out.print("Enter information about delivery dimensions (small or large): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "small":
                    cargoDimensions = CargoDimensions.SMALL;
                    break;
                case "large":
                    cargoDimensions = CargoDimensions.LARGE;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid delivery dimensions.");
            }

            // Ввод хрупкости груза
            System.out.print("Enter information about cargo fragility (yes or no): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "yes":
                    cargoFragility = true;
                    break;
                case "no":
                    cargoFragility = false;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid delivery fragility.");
            }

            // Ввод загруженности сервиса доставки
            System.out.print("Enter information about delivery workload (normal, busy, high or extra high): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "normal":
                    deliveryWorkload = DeliveryWorkload.NORMAL;
                    break;
                case "busy":
                    deliveryWorkload = DeliveryWorkload.BUSY;
                    break;
                case "high":
                    deliveryWorkload = DeliveryWorkload.HIGH;
                    break;
                case "extra high":
                    deliveryWorkload = DeliveryWorkload.EXTRA_HIGH;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid delivery workload.");
            }

            // Вывод итоговой стоимости доставки
            totalDeliveryCost = new DeliveryCostCalculation(
                   deliveryDistance,
                   cargoDimensions,
                   cargoFragility,
                   deliveryWorkload).getTotalDeliveryCost();
            System.out.print("\nThe total delivery cost: " + totalDeliveryCost);
        } catch (IllegalArgumentException exc) {
            System.out.println("\nError: " + exc.getMessage());
            System.out.println("Please restart the program with correct data.");
        } catch (Exception exc) {
            System.out.println("\nUnexpected error: " + exc.getMessage());
        }
    }
}
