package entity.web;

public class WebReservationHelper {

    private int journeyId;
    private int numberOfPeople;
    private String vehicleType;

    public WebReservationHelper(int journeyId, int numberOfPeople, String vehicleType) {
        this.journeyId = journeyId;
        this.numberOfPeople = numberOfPeople;
        this.vehicleType = vehicleType;
    }
}
