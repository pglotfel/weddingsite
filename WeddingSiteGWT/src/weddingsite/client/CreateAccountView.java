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

		public CreateAccountView() {
			
			mainFlowPanel = new FlowPanel();
			mainFlowPanel.setStyleName("Background");
			initWidget(mainFlowPanel);
			mainFlowPanel.setSize("100%", "100%");
			
			LayoutPanel layoutPanel = new LayoutPanel();
			layoutPanel.setStyleName("Background");
			mainFlowPanel.add(layoutPanel);
			layoutPanel.setSize("544px", "579px");
			
			Label lblCreateAName = new Label("Create a name for your account:");
			lblCreateAName.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblCreateAName);
			layoutPanel.setWidgetLeftWidth(lblCreateAName, 21.0, Unit.PX, 251.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblCreateAName, 56.0, Unit.PX, 18.0, Unit.PX);
			
			Label lblegsmithjohnsonwedding = new Label("(Eg. \"SmithJohnsonWedding\")");
			lblegsmithjohnsonwedding.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblegsmithjohnsonwedding);
			layoutPanel.setWidgetLeftWidth(lblegsmithjohnsonwedding, 21.0, Unit.PX, 234.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblegsmithjohnsonwedding, 80.0, Unit.PX, 18.0, Unit.PX);
			
			AccountNameTxt = new TextBox();
			layoutPanel.add(AccountNameTxt);
			layoutPanel.setWidgetLeftWidth(AccountNameTxt, 291.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(AccountNameTxt, 64.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblChooseAnAdmin = new Label("Choose an admin username:");
			lblChooseAnAdmin.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblChooseAnAdmin);
			layoutPanel.setWidgetLeftWidth(lblChooseAnAdmin, 21.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblChooseAnAdmin, 137.0, Unit.PX, 18.0, Unit.PX);
			
			AdminUsernameTxt = new TextBox();
			layoutPanel.add(AdminUsernameTxt);
			layoutPanel.setWidgetLeftWidth(AdminUsernameTxt, 291.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(AdminUsernameTxt, 138.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblPleaseEnterA = new Label("Please enter a password: ");
			lblPleaseEnterA.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblPleaseEnterA);
			layoutPanel.setWidgetLeftWidth(lblPleaseEnterA, 21.0, Unit.PX, 195.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblPleaseEnterA, 209.0, Unit.PX, 18.0, Unit.PX);
			
			Label lblatLeast = new Label("(At least 6 characters)");
			lblatLeast.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblatLeast);
			layoutPanel.setWidgetLeftWidth(lblatLeast, 21.0, Unit.PX, 195.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblatLeast, 233.0, Unit.PX, 18.0, Unit.PX);
			
			PasswordTxt = new PasswordTextBox();
			layoutPanel.add(PasswordTxt);
			layoutPanel.setWidgetLeftWidth(PasswordTxt, 291.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(PasswordTxt, 217.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblPleaseReenterThe = new Label("Please re-enter the password:");
			lblPleaseReenterThe.setStyleName("CreateAccountLbls");
			layoutPanel.add(lblPleaseReenterThe);
			layoutPanel.setWidgetLeftWidth(lblPleaseReenterThe, 21.0, Unit.PX, 234.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblPleaseReenterThe, 335.0, Unit.PX, 18.0, Unit.PX);
			
			ConfirmPasswordTxt = new PasswordTextBox();
			layoutPanel.add(ConfirmPasswordTxt);
			layoutPanel.setWidgetLeftWidth(ConfirmPasswordTxt, 291.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(ConfirmPasswordTxt, 335.0, Unit.PX, 34.0, Unit.PX);
			
			Button CreateAccountBtn = new Button("New button");
			CreateAccountBtn.setStyleName("h1");
			CreateAccountBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					handleCreateAccountBtnClick();
				}
			});
			CreateAccountBtn.setText("Create Account");
			layoutPanel.add(CreateAccountBtn);
			layoutPanel.setWidgetLeftWidth(CreateAccountBtn, 161.0, Unit.PX, 184.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(CreateAccountBtn, 452.0, Unit.PX, 30.0, Unit.PX);
			
			ErrorLbl = new Label("");
			layoutPanel.add(ErrorLbl);
			layoutPanel.setWidgetLeftWidth(ErrorLbl, 161.0, Unit.PX, 184.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(ErrorLbl, 394.0, Unit.PX, 18.0, Unit.PX);
			
			
			
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
