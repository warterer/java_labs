package lab4.road;

import lab4.person.Firefighter;
import lab4.person.Human;
import lab4.person.Policeman;
import lab4.vehicle.Bus;
import lab4.vehicle.FireTruck;
import lab4.vehicle.PoliceCar;
import lab4.vehicle.Taxi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoadTest {
    @Test
    void emptyRoadHasNoHumans() {
        assertEquals(0, new Road().getCountOfHumans());
    }

    @Test
    void countsPeopleInVehiclesWithDifferentPassengerTypes() {
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

        assertEquals(8, road.getCountOfHumans());
    }

    @Test
    void countChangesWhenPassengersLeaveOrJoin() {
        FireTruck truck = new FireTruck();
        Firefighter ivan = new Firefighter("Іван");
        truck.board(ivan);
        Road road = new Road();
        road.addCarToRoad(truck);
        assertEquals(1, road.getCountOfHumans());

        truck.board(new Firefighter("Сергій"));
        assertEquals(2, road.getCountOfHumans());

        truck.disembark(ivan);
        assertEquals(1, road.getCountOfHumans());
    }

    @Test
    void sameVehicleCannotBeAddedTwice() {
        Taxi taxi = new Taxi();
        taxi.board(new Human("Анна"));
        Road road = new Road();
        road.addCarToRoad(taxi);

        assertThrows(IllegalArgumentException.class, () -> road.addCarToRoad(taxi));
        assertEquals(1, road.getCountOfHumans());
    }

    @Test
    void nullVehicleIsRejected() {
        assertThrows(NullPointerException.class, () -> new Road().addCarToRoad(null));
    }
}
