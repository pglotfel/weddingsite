package weddingsite.client;

import com.google.gwt.core.shared.GWT;

public class RPC {
	public static final PerformLoginServiceAsync performLoginService = GWT.create(PerformLoginService.class);
}
