package com.aleksandrovaveronika.task12.delivery;

public class DeliveryCostCalculation {
    private static final double MIN_COST = 400.0;
    private int deliveryDistance;
    private CargoDimensions cargoDimensions;
    private boolean cargoFragility;
    private DeliveryWorkload deliveryWorkload;
    private double totalDeliveryCost;

    // Валидация входных данных
    private static void validateInputParameters(
            int deliveryDistance,
            CargoDimensions cargoDimensions,
            boolean cargoFragility,
            DeliveryWorkload deliveryWorkload) {
        if (deliveryDistance <= 0) {
            throw new IllegalArgumentException("Delivery distance should be more than 0 km.");
        }
        if (cargoDimensions == null) {
            throw new IllegalArgumentException("Cargo dimensions should be specified.");
        }
        if (cargoFragility == true && deliveryDistance > 30) {
            throw new IllegalArgumentException("Fragile cargo cannot be transported over distance greater than 30 km.");
        }
        if (deliveryWorkload == null) {
            throw new IllegalArgumentException("Delivery service workload should be specified.");
        }
    }

    // Конструктор объекта, содержащий входные данные и расчет стоимости доставки
    public DeliveryCostCalculation(
            int deliveryDistance,
            CargoDimensions cargoDimensions,
            boolean cargoFragility,
            DeliveryWorkload deliveryWorkload) {
        this.deliveryDistance = deliveryDistance;
        this.cargoDimensions = cargoDimensions;
        this.cargoFragility = cargoFragility;
        this.deliveryWorkload = deliveryWorkload;

        validateInputParameters(deliveryDistance, cargoDimensions, cargoFragility, deliveryWorkload);

        this.totalDeliveryCost = Math.max(
                MIN_COST,
                (deliveryCostViaDistance(deliveryDistance)
                + deliveryCostViaDimensions(cargoDimensions)
                + deliveryCostViaFragility(cargoFragility))
                * coefficientViaWorkload(deliveryWorkload));
    }

    // Метод расчета стоимости доставки с учетом расстояния до пункта назначения
    private static double deliveryCostViaDistance(int deliveryDistance) {
        if (deliveryDistance <= 2) {
            return 50;
        } else if (deliveryDistance <= 10) {
            return 100;
        } else if (deliveryDistance <= 30) {
            return 200;
        } else {
            return 300;
        }
    }

    // Метод расчета стоимости доставки с учетом габаритов груза
    private static double deliveryCostViaDimensions(CargoDimensions cargoDimensions) {
        return cargoDimensions == CargoDimensions.SMALL ? 100 : 200;
    }

    // Метод расчета стоимости доставки с учетом хрупкости груза
    private static double deliveryCostViaFragility(boolean cargoFragility) {
        return cargoFragility == true ? 300 : 0;
    }

    // Метод расчета коэффициента к стоимости доставки с учетом загруженности сервиса
    private static double coefficientViaWorkload(DeliveryWorkload deliveryWorkload) {
        if (deliveryWorkload == DeliveryWorkload.EXTRA_HIGH) {
            return 1.6;
        } else if (deliveryWorkload == DeliveryWorkload.HIGH) {
            return 1.4;
        } else if (deliveryWorkload == DeliveryWorkload.BUSY) {
            return 1.2;
        } else {
            return 1.0;
        }
    }

    // Геттеры
    // Сеттеры не используются, поскольку пересчет стоимости доставки не происходит при использовании сеттеров
    public int getDeliveryDistance() { return deliveryDistance; }
    public CargoDimensions getCargoDimensions() { return cargoDimensions; }
    public boolean isCargoFragility() { return cargoFragility; }
    public DeliveryWorkload getDeliveryWorkload() { return deliveryWorkload; }
    public double getTotalDeliveryCost() { return totalDeliveryCost; }
}
