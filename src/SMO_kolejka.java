import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimEventSemaphore;

public class SMO_kolejka extends BasicSimObj{
	
	Otoczenie otoczenie;
	Mechanizm_kolejki mechanizm;
	SMO_kolejka dla_zniecierpliwonych;
	SimEventSemaphore blokada_wejscia;
	Stanowisko_obslugi [] stanowiska;
	
	public SMO_kolejka(Otoczenie otoczenie, Boolean skonczona_kolejka, Stanowisko_obslugi [] stanowiska, int limit_kolejki, SMO_kolejka dla_zniecierpliwonych ) 
	{
		this.otoczenie = otoczenie;
		mechanizm = new Mechanizm_kolejki(skonczona_kolejka, limit_kolejki);
		this.dla_zniecierpliwonych = dla_zniecierpliwonych;
		for (Stanowisko_obslugi so : stanowiska)
		{
			so.set_kolejka(this);
		}
		
	}
	
	

	
	
	
	
	
	
	
	
	public Boolean czy_zapelnione()
	{
		return mechanizm.get_pelna();
	}
	
	
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
