import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class Samochod extends BasicSimObj{
	int numer;
	int flaga;
	Niecierpliwienie zdarzenie_niecierpliwienia;
	SMO_kolejka kolejka;
	
	Samochod(int numer, int flaga, SMO_kolejka kolejka) throws SimControlException
	{
		this.numer = numer;
		this.flaga = flaga;
		this.kolejka = kolejka;
	}
	
	@Override
	public boolean filter(IPublisher arg0, INotificationEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void reflect(IPublisher arg0, INotificationEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
