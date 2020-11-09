package main;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Serviciu {

	private static final List<Tranzactie> _tranzactii = new CopyOnWriteArrayList<Tranzactie>();
	private static final List<Client> _vanzatori = new CopyOnWriteArrayList<>();
	private static final List<Client> _cumparatori = new CopyOnWriteArrayList<>();
	
	public static void adaugaTranzactie(Tranzactie _tranzactie)
	{
		_tranzactii.add(_tranzactie);
	}
	
	public static void adaugaVanzator(Vanzator _vanzator)
	{
		adaugaClient(_vanzator,_vanzatori);
	}
	
	public static void adaugaCumparator(Cumparator _cumparator)
	{
		adaugaClient(_cumparator,_cumparatori);
	}
	
	public static void actualizeazaVanzator(Vanzator _vanzator)
	{
		actualizeazaClient(_vanzator,_vanzatori);
	}
	
	public static void actualizeazaCumparator(Cumparator _cumparator)
	{
		actualizeazaClient(_cumparator,_cumparatori);
	}
	
	public static void adaugaClient(Client _client, List<Client> _lista)
	{
		_lista.add(_client);
	}
	
	public static void actualizeazaClient(Client _client, List<Client> _lista)
	{
		Optional<Client>_cauta=_lista.stream().filter(el->el.acelasiID(_client)).findFirst();
		if(_cauta.isPresent())
		{
			Client _actualizeaza=_cauta.get();
			_lista.set(_lista.indexOf(_actualizeaza),_client);
		}
	}
	
	public static List<Oferta> _obtineOferteVanzare()
	{
		return obtineOferta(_vanzatori);
	}
	
	public static List<Oferta> _obtineCerereCumparare()
	{
		return obtineOferta(_cumparatori);
	}
	
	private static List<Oferta> obtineOferta(List<Client> _lista) {
		List<Oferta> _colecteaza = _lista.stream().map(el -> el.obtineOferta()).collect(Collectors.toList());
		return eliminaStocuriGoale(_colecteaza);
	}
	
	public static List<Client> obtineVanzatori()
	{
		return _vanzatori;
	}
	
	public static List<Client> obtineCumparatori()
	{
		return _cumparatori;
	}
	
	private static List<Oferta> eliminaStocuriGoale(List<Oferta> _lista)
	{
		List<Oferta> _listaVida=_lista.stream().filter(el-> el.stocEpuizat()).collect(Collectors.toList());
		_lista.removeAll(_listaVida);
		return _lista;
	}
	
	public static List<Tranzactie> obtineTranzactii()
	{
		return _tranzactii;
	}
}
