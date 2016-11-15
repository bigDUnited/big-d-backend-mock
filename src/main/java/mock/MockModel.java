package mock;

import dtos.JourneySummaryDTO;
import dtos.JourneysDTO;
import dtos.LocationDTO;
import dtos.ReservationSummaryDTO;
import dtos.RouteDTO;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MockModel {

    private final List<LocationDTO> locations;
    private final List<RouteDTO> routes;
    private final JourneysDTO journeys;
    private final ReservationSummaryDTO reservationSummary;

    public MockModel() {
        locations = Arrays.asList(
                new LocationDTO(0, "București"), new LocationDTO(1, "København"),
                new LocationDTO(2, "София"), new LocationDTO(3, "Bratislava"),
                new LocationDTO(4, "Warszawa"));

        routes = Arrays.asList(new RouteDTO(1, "София", "București"),
                new RouteDTO(2, "София", "København"),
                new RouteDTO(3, "София", "Bratislava"),
                new RouteDTO(4, "София", "Warszawa"));

        journeys = new JourneysDTO(
                Arrays.asList(
                        new JourneySummaryDTO(25, new Date(), new Date(), "Medium ship"),
                        new JourneySummaryDTO(35, new Date(), new Date(), "Big ship"),
                        new JourneySummaryDTO(70, new Date(), new Date(), "Small ship"),
                        new JourneySummaryDTO(105, new Date(), new Date(), "Medium ship")
                ), "София", "București");

        reservationSummary = new ReservationSummaryDTO("София", "København", new Date(),
                new Date(), "Big ferry", 3, "Car", 3500700);
    }

    public List<LocationDTO> getLocations() {
        return locations;

    }

    public List<RouteDTO> getRouteByLocationId(String locationId) {
        //Mock does not contain logic, therefore locationId is not used.
        return routes;
    }

    public JourneysDTO getJourneysByRouteId(String routeId) {
        //Mock does not contain logic, therefore routeId is not used.
        return journeys;
    }

    public ReservationSummaryDTO createReservation(String jsonQuery) {
        //Mock does not contain logic, therefore the json object is not used.
        return reservationSummary;
    }
}
