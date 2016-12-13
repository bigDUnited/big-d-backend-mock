package mockito;

import java.util.Arrays;
import java.util.List;
import contractinterface.ContractInterface;
import dtos.JourneySummaryDTO;
import dtos.JourneysDTO;
import dtos.LocationDTO;
import dtos.ReservationSummaryDTO;
import dtos.RouteDTO;
import java.util.ArrayList;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.is;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MockitoBackendMockTest {

    //define the object
    private static ContractInterface contract;

    @BeforeClass
    public static void setUpClass() {
        //Mock the contract
        contract = Mockito.mock(ContractInterface.class);
    }

    @AfterClass
    public static void tearDownClass() {
        contract = null;
    }

    @Test
    public void getLocationsMock_Test() {

        //Prebuild
        int[] localLocationIds = {0, 1, 2, 3, 4};
        String[] localLocationNames = {"București", "København", "София",
            "Bratislava", "Warszawa"};

        //Mocking
        Mockito.when(
                contract.getLocations()
        ).thenAnswer(new Answer() {
            List<LocationDTO> mockedLocDTOlist;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                mockedLocDTOlist = Arrays.asList(
                        new LocationDTO(0, "București"),
                        new LocationDTO(1, "København"),
                        new LocationDTO(2, "София"),
                        new LocationDTO(3, "Bratislava"),
                        new LocationDTO(4, "Warszawa")
                );

                return mockedLocDTOlist;
            }
        });

        //Testing
        List<LocationDTO> locDTOlist = contract.getLocations();
        MatcherAssert.assertThat(locDTOlist.size(), is(5));

        int i = 0;
        for (LocationDTO locationDTO : locDTOlist) {
            MatcherAssert.assertThat(locationDTO.getLocationId(),
                    is(localLocationIds[i]));

            MatcherAssert.assertThat(locationDTO.getLocationName(),
                    is(localLocationNames[i]));
            i++;
        }
    }

    @Test
    public void getRoutesMock_Test() {

        //Prebuild
        int locationId = 1;
        String localDepartureLocation = "София";
        String[] localDestinationLocations = {"București", "København", "Bratislava",
            "Warszawa"};

        //Mocking
        Mockito.when(
                contract.getRoutes(locationId)
        ).thenAnswer(new Answer() {
            List<RouteDTO> mockedRouteDTOlist;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                mockedRouteDTOlist = Arrays.asList(
                        new RouteDTO(1, "София", "București"),
                        new RouteDTO(1, "София", "København"),
                        new RouteDTO(1, "София", "Bratislava"),
                        new RouteDTO(1, "София", "Warszawa")
                );

                return mockedRouteDTOlist;
            }
        });

        //Testing
        List<RouteDTO> routeDTOlist = contract.getRoutes(locationId);
        MatcherAssert.assertThat(routeDTOlist.size(), is(4));

        int i = 0;
        for (RouteDTO routeDTO : routeDTOlist) {
            MatcherAssert.assertThat(routeDTO.getRouteId(),
                    is(locationId));

            MatcherAssert.assertThat(routeDTO.getDepartureLocation(),
                    is(localDepartureLocation));

            MatcherAssert.assertThat(routeDTO.getDestinationLocation(),
                    is(localDestinationLocations[i]));

            i++;
        }
    }

    @Test
    public void getJourneyMock_Test() {

        //Prebuild
        int routeId = 1;
        String localDepartureLocation = "София";
        String localDestinationLocation = "București";
        int[] localJourneyIds = {25, 35, 70, 105};
        String[] localShipTypes = {"Medium ship", "Big ship", "Small ship",
            "Medium ship"};

        //Mocking
        Mockito.when(
                contract.getJourney(routeId)
        ).thenAnswer(new Answer() {
            JourneysDTO mockedJorneysDTO;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                mockedJorneysDTO = new JourneysDTO(
                        Arrays.asList(
                                new JourneySummaryDTO(25, new Date(), new Date(), "Medium ship"),
                                new JourneySummaryDTO(35, new Date(), new Date(), "Big ship"),
                                new JourneySummaryDTO(70, new Date(), new Date(), "Small ship"),
                                new JourneySummaryDTO(105, new Date(), new Date(), "Medium ship")
                        ), "София", "București");

                return mockedJorneysDTO;
            }
        });

        //Testing
        JourneysDTO journeysDTO = contract.getJourney(routeId);
        MatcherAssert.assertThat(journeysDTO.getDepartureLocation(),
                is(localDepartureLocation));
        MatcherAssert.assertThat(journeysDTO.getDestinationLocation(),
                is(localDestinationLocation));

        List<JourneySummaryDTO> journeySummaryDTOList = journeysDTO.getJourneysList();

        int i = 0;
        for (JourneySummaryDTO journeySummaryDTO : journeySummaryDTOList) {
            MatcherAssert.assertThat(journeySummaryDTO.getJourneyId(),
                    is(localJourneyIds[i]));

            MatcherAssert.assertThat(journeySummaryDTO.getFerryName(),
                    is(localShipTypes[i]));

            i++;
        }
    }

    @Test
    public void makeReservationMock_Test() {

        //Prebuild
        int localJourneyId = 70;
        int localNumOfPeople = 3;
        String localVehicleType = "Car";

        String localDepartureLocation = "София";
        String localDestinationLocation = "København";
        String localFerryName = "Big ferry";
        int localReferenceNumber = 3500700;

        //Mocking
        Mockito.when(
                contract.makeReservation(localJourneyId, localNumOfPeople,
                        localVehicleType)
        ).thenAnswer(new Answer() {
            ReservationSummaryDTO mockedReservationSummaryDTO;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                mockedReservationSummaryDTO
                        = new ReservationSummaryDTO("София", "København", new Date(),
                                new Date(), "Big ferry", 3, "Car", 3500700);

                return mockedReservationSummaryDTO;
            }
        });

        //Testing
        ReservationSummaryDTO reservationSummaryDTO
                = contract.makeReservation(localJourneyId, localNumOfPeople,
                        localVehicleType);

        MatcherAssert.assertThat(reservationSummaryDTO.getDepartureLocation(),
                is(localDepartureLocation));

        MatcherAssert.assertThat(reservationSummaryDTO.getDestinationLocation(),
                is(localDestinationLocation));

        MatcherAssert.assertThat(reservationSummaryDTO.getFerryName(),
                is(localFerryName));

        MatcherAssert.assertThat(reservationSummaryDTO.getNumberOfPeople(),
                is(localNumOfPeople));

        MatcherAssert.assertThat(reservationSummaryDTO.getVehicleType(),
                is(localVehicleType));

        MatcherAssert.assertThat(reservationSummaryDTO.getReferenceNumber(),
                is(localReferenceNumber));
    }

    @Test
    public void getReservationByIdMock_Test() {

        //Prebuild
        int localNumOfPeople = 3;
        String localVehicleType = "Car";

        String localDepartureLocation = "София";
        String localDestinationLocation = "København";
        String localFerryName = "Big ferry";
        int localReferenceNumber = 3500700;

        //Prebuild
        int reservationId = localReferenceNumber;

        //Mocking
        Mockito.when(
                contract.getReservation(reservationId)
        ).thenAnswer(new Answer() {
            ReservationSummaryDTO mockedReservationSummaryDTO;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                mockedReservationSummaryDTO
                        = new ReservationSummaryDTO("София", "København", new Date(),
                                new Date(), "Big ferry", 3, "Car", 3500700);

                return mockedReservationSummaryDTO;
            }
        });

        //Testing
        ReservationSummaryDTO specificReservationSummaryDTO
                = contract.getReservation(reservationId);

        MatcherAssert.assertThat(specificReservationSummaryDTO.getDepartureLocation(),
                is(localDepartureLocation));

        MatcherAssert.assertThat(specificReservationSummaryDTO.getDestinationLocation(),
                is(localDestinationLocation));

        MatcherAssert.assertThat(specificReservationSummaryDTO.getFerryName(),
                is(localFerryName));

        MatcherAssert.assertThat(specificReservationSummaryDTO.getNumberOfPeople(),
                is(localNumOfPeople));

        MatcherAssert.assertThat(specificReservationSummaryDTO.getVehicleType(),
                is(localVehicleType));

        MatcherAssert.assertThat(specificReservationSummaryDTO.getReferenceNumber(),
                is(localReferenceNumber));
    }

    @Test
    public void getReservationsMock_Test() {
        //Prebuild
        int[] localNumOfPeopleArray = new int[]{3, 2};
        String[] localVehicleTypes = new String[]{"car", "spaceship"};

        String[] localDepartureLocations = new String[]{"København", "София"};
        String[] localDestinationLocations = new String[]{"София", "København"};
        String[] localFerryNames = new String[]{"Big ferry", "Small ferry"};
        int[] localReferenceNumbers = new int[]{3500700, 99999999};

        Mockito.when(
                contract.getAllReservations()
        ).thenAnswer(new Answer() {
            List<ReservationSummaryDTO> mockedRSdtoList = new ArrayList();

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                mockedRSdtoList.add(new ReservationSummaryDTO("København",
                        "София", new Date(), new Date(), "Big ferry", 3, "car",
                        3500700));

                mockedRSdtoList.add(new ReservationSummaryDTO("София",
                        "København", new Date(), new Date(), "Small ferry", 2, "spaceship",
                        99999999));

                return mockedRSdtoList;
            }
        });

        //Testing
        List<ReservationSummaryDTO> rsDTOlist = contract.getAllReservations();

        for (int i = 0; i < 2; i++) {

            MatcherAssert.assertThat(rsDTOlist.get(i).getDepartureLocation(),
                    is(localDepartureLocations[i]));

            MatcherAssert.assertThat(rsDTOlist.get(i).getDestinationLocation(),
                    is(localDestinationLocations[i]));

            MatcherAssert.assertThat(rsDTOlist.get(i).getFerryName(),
                    is(localFerryNames[i]));

            MatcherAssert.assertThat(rsDTOlist.get(i).getNumberOfPeople(),
                    is(localNumOfPeopleArray[i]));

            MatcherAssert.assertThat(rsDTOlist.get(i).getVehicleType(),
                    is(localVehicleTypes[i]));

            MatcherAssert.assertThat(rsDTOlist.get(i).getReferenceNumber(),
                    is(localReferenceNumbers[i]));

        }

    }

}
