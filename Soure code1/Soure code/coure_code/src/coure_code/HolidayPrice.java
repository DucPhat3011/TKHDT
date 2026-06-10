package coure_code;

public class HolidayPrice implements IPriceStrategy {

	@Override
	public double calculatePrice(double basePrice) {
		// TODO Auto-generated method stub
		return basePrice * 1.3;
	}

}
