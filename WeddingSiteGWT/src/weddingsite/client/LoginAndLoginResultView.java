package weddingsite.client;

import weddingsite.client.Site.Pages;
import weddingsite.shared.ActionType;
import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;
import weddingsite.shared.Login;
import weddingsite.shared.LoginResult;
import weddingsite.shared.User;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class LoginAndLoginResultView extends Composite implements ISubscriber {
	
	private LoginResult loginResult;
	private Login model;
	private LoginResultView resultView;
	private TextBox accountNameTextBox;
	private TextBox usernameTextBox;
	private PasswordTextBox passwordTextBox;
	
	public LoginAndLoginResultView() {
		this.loginResult = new LoginResult();
		
		LayoutPanel panel = new LayoutPanel();
		initWidget(panel);
		panel.setHeight("428px");
		
		this.resultView = new LoginResultView();
		resultView.setModel(loginResult);
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
		
		passwordTextBox = new PasswordTextBox();
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
	
	private void updateModel(ActionType actionType) {
		model.setWeddingName(accountNameTextBox.getText());
		model.setUsername(usernameTextBox.getText());
		model.setPassword(passwordTextBox.getText());
		model.setType(actionType);
	}
	
	protected void handleLogin() {
		
		if(accountNameTextBox.getText().equals("") || usernameTextBox.getText().equals("")|| passwordTextBox.getText().equals("")) {
			loginResult.setMessage("Please enter the required fields");
		} else {
			
			updateModel(ActionType.LOGIN);
		
			RPC.performLoginService.performLogin(model, new AsyncCallback<LoginResult>() {

				@Override
				public void onFailure(Throwable caught) {
					System.err.println("Something went wrong with login...");				
				}
	
				@Override
				public void onSuccess(LoginResult result) {
					if (result.getMessage().equals("")) {
						Site.currentUser.setAccountName(accountNameTextBox.getText());
						Site.currentUser.setUsername(usernameTextBox.getText());
						Site.search(Pages.HOMEPAGE);
						
						
						
					} else {
						loginResult.setMessage(result.getMessage());	
					}				
				}
			
			});
		}
	}
}
