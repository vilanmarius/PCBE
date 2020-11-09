package main;

public class Oferta {
	private String _stocCompanie;
	private int _stocNr;
	private double _stocPret;
	private String _id;
	private final GeneratorID _idGenerator=new GeneratorID();
	
	public Oferta(String _stocCompanie, int _stocNr, double _stocPret)
	{
		this._stocCompanie=_stocCompanie;
		this._stocNr=_stocNr;
		this._stocPret=_stocPret;
		this._id=_idGenerator.obtineIdNou();
	}
	
	public boolean poateNegocia(Oferta _oferta)
	{
		return this.obtineStocCompanie().equals(_oferta.obtineStocCompanie()) && this.obtinePretStoc()==_oferta._stocPret;
	}
	
	public Oferta minimulDupaNrStoc(Oferta _oferta)
	{
		return new Oferta(_oferta.obtineStocCompanie(),Math.min(this._stocNr, _oferta._stocNr),_oferta._stocPret);
	}
	
	public boolean acelasiId(Oferta _oferta)
	{
		return this.obtineId().equals(_oferta.obtineId());
	}
	public boolean stocEpuizat()
	{
		return this.obtineNrStoc()==0;
	}
	public String obtineStocCompanie()
	{
		return _stocCompanie;
	}
	public int obtineNrStoc()
	{
		return _stocNr;
	}
	public double obtinePretStoc()
	{
		return _stocPret;
	}
	public String obtineId()
	{
		return _id;
	}
	public String toString() {
		return "Oferta [stocCompanie=" + _stocCompanie + ", stocNr=" + _stocNr + ", stocPret="
				+ _stocPret + "]";
	}
}
