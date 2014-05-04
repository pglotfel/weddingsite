package weddingsite.shared;

import java.io.Serializable;
import java.util.List;

public class GetItemsResult<Type> implements Serializable {
	
	private List<Type> result;

	public List<Type> getResult() {
		return result;
	}

	public void setResult(List<Type> result) {
		this.result = result;
	}
}
