import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimEventSemaphore;

public class SMO_kolejka extends BasicSimObj{
	
	Mechanizm_kolejki mechanizm;
	SMO_kolejka dla_zniecierpliwonych;
	SimEventSemaphore blokada_wejscia;
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
