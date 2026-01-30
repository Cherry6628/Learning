package assignments.assignment003;
import java.util.Arrays;
import java.util.Comparator;

class AreaComparator implements Comparator<City>{
	public int compare(City c1, City c2) {
		double a1=c1.area, a2=c2.area;
		return a1==a2?0:a1>a2?1:-1;
	}
}
class AirportComparator implements Comparator<City>{
	public int compare(City c1, City c2) {
		int a1=c1.numOfAirports, a2=c2.numOfAirports;
		return a1==a2?0:a1>a2?1:-1;
	}
}
class NameComparator implements Comparator<City>{
	public int compare(City c1, City c2) {
		String n1 = c1.name;
		String n2 = c2.name;
		int res = n1.compareTo(n2);
		return res==0?0:res>0?1:-1;
	}
}

class City implements Comparable<City>{
	String name;
	long population;
	double area;
	int numOfAirports;
	City(String name, long population, double area, int airports){
		this.name=name;
		this.population=population;
		this.area=area;
		this.numOfAirports=airports;
	}
	public String toString() {
		return "[area="+this.area+"&population="+this.population+"&airportcount="+this.numOfAirports+"]@"+this.name;
	}
	public int compareTo(City c1) {
		long a1=this.population, a2=c1.population;
		return a1==a2?0:a1>a2?1:-1;
	}
}

public class CityComparatorExample {
	public static void main(String[] args) {
		City c1 = new City("Madurai", 100002340, 1000000, 800);
		City c2 = new City("Madurai 2", 123400000, 1300000, 10);
		City c3 = new City("Madurai 5", 10230000, 1200000, 1200);
		City c4 = new City("Madurai 1", 100453000, 900000, 10200);
		
		City[] cities = {c1, c2, c3, c4};
		System.out.println("Unsorted: "+Arrays.toString(cities));

		Arrays.sort(cities);
		System.out.println("\nSorted on based of Population: "+Arrays.toString(cities));
		
		Arrays.sort(cities, new AreaComparator());
		System.out.println("\nSorted on based of Area: "+Arrays.toString(cities));

		Arrays.sort(cities, new AirportComparator());
		System.out.println("\nSorted on based of Airport: "+Arrays.toString(cities));
		
		Arrays.sort(cities, new NameComparator().reversed());
		System.out.println("\nSorted on based of Name in reverse: "+Arrays.toString(cities));
	}
}
