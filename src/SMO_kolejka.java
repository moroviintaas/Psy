import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimEventSemaphore;

public class SMO_kolejka extends BasicSimObj{
	
	Otoczenie otoczenie;
	Mechanizm_kolejki mechanizm;
	SMO_kolejka dla_zniecierpliwonych;
	SimEventSemaphore blokada_wejscia;
	Stanowisko_obslugi [] stanowiska;
	String nazwa;
	SimGenerator generator;
	Boolean wlacz_niecierpliwienie;
	
	public SMO_kolejka(String nazwa, Otoczenie otoczenie,  Stanowisko_obslugi [] stanowiska, Boolean skonczona_kolejka, int limit_kolejki, Boolean wlacz_niecierpliwienie, SMO_kolejka dla_zniecierpliwonych ) 
	{
		this.nazwa = nazwa;
		this.otoczenie = otoczenie;
		mechanizm = new Mechanizm_kolejki(skonczona_kolejka, limit_kolejki);
		this.dla_zniecierpliwonych = dla_zniecierpliwonych;
		this.stanowiska = stanowiska;
		for (Stanowisko_obslugi so : this.stanowiska)
		{
			so.set_kolejka(this);
		}
		generator = new SimGenerator();
		this.wlacz_niecierpliwienie = wlacz_niecierpliwienie;
		blokada_wejscia = new SimEventSemaphore();
		
		
	}
	public Boolean dodaj_do_kolejki(Samochod s)
	{
		Boolean b = mechanizm.dodaj(s);
		if( b== true)
		{
			s.kolejka = this;
		}
		return b;
	}
	public Boolean usun_z_kolejki(Samochod s)
	{
		Boolean b = mechanizm.usun(s);
		if(b==true)
		{
			s.kolejka = null;
		}
		return b;
	}
	public Samochod pobierz_z_kolejki_flaga(int flaga)
	{
		Samochod s = mechanizm.pobierz_z_flaga(flaga);
		if(s!=null)
		{
			s.kolejka = null;
		}
		return s;
	}
	public Samochod pobierz_z_kolejki()
	{
		Samochod s = mechanizm.pobierz();
		if(s!=null)
		{
			s.kolejka = null;
		}
		return s;
	}
	public Boolean jedno_wolne_miejsce()
	{
		if(mechanizm.kolejka.size()==mechanizm.limit-1) return true;
		else return false;
	}
	public Boolean pusta()
	{
		return mechanizm.kolejka.isEmpty();
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
