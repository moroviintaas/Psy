import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;

public class Niecierpliwienie extends BasicSimEvent< Samochod, Object>
{
	Samochod samochod;
	public Niecierpliwienie(Samochod samochod, double delay) throws SimControlException {
		super(samochod, delay);
		this.samochod  =samochod;
		samochod.zdarzenie_niecierpliwienia = this;
	}
	public Niecierpliwienie(Samochod samochod, SimEventSemaphore semafor) throws SimControlException
    {
    	super(samochod, semafor);
        this.samochod = samochod;
        samochod.zdarzenie_niecierpliwienia = this;
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
		//System.out.println("Event N");
		
		if(samochod.kolejka.dla_zniecierpliwonych == null)
		{
			//rezygnuje nie ma innej kolejki w ktorej mialby stanac
			System.out.println(simTime()+ " Niecierpliwienie: Samochod nr: " + samochod.numer + ", zrezygnowal ze stania w kolejce i  opuszcza stacje.");
			samochod.kolejka.mechanizm.usun(samochod);
		}
		else
		{
			if(samochod.kolejka.dla_zniecierpliwonych.czy_zapelnione())
			{
				Niecierpliwienie n = new Niecierpliwienie(samochod, samochod.kolejka.dla_zniecierpliwonych.blokada_wejscia);
			}
			else
			{
				Boolean b1, b2;
				b1 = samochod.kolejka.usun_z_kolejki(samochod); 
				b2 = samochod.kolejka.dla_zniecierpliwonych.dodaj_do_kolejki(samochod);
				if(b1==true && b2 ==true)
				{
					System.out.println(simTime()+ " Niecierpliwienie: Samochod nr: " + samochod.numer + ", zrezygnowal ze stania w kolejce i przeniosl sie do kolejki: " + samochod.kolejka.nazwa);;
				}
			}
		}
		
		
		
		
		
		
	}

}
