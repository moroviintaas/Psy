import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;

public class Koniec_obslugi extends BasicSimEvent<Stanowisko_obslugi, Samochod>
{
	Stanowisko_obslugi parent_SO;

	public Koniec_obslugi(Stanowisko_obslugi parent, double delay) throws SimControlException {
		super(parent, delay);
		this.parent_SO = parent;
	}
	public Koniec_obslugi(Stanowisko_obslugi parent, SimEventSemaphore semafor, Samochod samochod) throws SimControlException
    {
    	super(parent, semafor, samochod);
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
	protected void stateChange() throws SimControlException
	{
		//parent_SO.obslugiwany = null;
		
		Samochod samochod = parent_SO.obslugiwany;
		
		Boolean przekazano_dalej =false;
		//Boolean wartownik1 =false;
		if (parent_SO.nastepne_kolejki == null)
		{
			parent_SO.obslugiwany = null;
			parent_SO.start_obslugi = new Start_obslugi(parent_SO, 0.0);
			System.out.println(simTime()+ " Koniec Obslugi: Stacja_obslugi: " +parent_SO.nazwa +  ", zakonczyla obsluge samochodu nr: "+samochod.numer +  ". Samochod opuszcza stacje."); 
		}
		else
		{

			
			double wybor = parent_SO.kolejka_do_gniazda.generator.nextDouble();
			//System.out.println(parent_SO.nazwa + "  w: " + wybor);
			int i=0;
			for(;i<parent_SO.nastepne_kolejki.length && !przekazano_dalej  ; i++ )
			{
				//System.out.println("i: " + parent_SO.dystrybuanta_skokowa_nastepnych_kolejek[i]);
				if(wybor <= parent_SO.dystrybuanta_skokowa_nastepnych_kolejek[i])
				{
					//System.out.println("tu");
					if(parent_SO.nastepne_kolejki[i].czy_zapelnione()==false)
					{
						parent_SO.nastepne_kolejki[i].dodaj_do_kolejki(samochod);
						parent_SO.obslugiwany = null;
						przekazano_dalej = true;
						
						for(int j=0 ; j<parent_SO.nastepne_kolejki[i].stanowiska.length; j++)
						{
							if(parent_SO.nastepne_kolejki[i].stanowiska[j].obslugiwany==null)
							{
								if(!parent_SO.nastepne_kolejki[i].pusta())
								{
									parent_SO.nastepne_kolejki[i].stanowiska[j].start_obslugi = new Start_obslugi(parent_SO.nastepne_kolejki[i].stanowiska[j], 0.0);//.wprowadz_do_obslugi();
								}
							}
						}
						System.out.println(simTime()+ " Koniec Obslugi: Stacja_obslugi: " +parent_SO.nazwa +  ", zakonczyla obsluge samochodu nr: "+samochod.numer +  ". Samochod udal sie do kolejki: "+samochod.kolejka.nazwa +".");
						parent_SO.start_obslugi = new Start_obslugi(parent_SO, 0.0);
						i = parent_SO.nastepne_kolejki.length;
					}
					
				}
			}
			
			
			if(przekazano_dalej == false)
			{
				try
				{
					parent_SO.koniec_obslugi = new Koniec_obslugi(parent_SO, parent_SO.nastepne_kolejki[i-1].blokada_wejscia, transitionParams);
				}
				catch (Exception e)
				{
					System.out.println(e);
				}

				System.out.println(simTime()+ " Koniec Obslugi: Stacja_obslugi: " +parent_SO.nazwa +  ", zakonczyla obsluge samochodu nr: "+samochod.numer +  ". Samochod czeka na zwolnienie w nastepnych kolejkach.");//, kolejka: "+parent_SO.nastepne_kolejki[i].nazwa+".");
			}
			
		}
		
	}

}
