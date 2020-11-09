package main;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class Client implements Runnable 
{
	protected String _nume;
	protected Oferta _oferta;
	protected LinkedBlockingQueue<Oferta> _coada=null;
	protected String _id;
	protected final GeneratorID _idGenerator=new GeneratorID();
	
	public void editeazaActiune(Oferta _oferta) throws InterruptedException
	{
		this._oferta=_oferta;
	}
	
	public Tranzactie creeazaTranzactie(Cumparator _cumparator, Vanzator _vanzator)
	{
		
		Tranzactie _tranzactie=new Tranzactie(_cumparator,_vanzator,_vanzator.obtineOferta().minimulDupaNrStoc(_cumparator.obtineOferta()));
		return _tranzactie;
	}
	
	protected void negot(Oferta _oferta) throws InterruptedException
	{
		int _o1=this._oferta.obtineNrStoc();
		int _o2=_oferta.obtineNrStoc();
		if (_o1>_o2)
		{
			editeazaActiune(new Oferta(this._oferta.obtineStocCompanie(),_o1-_o2,_oferta.obtinePretStoc()));
		}
		else
		{
			editeazaActiune(new Oferta(this._oferta.obtineStocCompanie(),0,_oferta.obtinePretStoc()));
		}
	}
	
	public boolean acelasiID(Client _client)
	{
		if(this.obtineID().equals(_client.obtineID()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String obtineNumeClient()
	{
		return _nume;
	}
	
	public String obtineID()
	{
		return _id;
	}
	
	public Oferta obtineOferta()
	{
		return _oferta;
	}
}