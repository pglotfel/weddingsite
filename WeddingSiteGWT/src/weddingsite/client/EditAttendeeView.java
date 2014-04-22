package weddingsite.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

public class EditAttendeeView extends Composite {
	
	private Label attendeeLabel;
	private TextBox numberAttendingTextBox;
	private Button closeButton;
	private Label numberAttendingLabel;
	private Button submitChanges;
	private Button deleteAttendeeButton;
	
	public EditAttendeeView() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("349px", "292px");
		
		closeButton = new Button("X");
		layoutPanel.add(closeButton);
		layoutPanel.setWidgetLeftWidth(closeButton, 0.0, Unit.PX, 32.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(closeButton, 0.0, Unit.PX, 30.0, Unit.PX);
		
		submitChanges = new Button("New button");
		submitChanges.setText("Submit");
		layoutPanel.add(submitChanges);
		layoutPanel.setWidgetLeftWidth(submitChanges, 132.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(submitChanges, 189.0, Unit.PX, 30.0, Unit.PX);
		
		numberAttendingTextBox = new TextBox();
		layoutPanel.add(numberAttendingTextBox);
		layoutPanel.setWidgetLeftWidth(numberAttendingTextBox, 85.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(numberAttendingTextBox, 135.0, Unit.PX, 34.0, Unit.PX);
		
		numberAttendingLabel = new Label("Number Attending");
		layoutPanel.add(numberAttendingLabel);
		layoutPanel.setWidgetLeftWidth(numberAttendingLabel, 85.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(numberAttendingLabel, 111.0, Unit.PX, 18.0, Unit.PX);
		
		attendeeLabel = new Label("");
		layoutPanel.add(attendeeLabel);
		layoutPanel.setWidgetLeftWidth(attendeeLabel, 85.0, Unit.PX, 128.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(attendeeLabel, 72.0, Unit.PX, 33.0, Unit.PX);
		
		deleteAttendeeButton = new Button("New button");
		deleteAttendeeButton.setText("Remove Party");
		layoutPanel.add(deleteAttendeeButton);
		layoutPanel.setWidgetLeftWidth(deleteAttendeeButton, 132.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(deleteAttendeeButton, 236.0, Unit.PX, 30.0, Unit.PX);
	}
	
	public Button getSubmitChangesButton() {
		return submitChanges;
	}
	
	public Button getCloseButton() {
		return closeButton;
	}
	
	public Button getRemoveButton() {
		return deleteAttendeeButton;
	}
	
	public TextBox getNumberAttendingTextBox() {
		return numberAttendingTextBox;
	}
	
	public Label getAttendeeLabel() {
		return attendeeLabel;
	}
}
