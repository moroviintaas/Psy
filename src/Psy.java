import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class Psy {
	public static void main(String [] argv)
	{
		System.out.println("SIEMA");
		
		//Przygotowanie srodowiska
		
		try
		{
			Otoczenie stacja = new Otoczenie();
			
			Stanowisko_obslugi kasa = new Stanowisko_obslugi("Kasa", null, false, 0);
			Stanowisko_obslugi [] array_kasy = new Stanowisko_obslugi[1];
			array_kasy[0] = kasa;
			
			SMO_kolejka kolejka_do_kasy = new SMO_kolejka("Kolejka do kasy", stacja,  array_kasy, true, 7, false, null);
			SMO_kolejka [] array_kolejki_do_kasy = new SMO_kolejka[1]; 
			array_kolejki_do_kasy[0] = kolejka_do_kasy;
			
			Stanowisko_obslugi myjnia = new Stanowisko_obslugi("Myjnia", array_kolejki_do_kasy, false, 0);
			Stanowisko_obslugi [] array_myjnie = new Stanowisko_obslugi[1];
			array_myjnie[0] = myjnia;
			
			SMO_kolejka kolejka_do_myjni = new SMO_kolejka("Kolejka do myjni", stacja,  array_myjnie, true, 4, true, kolejka_do_kasy);
			
			SMO_kolejka [] array_kolejki_po_tankowaniu = new SMO_kolejka[2];
			array_kolejki_po_tankowaniu[0] = kolejka_do_kasy;
			array_kolejki_po_tankowaniu[1] = kolejka_do_myjni;
			
			Stanowisko_obslugi dystrybutor_lpg = new Stanowisko_obslugi("LPG", array_kolejki_po_tankowaniu, true, 0x01);
			Stanowisko_obslugi dystrybutor_on = new Stanowisko_obslugi("ON", array_kolejki_po_tankowaniu, true, 0x02);
			Stanowisko_obslugi dystrybutor_pb = new Stanowisko_obslugi("PB", array_kolejki_po_tankowaniu, true, 0x03);
			Stanowisko_obslugi [] array_dystrybutory = new Stanowisko_obslugi[3];
			array_dystrybutory[0] = dystrybutor_lpg;
			array_dystrybutory[1] = dystrybutor_on;
			array_dystrybutory[2] = dystrybutor_pb;
			
			SMO_kolejka kolejka_do_dystrybutorow = new SMO_kolejka("Kolejka_do_dystrybutorow", stacja, array_dystrybutory, true, 6, true, null);
			
			stacja.kolejki.add(kolejka_do_dystrybutorow);
			stacja.kolejki.add(kolejka_do_myjni);
			stacja.kolejki.add(kolejka_do_kasy);
			
			stacja.organizuj_wjazd(kolejka_do_dystrybutorow);
			
			
			SimManager model = SimManager.getInstance();
			SimControlEvent stopEvent = new SimControlEvent(1000.0, SimControlStatus.STOPSIMULATION);
			model.startSimulation();
			System.out.println("Nara");
			
				
			
		}
		catch (SimControlException e)
		{
			
		}
		
		
		
		
		
		
		
	}

}
