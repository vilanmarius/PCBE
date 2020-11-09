package main;
public class GeneratorID {

	private static long _ultimaSesiune=0;
	private String _y;
	private long idContor;
	
	public GeneratorID() 
	{
		idContor=0;
		_y=Long.toString(obtineUltimaSesiune());
	}
	
	public String obtineIdNou()
	{
		return _y+idContor++;
	}
	
	private static long obtineUltimaSesiune()
	{
		long _y=java.time.Instant.now().getEpochSecond();
		if(_y>_ultimaSesiune)
		{
			_ultimaSesiune=_y;
		}
		else
		{
			_ultimaSesiune++;
		}
		return _ultimaSesiune;
	}

}
