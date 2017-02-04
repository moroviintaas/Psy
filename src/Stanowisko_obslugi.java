

public class Stanowisko_obslugi
{
	/**
	 * Tablica nastepnych kolejek, jeśli kolejka bedzie pewna nastapi proba wpakowania do nastepnej
	 */
	SMO_kolejka [] nastepne_kolejki;
	SMO_kolejka kolejka;
	Boolean wymagana_zgodnosc_flag;
	int flaga;
	Samochod obslugiwany;
	
	
	public Stanowisko_obslugi(SMO_kolejka[] nastepne_kolejki, Boolean wymagana_zgodnosc_flag ,int flaga)
	{
		this.nastepne_kolejki = nastepne_kolejki;
		this.wymagana_zgodnosc_flag = wymagana_zgodnosc_flag;
		this.flaga = flaga;
	}
	void set_kolejka(SMO_kolejka kolejka)
	{
		this.kolejka = kolejka;
	}
	
	void wprowadz_do_obslugi()
	{
		if(wymagana_zgodnosc_flag)
		{
			obslugiwany = kolejka.mechanizm.pobierz_z_flaga(flaga);
		}
		else
		{
			obslugiwany = kolejka.mechanizm.pobierz(); 
		}
	}

}
