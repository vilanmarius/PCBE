package main;

import java.util.concurrent.LinkedBlockingQueue;

public class Cumparator extends Client {

	public Cumparator(LinkedBlockingQueue<Oferta> _coada,String _nume, Oferta _cerereCumparare) {
		super._nume=_nume;
		super._oferta=_cerereCumparare;
		super._coada=_coada;
		super._id=_idGenerator.obtineIdNou();
	}

	@Override
	public void run() 
	{
		try
		{
			while(!Thread.currentThread().isInterrupted())
			{
				System.out.println("Cumparator: "+Thread.currentThread().getId()+" ruleaza!");
				Serviciu.adaugaCumparator(this);
				this.proceseazaOferta();
			}
		}
		catch(Exception e)
		{
			Thread.currentThread().interrupt();
			System.out.println("Cumoaratorul cu id-ul: "+Thread.currentThread().getId()+" s-a oprit!");
		}
	}

	private void negot(Vanzator _vanzator, Cumparator _cumparator) throws InterruptedException
	{
		Tranzactie _tranzactie=creeazaTranzactie(_cumparator,_vanzator);
		Serviciu.adaugaTranzactie(_tranzactie);
		System.out.println("Tranzactie: "+_tranzactie);
		Oferta _ofertaCumparator = _cumparator.obtineOferta();
		Oferta _ofertaVanzator = _vanzator.obtineOferta();

		_cumparator.negot(_ofertaVanzator);
		_vanzator.negot(_ofertaCumparator);

		Serviciu.actualizeazaVanzator(_vanzator);
		Serviciu.actualizeazaCumparator(_cumparator);
	}
	
	protected void proceseazaOferta() throws InterruptedException
	{
		System.out.println("Cumparatorul "+obtineNumeClient()+" cu thread-ul "+Thread.currentThread().getId()+" a postat: "+obtineOferta().toString());
		Oferta _oferta=_coada.take();
		if(_oferta.poateNegocia(_oferta))
		{
			Vanzator vanzator=(Vanzator)Serviciu.obtineVanzatori().stream().filter(el->_oferta.acelasiId(el.obtineOferta())).findAny().orElse(null);
		}
		else
		{
			_coada.offer(_oferta);
			_coada.notifyAll();
		}
		while(!_coada.peek().equals(_oferta))
		{
			wait();
		}
	}
}
