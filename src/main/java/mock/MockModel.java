package mock;

import dtos.JourneySummaryDTO;
import dtos.JourneysDTO;
import dtos.LocationDTO;
import dtos.ReservationSummaryDTO;
import dtos.RouteDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MockModel {

    Random rand;

    public MockModel() {
        rand = new Random();
    }

    public List<LocationDTO> getLocations() {
        return Arrays.asList(
                new LocationDTO(0, "Bucuresti"),
                new LocationDTO(1, "Copenhagen"),
                new LocationDTO(2, "София"),
                new LocationDTO(3, "Bratislava"),
                new LocationDTO(4, "Warszawa"));

    }

    public List<RouteDTO> getRouteByLocationId(int locationId) {
        //Ids of routes for different locations are having similar id's, so
        //that it will be easier to create a logical mock with different but not 
        //too many possibilities. eg. 5 routes = 5x5 journeys. With this solution
        //of the backend mock there should be only 5 journeys for 5 routes.
        List<RouteDTO> fromRomania = Arrays.asList(
                new RouteDTO(0, "Bucuresti", "Sofia"),
                new RouteDTO(1, "Bucuresti", "Copenhagen"),
                new RouteDTO(2, "Bucuresti", "Bratislava"),
                new RouteDTO(3, "Bucuresti", "Warszawa"));

        List<RouteDTO> fromDenmark = Arrays.asList(
                new RouteDTO(4, "Copenhagen", "Bucuresti"),
                new RouteDTO(5, "Copenhagen", "Sofia"),
                new RouteDTO(6, "Copenhagen", "Bratislava"),
                new RouteDTO(7, "Copenhagen", "Warszawa"));

        List<RouteDTO> fromBulgaria = Arrays.asList(
                new RouteDTO(8, "Sofia", "Bucuresti"),
                new RouteDTO(9, "Sofia", "Copenhagen"),
                new RouteDTO(10, "Sofia", "Bratislava"),
                new RouteDTO(11, "Sofia", "Warszawa"));

        List<RouteDTO> fromSlovakia = Arrays.asList(
                new RouteDTO(12, "Bratislava", "Bucuresti"),
                new RouteDTO(13, "Bratislava", "Copenhagen"),
                new RouteDTO(14, "Bratislava", "Sofia"),
                new RouteDTO(15, "Bratislava", "Warszawa"));

        List<RouteDTO> fromPoland = Arrays.asList(
                new RouteDTO(16, "Warszawa", "Bucuresti"),
                new RouteDTO(17, "Warszawa", "Copenhagen"),
                new RouteDTO(18, "Warszawa", "Bratislava"),
                new RouteDTO(19, "Warszawa", "Sofia"));

        switch (locationId) {
            case -1:
                //If -1 return everything

                List<RouteDTO> fromEverywhere = new ArrayList();
                fromEverywhere.addAll(fromRomania);
                fromEverywhere.addAll(fromDenmark);
                fromEverywhere.addAll(fromBulgaria);
                fromEverywhere.addAll(fromSlovakia);
                fromEverywhere.addAll(fromPoland);

                return fromEverywhere;
            case 0:
                return fromRomania;
            case 1:
                return fromDenmark;
            case 2:
                return fromBulgaria;
            case 3:
                return fromSlovakia;
            case 4:
                return fromPoland;
            default:
                return null;
        }
    }

    private Date returnImaginaryDate(Calendar cal) {
        int max = 12, min = 2;

        cal.add(Calendar.HOUR_OF_DAY, -(rand.nextInt((max - min) + 1) + min)); // adds one hour
        return cal.getTime(); // returns new date object, one hour in the future
    }

    public JourneysDTO getJourneysByRouteId(int routeId) {

        if (routeId < 0 || routeId > 19) {
            return null;
        }

        RouteDTO currentRoute = getRouteByLocationId(-1).get(routeId);

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.DATE, 1);

        return new JourneysDTO(Arrays.asList(
                new JourneySummaryDTO(0,
                        returnImaginaryDate(cal), returnImaginaryDate(cal), "Medium ship"),
                new JourneySummaryDTO(1,
                        returnImaginaryDate(cal), returnImaginaryDate(cal), "Big ship"),
                new JourneySummaryDTO(2,
                        returnImaginaryDate(cal), returnImaginaryDate(cal), "Small ship"),
                new JourneySummaryDTO(3,
                        returnImaginaryDate(cal), returnImaginaryDate(cal), "Medium ship")
        ), currentRoute.getDepartureLocation(), currentRoute.getDestinationLocation());

    }

    public ReservationSummaryDTO createReservation(String jsonQuery) {
        //Mock does not contain logic, therefore the json object is not used.
        System.out.println("jsonQuery is : " + jsonQuery );
        return new ReservationSummaryDTO("София", "København", new Date(),
                new Date(), "Big ferry", 3, "Car", 3500700);
    }
}
