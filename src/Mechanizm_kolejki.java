import java.util.LinkedList;

public class Mechanizm_kolejki {

	LinkedList<Samochod> kolejka;
	int limit;
	Boolean skonczona;
	private Boolean pelna;
	
	Mechanizm_kolejki(Boolean skonczona, int limit)
	{
		kolejka = new LinkedList<Samochod>();
		this.limit = limit;
		this.skonczona = skonczona;
		pelna = false;
	}
	
	Boolean dodaj(Samochod s)
	{
		if(skonczona)
		{
			if(kolejka.size()<limit)
			{
				Boolean b = kolejka.add(s);
				if(limit == kolejka.size()) pelna = true;
				return b;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return kolejka.add(s);
		}
		
	}
	
	Samochod pobierz()
	{
		 pelna = false;
		 if(kolejka.size()>0)
		 {
			 return kolejka.removeFirst();
		 }
		 else return null;
	}
	Samochod pobierz_z_flaga(int flaga)
	{
		Samochod s=null;
		int i=0;
		for( ; i<kolejka.size(); i++)
		{
			if(kolejka.get(i).flaga == flaga)
			{
				s = kolejka.get(i);
				kolejka.remove(i);
				i = kolejka.size();
				pelna = false;
			}
		}
		return s;
	}
	Boolean usun(Samochod s)
	{
		
		if(kolejka.remove(s))
		{
			pelna = false;
			return true;
		}
		else
		{
			return false;
		}
	}
	Boolean get_pelna()
	{
		return pelna;
	}
}
