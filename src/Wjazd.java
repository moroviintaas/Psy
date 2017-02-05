import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import java.util.Random;

public class Wjazd extends BasicSimEvent<Otoczenie, Object> {

	SimGenerator generator;
	Otoczenie parent_otoczenie;
	int wjechalo_samochodow;
	SMO_kolejka kolejka;
	Random rnd;

	public Wjazd(Otoczenie parent, SMO_kolejka kolejka, double delay) throws SimControlException {
		super(parent,delay);
		generator = new SimGenerator();
		this.parent_otoczenie = parent;
		wjechalo_samochodow = 0;
		this.kolejka = kolejka;
		rnd  = new Random();
		
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
		parent_otoczenie = getSimObj();
		wjechalo_samochodow++;
		
		int fl = rnd.nextInt(3);
		fl++;
		Samochod s = new Samochod(wjechalo_samochodow, fl,kolejka);
		
		if(kolejka.czy_zapelnione())
		{
			System.out.println(simTime()+ " Wjazd: Wjechal samochod nr: " + s.numer + ", ale zrezygnowal, kolejka pelna. ");
		}
		else
		{
			System.out.println(simTime()+ " Wjazd: Wjechal samochod nr: " + s.numer + ", czeka do: " + ((s.flaga==1) ? "LPG":(s.flaga==2)?"ON":"PB")+".");
			kolejka.dodaj_do_kolejki(s);
			double do_zniecierpliwienia = generator.normal(15.0, 1.0);
			Niecierpliwienie n = new Niecierpliwienie(s, do_zniecierpliwienia);
			s.zdarzenie_niecierpliwienia = n;
			for(int i=0 ; i<kolejka.stanowiska.length; i++)
			{
				if(kolejka.stanowiska[i].obslugiwany==null)
				{
					if(!kolejka.pusta())
					{
						kolejka.stanowiska[i].start_obslugi = new Start_obslugi(kolejka.stanowiska[i], 0.0);//.wprowadz_do_obslugi();
					}
				}
			}
		}
		double odstep = generator.normal(5.0, 1.0);
        setRepetitionPeriod(odstep);
		//System.out.println(System.out.println(simTime()+ "Wjazd: Wjechal samochod nr: " + s.numer + ", czeka na: " ;
		
	}

}
