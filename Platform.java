import java.util.ArrayList;

import iiitb.ess201a7.a7base.*;

public class Platform {

	ArrayList<Fleet> fleets;

// all the methods in this class need to be implemented

	public Platform() {
		fleets = new ArrayList<Fleet>();
	}

	public void addFleet(Fleet f) {
		fleets.add(f);
	}

	// for a request defined as a Trip, find the best car by checking each of its fleets
	// and assigns the car to this trip
	public Car assignCar(Trip trip) {
		Car minCar = null;

		for (int i = 0; i < fleets.size(); i++){
			minCar = fleets.get(i).findNearestCar(trip.getStart());
			if(minCar != null)break;
		}
		for(int i = 0; i < fleets.size(); i++){
			Car temp = fleets.get(i).findNearestCar(trip.getStart());
			if(temp != null && temp.distSqrd(trip.getStart()) < minCar.distSqrd(trip.getStart())){
				minCar = fleets.get(i).findNearestCar(trip.getStart());
			}
		}
		minCar.assignTrip(trip);

		return minCar;
	}

	// returns list of all cars (in all the fleets) managed by this platform
	public ArrayList<Car> findCars() {
		ArrayList<Car> cars = new ArrayList<Car>();

		for(Fleet i:fleets) {
			cars.addAll(i.getCars());
		}
		return cars;
	}

}
