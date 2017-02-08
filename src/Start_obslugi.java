import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class Start_obslugi extends BasicSimEvent<Stanowisko_obslugi, Samochod>
{
	Stanowisko_obslugi parent_SO;

	public Start_obslugi(Stanowisko_obslugi parent, double delay) throws SimControlException {
		super(parent, delay);
		this.parent_SO = parent;
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
		Samochod samochod = null;
		if(parent_SO.wymagana_zgodnosc_flag)
		{
			samochod=parent_SO.kolejka_do_gniazda.pobierz_z_kolejki_flaga(parent_SO.flaga);
			parent_SO.obslugiwany = samochod;
			if(samochod!=null)
			{
				//Ktos czekal do tego dystrybutora miejsce sie zwolnilo
				if(parent_SO.kolejka_do_gniazda.jedno_wolne_miejsce())
				{
					parent_SO.kolejka_do_gniazda.blokada_wejscia.open();
					
					
				}
				samochod.zdarzenie_niecierpliwienia.interrupt();
				System.out.println(simTime()+ " Start Obslugi: Stacja_obslugi: " +parent_SO.nazwa +  ", zaczyna obsluge samochodu nr: "+samochod.numer +  ".");
				
				
				
				
			}
			else
			{
			}
		}
		else
		{
			samochod = parent_SO.kolejka_do_gniazda.pobierz_z_kolejki();
			parent_SO.obslugiwany = samochod;
			if(parent_SO.kolejka_do_gniazda.jedno_wolne_miejsce())
			{
				try
				{
					parent_SO.kolejka_do_gniazda.blokada_wejscia.open();
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}

				
				
			if(samochod !=null)
			{
				samochod.zdarzenie_niecierpliwienia.interrupt();
				System.out.println(simTime()+ " Start Obslugi: Stacja_obslugi: " +parent_SO.nazwa +  ", zaczyna obsluge samochodu nr: "+samochod.numer +  ".");
			}
			
		}
		if(samochod!=null)
		{
			double czas_obslugi = parent_SO.kolejka_do_gniazda.generator.normal(parent_SO.wartosc_oczekwiana_czasu_obslugi, parent_SO.odchylenie_czasu_obslugi);
			parent_SO.koniec_obslugi = new Koniec_obslugi(parent_SO, czas_obslugi);
		}
		
	}

}
