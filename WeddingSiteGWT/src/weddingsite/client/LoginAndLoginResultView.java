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
import com.google.gwt.user.client.ui.FlowPanel;
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
	private FlowPanel mainFlowPanel;
	
	public LoginAndLoginResultView() {
		this.loginResult = new LoginResult();
		
		mainFlowPanel = new FlowPanel();
		mainFlowPanel.setStyleName("Background");
		initWidget(mainFlowPanel);
		mainFlowPanel.setSize("100%", "100%");
		
		LayoutPanel panel = new LayoutPanel();
		panel.setStyleName("InnerBackground");
		mainFlowPanel.add(panel);
		panel.setSize("350px", "500px");
		
		this.resultView = new LoginResultView();
		resultView.setStyleName("CenterText");
		resultView.setModel(loginResult);
		panel.add(resultView);
		panel.setWidgetTopHeight(resultView, 418.0, Unit.PX, 31.0, Unit.PX);
		
		accountNameTextBox = new TextBox();
		accountNameTextBox.setStyleName("CenterEditText");
		accountNameTextBox.setSize("200px", "30px");
		panel.add(accountNameTextBox);
		panel.setWidgetTopHeight(accountNameTextBox, 127.0, Unit.PX, 34.0, Unit.PX);
		
		usernameTextBox = new TextBox();
		usernameTextBox.setStyleName("CenterEditText");
		panel.add(usernameTextBox);
		usernameTextBox.setSize("200px", "30px");
		panel.setWidgetTopHeight(usernameTextBox, 191.0, Unit.PX, 34.0, Unit.PX);
		
		passwordTextBox = new PasswordTextBox();
		passwordTextBox.setStyleName("CenterEditText");
		panel.add(passwordTextBox);
		passwordTextBox.setSize("200px", "30px");
		panel.setWidgetTopHeight(passwordTextBox, 267.0, Unit.PX, 34.0, Unit.PX);
		
		Label accountNameLabel = new Label("Account Name:");
		accountNameLabel.setStyleName("CenterText");
		panel.add(accountNameLabel);
		panel.setWidgetTopHeight(accountNameLabel, 103.0, Unit.PX, 18.0, Unit.PX);
		
		Label usernameLabel = new Label("Username:");
		usernameLabel.setStyleName("CenterText");
		panel.add(usernameLabel);
		panel.setWidgetTopHeight(usernameLabel, 167.0, Unit.PX, 18.0, Unit.PX);
		
		Label passwordLabel = new Label("Password:");
		passwordLabel.setStyleName("CenterText");
		panel.add(passwordLabel);
		panel.setWidgetTopHeight(passwordLabel, 243.0, Unit.PX, 18.0, Unit.PX);
		
		Label LoginTitle = new Label("Log In");
		LoginTitle.setStyleName("CenterTitles");
		panel.add(LoginTitle);
		LoginTitle.setSize("150px", "30px");
		panel.setWidgetTopHeight(LoginTitle, 37.0, Unit.PX, 34.0, Unit.PX);
		
		FlowPanel CreateAcctBtnPanel = new FlowPanel();
		CreateAcctBtnPanel.setStyleName("CenterButton");
		panel.add(CreateAcctBtnPanel);
		CreateAcctBtnPanel.setSize("150px", "30px");
		panel.setWidgetTopHeight(CreateAcctBtnPanel, 366.0, Unit.PX, 43.0, Unit.PX);
		
		Button createAccountButton = new Button("Create Account");
		CreateAcctBtnPanel.add(createAccountButton);
		createAccountButton.setSize("100%", "100%");
		createAccountButton.setStyleName("CenterButton");
		createAccountButton.setText("Create an Account");
		
		FlowPanel loginButtonPanel = new FlowPanel();
		loginButtonPanel.setStyleName("CenterButton");
		panel.add(loginButtonPanel);
		loginButtonPanel.setSize("150px", "30px");
		panel.setWidgetTopHeight(loginButtonPanel, 326.0, Unit.PX, 93.0, Unit.PX);
		
		
		Button loginButton = new Button("New button");
		loginButtonPanel.add(loginButton);
		loginButton.setSize("100%", "100%");
		loginButton.setStyleName("CenterButton");
		loginButton.setText("Login");
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handleLogin();
				
			}			
		});
		createAccountButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleNewAccountClick();
				
			}
			
		});
				
		setStyleName("Background");
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
	
	protected void handleNewAccountClick() {
		Site.search(Pages.CREATEACCOUNTPAGE);
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
						Site.currentUser.setIsAdmin(result.getIsAdmin());
						Site.search(Pages.HOMEPAGE);
						
						
						
					} else {
						loginResult.setMessage(result.getMessage());	
					}				
				}
			
			});
		}
	}
}
