package main;

public class Tranzactie {
	private Cumparator _cumparator;
	private Vanzator _vanzator;
	private Oferta _oferta;

	public Tranzactie(Cumparator _cumparator, Vanzator _vanzator, Oferta _oferta) {
		this._cumparator=_cumparator;
		this._vanzator=_vanzator;
		this._oferta=_oferta;
	}

	public Cumparator obtineCumparator() {
		return _cumparator;
	}

	public Vanzator obtineVanzator() {
		return _vanzator;
	}

	public Oferta obtineOferta() {
		return _oferta;
	}

	@Override
	public String toString() {
		return "Tranzactia [cumparator=" + _cumparator.obtineNumeClient() + ", vanzator=" + _vanzator.obtineNumeClient() + ", oferta="
				+ _oferta + "]";
	}

}
