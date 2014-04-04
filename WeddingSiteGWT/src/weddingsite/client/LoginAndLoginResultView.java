package weddingsite.client;

import weddingsite.shared.ActionType;
import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;
import weddingsite.shared.Login;
import weddingsite.shared.LoginResult;
import weddingsite.shared.PerformLogin;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

public class LoginAndLoginResultView extends Composite implements ISubscriber {
	
	private LoginResult result;
	private Login model;
	private PerformLogin controller;
	private LoginResultView resultView;
	private TextBox accountNameTextBox;
	private TextBox usernameTextBox;
	private TextBox passwordTextBox;
	
	public LoginAndLoginResultView() {
		this.result = new LoginResult();
		
		LayoutPanel panel = new LayoutPanel();
		initWidget(panel);
		panel.setHeight("428px");
		
		this.resultView = new LoginResultView();
		resultView.setModel(result);
		panel.add(resultView);
		panel.setWidgetLeftWidth(resultView, 25.0, Unit.PX, 200.0, Unit.PX);
		panel.setWidgetTopHeight(resultView, 298.0, Unit.PX, 31.0, Unit.PX);
		
		accountNameTextBox = new TextBox();
		panel.add(accountNameTextBox);
		panel.setWidgetLeftWidth(accountNameTextBox, 25.0, Unit.PX, 173.0, Unit.PX);
		panel.setWidgetTopHeight(accountNameTextBox, 48.0, Unit.PX, 34.0, Unit.PX);
		
		usernameTextBox = new TextBox();
		panel.add(usernameTextBox);
		panel.setWidgetLeftWidth(usernameTextBox, 25.0, Unit.PX, 173.0, Unit.PX);
		panel.setWidgetTopHeight(usernameTextBox, 112.0, Unit.PX, 34.0, Unit.PX);
		
		passwordTextBox = new TextBox();
		panel.add(passwordTextBox);
		panel.setWidgetLeftWidth(passwordTextBox, 25.0, Unit.PX, 173.0, Unit.PX);
		panel.setWidgetTopHeight(passwordTextBox, 176.0, Unit.PX, 34.0, Unit.PX);
		
		Label accountNameLabel = new Label("Account Name");
		panel.add(accountNameLabel);
		panel.setWidgetLeftWidth(accountNameLabel, 25.0, Unit.PX, 101.0, Unit.PX);
		panel.setWidgetTopHeight(accountNameLabel, 24.0, Unit.PX, 18.0, Unit.PX);
		
		Label usernameLabel = new Label("Username");
		panel.add(usernameLabel);
		panel.setWidgetLeftWidth(usernameLabel, 25.0, Unit.PX, 81.0, Unit.PX);
		panel.setWidgetTopHeight(usernameLabel, 88.0, Unit.PX, 18.0, Unit.PX);
		
		Label passwordLabel = new Label("Password");
		panel.add(passwordLabel);
		panel.setWidgetLeftWidth(passwordLabel, 25.0, Unit.PX, 56.0, Unit.PX);
		panel.setWidgetTopHeight(passwordLabel, 152.0, Unit.PX, 18.0, Unit.PX);
		
		Button loginButton = new Button("New button");
		loginButton.setText("Login");
		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleLogin();
				
			}			
		});
		panel.add(loginButton);
		panel.setWidgetLeftWidth(loginButton, 25.0, Unit.PX, 81.0, Unit.PX);
		panel.setWidgetTopHeight(loginButton, 236.0, Unit.PX, 30.0, Unit.PX);
				
	}

	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		//Don't do anything here...
	}
	
	public void setModel(Login model) {
		this.model = model;
		this.model.subscribe(Login.Events.VALUE_OR_ACTION_TYPE_CHANGED, this);
	}
	
	public void setController(PerformLogin controller) {
		this.controller = controller;
	}

	protected void handleLogin() {
		String accountName = accountNameTextBox.getText();
		String username = usernameTextBox.getText();
		String password = passwordTextBox.getText();
		controller.setWeddingName(accountName);
		controller.setUsername(username);
		controller.setPassword(password);
		controller.setActionType(ActionType.LOGIN);
		controller.perform(result);
	}
}
