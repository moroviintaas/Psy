import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Stanowisko_obslugi extends BasicSimObj
{
	/**
	 * Tablica nastepnych kolejek, jeśli kolejka bedzie pewna nastapi proba wpakowania do nastepnej
	 */
	SMO_kolejka [] nastepne_kolejki;
	double [] dystrybuanta_skokowa_nastepnych_kolejek;
	SMO_kolejka kolejka_do_gniazda;
	Boolean wymagana_zgodnosc_flag;
	int flaga;
	Samochod obslugiwany;
	String nazwa;
	Start_obslugi start_obslugi;
	Koniec_obslugi koniec_obslugi;
	double wartosc_oczekwiana_czasu_obslugi, odchylenie_czasu_obslugi;
	
	
	public Stanowisko_obslugi(String nazwa,SMO_kolejka[] nastepne_kolejki, double [] dystrybuanta_skokowa_nastepnych_kolejek, Boolean wymagana_zgodnosc_flag ,int flaga, double wartosc_oczekiwana_czasu_obslugi, double odchylenie_czasu_obslugi)
	{
		this.nazwa = nazwa;
		this.nastepne_kolejki = nastepne_kolejki;
		this.wymagana_zgodnosc_flag = wymagana_zgodnosc_flag;
		this.flaga = flaga;
		this.dystrybuanta_skokowa_nastepnych_kolejek = dystrybuanta_skokowa_nastepnych_kolejek;
		this.wartosc_oczekwiana_czasu_obslugi = wartosc_oczekiwana_czasu_obslugi;
		this.odchylenie_czasu_obslugi = odchylenie_czasu_obslugi;
		
	}
	void set_kolejka(SMO_kolejka kolejka)
	{
		this.kolejka_do_gniazda = kolejka;
	}
	
	/*void wprowadz_do_obslugi()
	{
		if(wymagana_zgodnosc_flag)
		{
			obslugiwany = kolejka_do_gniazda.mechanizm.pobierz_z_flaga(flaga);
		}
		else
		{
			obslugiwany = kolejka_do_gniazda.mechanizm.pobierz(); 
		}
	}*/
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
