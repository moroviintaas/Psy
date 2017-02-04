import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class Koniec_obslugi extends BasicSimEvent<SMO_kolejka, Samochod>
{

	public Koniec_obslugi() throws SimControlException {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		
	}

}
