package lab4;

import lab4.person.Firefighter;
import lab4.person.Human;
import lab4.person.Policeman;
import lab4.road.Road;
import lab4.vehicle.*;

public class Main {
    public static void main(String[] args) {
        Taxi taxi = new Taxi();
        taxi.board(new Human("Анна"));
        taxi.board(new Firefighter("Іван"));

        Bus bus = new Bus();
        bus.board(new Human("Марія"));
        bus.board(new Human("Дмитро"));
        bus.board(new Policeman("Олег"));

        FireTruck truck = new FireTruck();
        truck.board(new Firefighter("Сергій"));
        truck.board(new Firefighter("Ігор"));

        PoliceCar police = new PoliceCar();
        police.board(new Policeman("Петро"));

        Road road = new Road();
        road.addCarToRoad(taxi);
        road.addCarToRoad(bus);
        road.addCarToRoad(truck);
        road.addCarToRoad(police);
        System.out.println("Людей на дорозі: " + road.getCountOfHumans());

        truck.disembark(truck.getPassengers().get(0));
        System.out.println("Після висадки з пожежної: " + road.getCountOfHumans());

        try {
            road.addCarToRoad(taxi);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
