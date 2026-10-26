package lab4;

import lab4.person.Firefighter;
import lab4.person.Human;
import lab4.person.Policeman;
import lab4.vehicle.*;

public class Main {
    public static void main(String[] args) {
        Taxi taxi = new Taxi(2);
        Human anna = new Human("Анна");
        Firefighter ivan = new Firefighter("Іван");
        taxi.board(anna);
        taxi.board(ivan);
        System.out.println(taxi + ": " + taxi.getPassengers());

        try {
            taxi.board(new Policeman("Петро"));
        } catch (VehicleFullException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        taxi.disembark(anna);
        System.out.println("Після висадки: " + taxi);
        try {
            taxi.disembark(anna);
        } catch (PassengerNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        FireTruck truck = new FireTruck();
        truck.board(ivan);
        PoliceCar police = new PoliceCar();
        police.board(new Policeman("Олег"));
        System.out.println(truck + " / " + police + " / " + new Bus());
    }
}
