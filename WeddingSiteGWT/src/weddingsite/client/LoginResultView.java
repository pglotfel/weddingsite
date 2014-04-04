package weddingsite.client;

import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;
import weddingsite.shared.LoginResult;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;

public class LoginResultView extends Composite implements ISubscriber {
	
	private LoginResult model;
	private InlineLabel label;
		
	public LoginResultView() {
		FlowPanel panel = new FlowPanel();
		
		this.label = new InlineLabel();
		this.label.setWidth("40px");
		panel.add(label);
		
		initWidget(panel);
	}
	
	public void setModel(LoginResult model) {
		this.model = model;
		
		// Register to receive VALUE_CHANGED events
		this.model.subscribe(LoginResult.Events.VALUE_CHANGED, this);
	}
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		if (key == LoginResult.Events.VALUE_CHANGED) {
			label.setText(model.getMessage());
		}
	}

}
