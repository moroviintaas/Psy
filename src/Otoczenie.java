import java.util.ArrayList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class Otoczenie extends BasicSimObj{
	
	ArrayList<SMO_kolejka> kolejki;
	Wjazd wjazd_na_stacje;
	
	Otoczenie() throws SimControlException
	{
		//
		kolejki = new ArrayList<SMO_kolejka>();
	}
	public void organizuj_wjazd(SMO_kolejka kolejka) throws SimControlException
	{
		wjazd_na_stacje = new Wjazd(this, kolejka, 0);
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
