package weddingsite.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

public class EditAttendeeWidgetView extends Composite {
	
	private Label attendeeLabel;
	private TextBox numberAttendingTextBox;
	private Label numberAttendingLabel;
	private Button submitChanges;
	private Button deleteAttendeeButton;
	
	public EditAttendeeWidgetView() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("452px", "308px");
		
		submitChanges = new Button("New button");
		submitChanges.setText("Submit");
		layoutPanel.add(submitChanges);
		layoutPanel.setWidgetLeftWidth(submitChanges, 33.8, Unit.PCT, 33.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(submitChanges, 71.6, Unit.PCT, 11.3, Unit.PCT);
		
		numberAttendingTextBox = new TextBox();
		layoutPanel.add(numberAttendingTextBox);
		layoutPanel.setWidgetLeftWidth(numberAttendingTextBox, 33.8, Unit.PCT, 33.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(numberAttendingTextBox, 45.5, Unit.PCT, 11.3, Unit.PCT);
		
		numberAttendingLabel = new Label("Number Attending");
		layoutPanel.add(numberAttendingLabel);
		layoutPanel.setWidgetLeftWidth(numberAttendingLabel, 33.8, Unit.PCT, 33.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(numberAttendingLabel, 32.2, Unit.PCT, 11.3, Unit.PCT);
		
		attendeeLabel = new Label("");
		layoutPanel.add(attendeeLabel);
		layoutPanel.setWidgetLeftWidth(attendeeLabel, 33.8, Unit.PCT, 33.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendeeLabel, 18.8, Unit.PCT, 11.3, Unit.PCT);
		
		deleteAttendeeButton = new Button("New button");
		deleteAttendeeButton.setText("Remove Party");
		layoutPanel.add(deleteAttendeeButton);
		layoutPanel.setWidgetLeftWidth(deleteAttendeeButton, 33.8, Unit.PCT, 33.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(deleteAttendeeButton, 58.6, Unit.PCT, 11.0, Unit.PCT);
	}
	
	public Button getSubmitChangesButton() {
		return submitChanges;
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
