package mock;

import dtos.LocationDTO;
import java.util.Arrays;
import java.util.List;

public class MockModel {

    public List<LocationDTO> getLocations() {
        return Arrays.asList(
                new LocationDTO(0, "București"), new LocationDTO(1, "København"),
                new LocationDTO(2, "София"), new LocationDTO(3, "Bratislava"),
                new LocationDTO(4, "Warszawa")
        );

    }
}
