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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MockModel {

    private Map<Integer, ReservationSummaryDTO> localRSlist;

    public MockModel() {
        localRSlist = new HashMap();
    }

    public static List<LocationDTO> getLocations() {
        return Arrays.asList(
                new LocationDTO(0, "București"),
                new LocationDTO(1, "København"),
                new LocationDTO(2, "София"),
                new LocationDTO(3, "Bratislava"),
                new LocationDTO(4, "Warszawa"));
    }

    public static List<RouteDTO> getRouteByLocationId(int locationId) {
        //Ids of routes for different locations are having similar id's, so
        //that it will be easier to create a logical mock with different but not 
        //too many possibilities. eg. 5 routes = 5x5 journeys. With this solution
        //of the backend mock there should be only 5 journeys for 5 routes.
        List<RouteDTO> fromRomania = Arrays.asList(
                new RouteDTO(0, "București", "София"),
                new RouteDTO(1, "București", "København"),
                new RouteDTO(2, "București", "Bratislava"),
                new RouteDTO(3, "București", "Warszawa"));

        List<RouteDTO> fromDenmark = Arrays.asList(
                new RouteDTO(4, "København", "București"),
                new RouteDTO(5, "København", "София"),
                new RouteDTO(6, "København", "Bratislava"),
                new RouteDTO(7, "København", "Warszawa"));

        List<RouteDTO> fromBulgaria = Arrays.asList(
                new RouteDTO(8, "София", "București"),
                new RouteDTO(9, "София", "København"),
                new RouteDTO(10, "София", "Bratislava"),
                new RouteDTO(11, "София", "Warszawa"));

        List<RouteDTO> fromSlovakia = Arrays.asList(
                new RouteDTO(12, "Bratislava", "București"),
                new RouteDTO(13, "Bratislava", "København"),
                new RouteDTO(14, "Bratislava", "София"),
                new RouteDTO(15, "Bratislava", "Warszawa"));

        List<RouteDTO> fromPoland = Arrays.asList(
                new RouteDTO(16, "Warszawa", "București"),
                new RouteDTO(17, "Warszawa", "København"),
                new RouteDTO(18, "Warszawa", "Bratislava"),
                new RouteDTO(19, "Warszawa", "София"));

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

    private static Date returnImaginaryDate(Calendar cal) {
        int max = 12, min = 2;

        cal.add(Calendar.HOUR_OF_DAY, -(new Random().nextInt((max - min) + 1) + min)); // adds one hour
        return cal.getTime(); // returns new date object, one hour in the future
    }

    public static JourneysDTO getJourneysByRouteId(int routeId) {

        if (routeId < 0 || routeId > 19) {
            return null;
        }

        RouteDTO currentRoute = getRouteByLocationId(-1).get(routeId);

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.DATE, 1);

        int journeyId = (1 * routeId) * 4;
        int[] journeyIds = {journeyId, (journeyId + 1), (journeyId + 2), (journeyId + 3)};

        return new JourneysDTO(Arrays.asList(
                new JourneySummaryDTO(journeyIds[0],
                        returnImaginaryDate(cal), returnImaginaryDate(cal), "Medium ship"),
                new JourneySummaryDTO(journeyIds[1],
                        returnImaginaryDate(cal), returnImaginaryDate(cal), "Big ship"),
                new JourneySummaryDTO(journeyIds[2],
                        returnImaginaryDate(cal), returnImaginaryDate(cal), "Small ship"),
                new JourneySummaryDTO(journeyIds[3],
                        returnImaginaryDate(cal), returnImaginaryDate(cal), "Medium ship")
        ), currentRoute.getDepartureLocation(), currentRoute.getDestinationLocation());

    }

    public ReservationSummaryDTO createReservation(int journeyId, int numOfPeople, String vehicleType) {
        int max = 999999, min = 100000;

        int routeId = 0;
        for (int x = 0; x < 20; x++) {
            JourneysDTO currRouteObj = getJourneysByRouteId(routeId);

            List<JourneySummaryDTO> listOfJourneySummaries = currRouteObj.getJourneysList();

            for (int y = 0; y < listOfJourneySummaries.size(); y++) {
                if (listOfJourneySummaries.get(y).getJourneyId() == journeyId) {

                    //Note: Departure and Arrival Date will never be the same as
                    //the original object, because this is a mock and we don't
                    //really save anything anywhere.
                    ReservationSummaryDTO curRSobj = new ReservationSummaryDTO(
                            currRouteObj.getDepartureLocation(),
                            currRouteObj.getDestinationLocation(),
                            listOfJourneySummaries.get(y).getDepartureDate(),
                            listOfJourneySummaries.get(y).getArrivalDate(),
                            listOfJourneySummaries.get(y).getFerryName(),
                            numOfPeople, vehicleType,
                            new Random().nextInt((max - min) + 1) + min);
                    localRSlist.put(curRSobj.getReferenceNumber(), curRSobj);
                    return localRSlist.get(curRSobj.getReferenceNumber());
                }
            }
            routeId++;
        }
        return null;

    }
}
