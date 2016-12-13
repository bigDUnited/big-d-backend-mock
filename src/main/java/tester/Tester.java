package tester;

import controller.MockController;
import dtos.ReservationSummaryDTO;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        new Tester().logic();
    }

    private void logic() {
        MockController mc = new MockController();
        ReservationSummaryDTO rsDTO = mc.makeReservation(56, 3, "spaceship");
        System.out.println("Currently inserted (1) : " + rsDTO.toString());

        ReservationSummaryDTO rsDTO2 = mc.makeReservation(17, 2, "car");
        System.out.println("Secondary inserted (2) : " + rsDTO2.toString());

        ReservationSummaryDTO loadedObject = mc.getReservation(rsDTO.getReferenceNumber());
        System.out.println("Currently requested (for 1): " + loadedObject.toString());

        List<ReservationSummaryDTO> loadedList = mc.getAllReservations();
        for (int i = 0; i < loadedList.size(); i++) {
            System.out.println("Currently requested from list (" + i + "): " + loadedList.get(i).toString());
        }
    }
}
