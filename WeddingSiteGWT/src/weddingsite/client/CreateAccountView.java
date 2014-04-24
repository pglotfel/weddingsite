package weddingsite.client;

import weddingsite.client.Site.Pages;
import weddingsite.shared.ActionType;
import weddingsite.shared.CreateAccountModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class CreateAccountView extends Composite{
	private TextBox AccountNameTxt;
	private TextBox AdminUsernameTxt;
	private TextBox PasswordTxt;
	private TextBox ConfirmPasswordTxt;

		public CreateAccountView() {
			
			LayoutPanel layoutPanel = new LayoutPanel();
			initWidget(layoutPanel);
			layoutPanel.setSize("482px", "537px");
			
			Label lblCreateAName = new Label("Create a name for your account:");
			layoutPanel.add(lblCreateAName);
			layoutPanel.setWidgetLeftWidth(lblCreateAName, 24.0, Unit.PX, 195.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblCreateAName, 56.0, Unit.PX, 18.0, Unit.PX);
			
			Label lblegsmithjohnsonwedding = new Label("(Eg. \"SmithJohnsonWedding\")");
			layoutPanel.add(lblegsmithjohnsonwedding);
			layoutPanel.setWidgetLeftWidth(lblegsmithjohnsonwedding, 24.0, Unit.PX, 195.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblegsmithjohnsonwedding, 80.0, Unit.PX, 18.0, Unit.PX);
			
			AccountNameTxt = new TextBox();
			layoutPanel.add(AccountNameTxt);
			layoutPanel.setWidgetLeftWidth(AccountNameTxt, 225.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(AccountNameTxt, 56.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblChooseAnAdmin = new Label("Choose an admin username:");
			layoutPanel.add(lblChooseAnAdmin);
			layoutPanel.setWidgetLeftWidth(lblChooseAnAdmin, 24.0, Unit.PX, 195.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblChooseAnAdmin, 137.0, Unit.PX, 18.0, Unit.PX);
			
			AdminUsernameTxt = new TextBox();
			layoutPanel.add(AdminUsernameTxt);
			layoutPanel.setWidgetLeftWidth(AdminUsernameTxt, 225.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(AdminUsernameTxt, 137.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblPleaseEnterA = new Label("Please enter a password: ");
			layoutPanel.add(lblPleaseEnterA);
			layoutPanel.setWidgetLeftWidth(lblPleaseEnterA, 24.0, Unit.PX, 195.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblPleaseEnterA, 212.0, Unit.PX, 18.0, Unit.PX);
			
			Label lblatLeast = new Label("(At least 6 characters)");
			layoutPanel.add(lblatLeast);
			layoutPanel.setWidgetLeftWidth(lblatLeast, 24.0, Unit.PX, 195.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblatLeast, 236.0, Unit.PX, 18.0, Unit.PX);
			
			PasswordTxt = new TextBox();
			layoutPanel.add(PasswordTxt);
			layoutPanel.setWidgetLeftWidth(PasswordTxt, 225.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(PasswordTxt, 212.0, Unit.PX, 34.0, Unit.PX);
			
			Label lblPleaseReenterThe = new Label("Please re-enter the password:");
			layoutPanel.add(lblPleaseReenterThe);
			layoutPanel.setWidgetLeftWidth(lblPleaseReenterThe, 24.0, Unit.PX, 195.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(lblPleaseReenterThe, 298.0, Unit.PX, 18.0, Unit.PX);
			
			ConfirmPasswordTxt = new TextBox();
			layoutPanel.add(ConfirmPasswordTxt);
			layoutPanel.setWidgetLeftWidth(ConfirmPasswordTxt, 225.0, Unit.PX, 220.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(ConfirmPasswordTxt, 298.0, Unit.PX, 34.0, Unit.PX);
			
			Button CreateAccountBtn = new Button("New button");
			CreateAccountBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					handleCreateAccountBtnClick();
				}
			});
			CreateAccountBtn.setText("Create Account");
			layoutPanel.add(CreateAccountBtn);
			layoutPanel.setWidgetLeftWidth(CreateAccountBtn, 129.0, Unit.PX, 184.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(CreateAccountBtn, 397.0, Unit.PX, 30.0, Unit.PX);
			
			
			
		}
		
		private void handleCreateAccountBtnClick() {
			
			String accountName = AccountNameTxt.getText();
			String adminName = AdminUsernameTxt.getText();
			String password_1 = PasswordTxt.getText();
			String password_2 = ConfirmPasswordTxt.getText();
			
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
