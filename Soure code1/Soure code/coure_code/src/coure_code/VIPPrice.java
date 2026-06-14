
public class VIPPrice implements IPriceStrategy {

	@Override
	public double calculatePrice(double basePrice) {
		// TODO Auto-generated method stub
		return basePrice * 0.8;
	}

}
