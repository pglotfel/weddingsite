package weddingsite.client;

import weddingsite.client.Site.Pages;
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
import com.google.gwt.user.client.ui.TextBox;

public class CreateAccountView extends Composite{
	private TextBox AccountNameTxt;
	private TextBox AdminUsernameTxt;
	private PasswordTextBox PasswordTxt;
	private PasswordTextBox ConfirmPasswordTxt;
	private Label ErrorLbl;
	private FlowPanel mainFlowPanel;
	private Button LogOutButton;

		public CreateAccountView() {
			
			mainFlowPanel = new FlowPanel();
			mainFlowPanel.setStyleName("Background");
			initWidget(mainFlowPanel);
			mainFlowPanel.setSize("100%", "100%");
			
			LayoutPanel layoutPanel = new LayoutPanel();
			layoutPanel.setStyleName("InnerBackground");
			mainFlowPanel.add(layoutPanel);
			layoutPanel.setSize("600px", "650px");
			
			Label lblCreateAName = new Label("Create a name for your account:");
			lblCreateAName.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblCreateAName);
			layoutPanel.setWidgetLeftWidth(lblCreateAName, 35.0, Unit.PX, 251.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblCreateAName, 129.0, Unit.PX, 18.0, Unit.PX);
			
			Label lblegsmithjohnsonwedding = new Label("(Eg. \"SmithJohnsonWedding\")");
			lblegsmithjohnsonwedding.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblegsmithjohnsonwedding);
			layoutPanel.setWidgetLeftWidth(lblegsmithjohnsonwedding, 47.0, Unit.PX, 239.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblegsmithjohnsonwedding, 153.0, Unit.PX, 18.0, Unit.PX);
			
			AccountNameTxt = new TextBox();
			AccountNameTxt.setStyleName("EditTextStyle");
			layoutPanel.add(AccountNameTxt);
			layoutPanel.setWidgetLeftWidth(AccountNameTxt, 310.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(AccountNameTxt, 129.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblChooseAnAdmin = new Label("Choose an admin username:");
			lblChooseAnAdmin.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblChooseAnAdmin);
			layoutPanel.setWidgetLeftWidth(lblChooseAnAdmin, 44.0, Unit.PX, 242.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblChooseAnAdmin, 214.0, Unit.PX, 18.0, Unit.PX);
			
			AdminUsernameTxt = new TextBox();
			AdminUsernameTxt.setStyleName("EditTextStyle");
			layoutPanel.add(AdminUsernameTxt);
			layoutPanel.setWidgetLeftWidth(AdminUsernameTxt, 310.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(AdminUsernameTxt, 214.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblPleaseEnterA = new Label("Please enter a password: ");
			lblPleaseEnterA.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblPleaseEnterA);
			layoutPanel.setWidgetLeftWidth(lblPleaseEnterA, 42.0, Unit.PX, 244.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblPleaseEnterA, 305.0, Unit.PX, 18.0, Unit.PX);
			
			Label lblatLeast = new Label("(At least 6 characters)");
			lblatLeast.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblatLeast);
			layoutPanel.setWidgetLeftWidth(lblatLeast, 42.0, Unit.PX, 244.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblatLeast, 329.0, Unit.PX, 18.0, Unit.PX);
			
			PasswordTxt = new PasswordTextBox();
			PasswordTxt.setStyleName("EditTextStyle");
			layoutPanel.add(PasswordTxt);
			layoutPanel.setWidgetLeftWidth(PasswordTxt, 310.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(PasswordTxt, 305.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblPleaseReenterThe = new Label("Please re-enter the password:");
			lblPleaseReenterThe.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblPleaseReenterThe);
			layoutPanel.setWidgetLeftWidth(lblPleaseReenterThe, 37.0, Unit.PX, 249.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblPleaseReenterThe, 399.0, Unit.PX, 18.0, Unit.PX);
			
			ConfirmPasswordTxt = new PasswordTextBox();
			ConfirmPasswordTxt.setStyleName("EditTextStyle");
			layoutPanel.add(ConfirmPasswordTxt);
			layoutPanel.setWidgetLeftWidth(ConfirmPasswordTxt, 310.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(ConfirmPasswordTxt, 401.0, Unit.PX, 34.0, Unit.PX);
			
			ErrorLbl = new Label("");
			ErrorLbl.setStyleName("CenterText");
			layoutPanel.add(ErrorLbl);
			layoutPanel.setWidgetTopHeight(ErrorLbl, 457.0, Unit.PX, 18.0, Unit.PX);
			
			Label CreateAccountHeader = new Label("Create An Account");
			CreateAccountHeader.setStyleName("CenterTitles");
			layoutPanel.add(CreateAccountHeader);
			layoutPanel.setWidgetTopHeight(CreateAccountHeader, 47.0, Unit.PX, 34.0, Unit.PX);
			
			FlowPanel flowPanel = new FlowPanel();
			flowPanel.setStyleName("CenterButton");
			layoutPanel.add(flowPanel);
			flowPanel.setSize("150px", "40px");
			layoutPanel.setWidgetTopHeight(flowPanel, 491.0, Unit.PX, 42.0, Unit.PX);
			
			Button CreateAccountBtn = new Button("New button");
			flowPanel.add(CreateAccountBtn);
			CreateAccountBtn.setSize("100%", "100%");
			CreateAccountBtn.setStyleName("CenterButton");
			CreateAccountBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					handleCreateAccountBtnClick();
				}
			});
			CreateAccountBtn.setText("Create Account");
			
			FlowPanel flowPanel_1 = new FlowPanel();
			flowPanel_1.setStyleName("CenterButton");
			layoutPanel.add(flowPanel_1);
			flowPanel_1.setSize("150px", "40px");
			layoutPanel.setWidgetLeftWidth(flowPanel_1, 225.0, Unit.PX, 152.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(flowPanel_1, 537.0, Unit.PX, 40.0, Unit.PX);
			
			LogOutButton = new Button("New button");
			LogOutButton.setStyleName("CenterButton");
			LogOutButton.setText("Log Out");
			flowPanel_1.add(LogOutButton);
			LogOutButton.setSize("100%", "100%");
			
			LogOutButton.addClickHandler( new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Site.search(Pages.LOGINPAGE);
					
				}
				
			});
			
			
			
			
			
			setStyleName("Background");
		}
		
		private void handleCreateAccountBtnClick() {
			
			String accountName = AccountNameTxt.getText();
			String adminName = AdminUsernameTxt.getText();
			String password_1 = PasswordTxt.getText();
			String password_2 = ConfirmPasswordTxt.getText();
			
			if(accountName.equals("") || adminName.equals("") || password_1.equals("") || password_2.equals("")) {
				ErrorLbl.setText("Please enter all fields");
			} else if (!password_1.equals(password_2)) {
				ErrorLbl.setText("Passwords do not match");
			} else {
			
				CreateAccountModel model = new CreateAccountModel();
				model.setAccountName(accountName);
				model.setAdminName(adminName);
				model.setPassword(password_1);
				model.setType(ActionType.CREATEACCOUNT);
				
				
				RPC.createAccountService.createAccount(model, new AsyncCallback<EditDataResult>() {
	
					@Override
					public void onFailure(Throwable caught) {
						System.err.println("Something went wrong while creating an account.");
						
					}
	
					@Override
					public void onSuccess(EditDataResult result) {
						System.out.println("made it to success in create account!");
						Site.search(Pages.LOGINPAGE);
						
					}
					
				});
			
			}
		}
}
