package weddingsite.client;

import weddingsite.shared.ActionType;
import weddingsite.shared.CreateAccountModel;
import weddingsite.shared.EditDataResult;

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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AccountView extends Composite{
	private LayoutPanel mainLayout;
	private Label AccountNameTxt;
	private Label UsernameTextLbl;
	private PasswordTextBox passwordTextBox;
	private TextBox usernameTextbox;
	private FlowPanel AddUserPanel;
	private Label AddUsernameLbl;
	private Label PasswordTempLbl;
	private FlowPanel SubmitButtonFlowPanel;
	private Button SubmitButton;
	
	public AccountView() {
		
		
		
		ScrollPanel MainFlowPanel = new ScrollPanel();
		MainFlowPanel.setStyleName("Background");
		initWidget(MainFlowPanel);
		MainFlowPanel.setSize("100%", "100%");
		
		
		
		mainLayout = new LayoutPanel();
		mainLayout.setStyleName("InnerBackground");
		MainFlowPanel.add(mainLayout);
		mainLayout.setSize("700px", "750px");
		
		PageView pageView = new PageView();
		mainLayout.add(pageView);
		mainLayout.setWidgetTopBottom(pageView, 93.7,  Unit.PCT, 0.0, Unit.PCT);
		mainLayout.setWidgetLeftRight(pageView, 2.3, Unit.PCT, 30, Unit.PCT);
		
		Label accountLabel = new Label("Account");
		accountLabel.setStyleName("CenterTitles");
		mainLayout.add(accountLabel);
		accountLabel.setSize("700px", "34px");
		mainLayout.setWidgetTopHeight(accountLabel, 50.0, Unit.PX, 38.0, Unit.PX);
		
		FlowPanel InfoFlowPanel = new FlowPanel();
		InfoFlowPanel.setStyleName("InnerBackgroundCenter");
		mainLayout.add(InfoFlowPanel);
		InfoFlowPanel.setSize("350px", "160px");
		mainLayout.setWidgetTopHeight(InfoFlowPanel, 119.0, Unit.PX, 213.0, Unit.PX);
		
		Label AccountNameLbl = new Label("Account Name:");
		AccountNameLbl.setStyleName("homePageText");
		InfoFlowPanel.add(AccountNameLbl);
		
		AccountNameTxt = new Label("");
		AccountNameTxt.setStyleName("homePageText");
		InfoFlowPanel.add(AccountNameTxt);
		AccountNameTxt.setSize("350px", "33px");
		AccountNameTxt.setText(Site.currentUser.getAccountName());
		
		Label UsernameLbl = new Label("Username:");
		UsernameLbl.setStyleName("homePageText");
		InfoFlowPanel.add(UsernameLbl);
		
		UsernameTextLbl = new Label("");
		UsernameTextLbl.setStyleName("homePageText");
		InfoFlowPanel.add(UsernameTextLbl);
		UsernameTextLbl.setSize("100%", "33px");
		UsernameTextLbl.setText(Site.currentUser.getUsername());

		boolean isAdmin = Site.currentUser.getIsAdmin();
		
		if(isAdmin) {
			
			FlowPanel AddUserButtonFlowPanel = new FlowPanel();
			AddUserButtonFlowPanel.setStyleName("CenterButton");
			mainLayout.add(AddUserButtonFlowPanel);
			AddUserButtonFlowPanel.setSize("100px", "50px");
			mainLayout.setWidgetTopHeight(AddUserButtonFlowPanel, 350.0, Unit.PX, 100.0, Unit.PX);
			
			Button AddUsersButton = new Button("AddUsersButton");
			AddUsersButton.setText("Add User");
			AddUserButtonFlowPanel.add(AddUsersButton);
			AddUsersButton.setStyleName("ButtonBold");
			AddUsersButton.setSize("100%", "100%");
			
			AddUsersButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					AddUserPanel = new FlowPanel();
					AddUserPanel.setStyleName("dialogVPanel");
					AddUserPanel.setSize("350px", "175px");
					mainLayout.add(AddUserPanel);
					mainLayout.setWidgetLeftWidth(AddUserPanel, 176.0, Unit.PX, 360.0, Unit.PX);
					mainLayout.setWidgetTopHeight(AddUserPanel, 406.0, Unit.PX, 175.0, Unit.PX);
					
					AddUsernameLbl = new Label("Username:");
					AddUsernameLbl.setStyleName("homePageText");
					AddUserPanel.add(AddUsernameLbl);
					
					usernameTextbox = new TextBox();
					AddUserPanel.add(usernameTextbox);
					usernameTextbox.setSize("100%", "30px");
					usernameTextbox.setStyleName("textboxMargin");
					
					PasswordTempLbl = new Label("Temporary Password:");
					PasswordTempLbl.setStyleName("homePageText");
					AddUserPanel.add(PasswordTempLbl);
					
					passwordTextBox = new PasswordTextBox();
					AddUserPanel.add(passwordTextBox);
					passwordTextBox.setSize("100%", "30px");
					passwordTextBox.setStyleName("textboxMargin");
					
					SubmitButtonFlowPanel = new FlowPanel();
					SubmitButtonFlowPanel.setStyleName("CenterButton");
					mainLayout.add(SubmitButtonFlowPanel);
					SubmitButtonFlowPanel.setSize("100px", "50px");
					mainLayout.setWidgetTopHeight(SubmitButtonFlowPanel, 605.0, Unit.PX, 100.0, Unit.PX);
					
					SubmitButton = new Button("New button");
					SubmitButtonFlowPanel.add(SubmitButton);
					SubmitButton.setStyleName("ButtonBold");
					SubmitButton.setText("Submit");
					SubmitButton.setSize("100%", "100%");
					
					SubmitButton.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							
							CreateAccountModel model = new CreateAccountModel();
							model.setAccountName(Site.currentUser.getAccountName());
							model.setAdminName(usernameTextbox.getText());
							model.setPassword(passwordTextBox.getText());
							model.setType(ActionType.CREATEUSER);
							RPC.createAccountService.addUser(model, new AsyncCallback<EditDataResult>(){

								@Override
								public void onFailure(Throwable caught) {
									System.err.println("ERROR ADDING USER");
									
								}

								@Override
								public void onSuccess(EditDataResult result) {
									removeWidgets();
									
								}

							});
							
						}
						
					});
					
				}
				
			});
			
		}
	}
	
	private void removeWidgets() {
		
		mainLayout.remove(PasswordTempLbl);
		mainLayout.remove(passwordTextBox);
		mainLayout.remove(usernameTextbox);
		mainLayout.remove(AddUserPanel);
		mainLayout.remove(AddUsernameLbl);
		mainLayout.remove(SubmitButtonFlowPanel);
		mainLayout.remove(SubmitButton);
		
		
	}
}
