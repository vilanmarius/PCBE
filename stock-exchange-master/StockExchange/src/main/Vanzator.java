package main;

import java.util.concurrent.LinkedBlockingQueue;

public class Vanzator extends Client {

	public Vanzator(LinkedBlockingQueue<Oferta> _coada,String _nume, Oferta _ofertaVanzare) {
		super._nume=_nume;
		super._oferta=_ofertaVanzare;
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
				System.out.println("Vazator: "+Thread.currentThread().getId()+" ruleaza!");
				Serviciu.adaugaVanzator(this);
				this.posteazaOfertaVanzator();
			}
		}
		catch(Exception e)
		{
			Thread.currentThread().interrupt();
			System.out.println("Vanzatorul cu id-ul: "+Thread.currentThread().getId()+" s-a oprit!");
		}
	}

	@Override
	protected void negot(Oferta _oferta) throws InterruptedException
	{
		super.negot(_oferta);
		if(this._oferta.obtineNrStoc()!=0)
		{
			posteazaOfertaVanzator();
		}
	}
	
	protected void posteazaOfertaVanzator() throws InterruptedException
	{
		System.out.println("Vanzatorul "+obtineNumeClient()+" cu thread-ul "+Thread.currentThread().getId()+" a postat: "+obtineOferta().toString());
		Oferta _ofertaVanzare=_oferta;
		if(_coada.offer(_ofertaVanzare))
		{
			_coada.notifyAll();
		}
		wait();
	}
}
