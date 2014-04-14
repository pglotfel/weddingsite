package weddingsite.shared;

import java.io.Serializable;

public class EditDataResult extends Publisher implements Serializable{
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private boolean result;
	
	public EditDataResult() {
		
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	

}
