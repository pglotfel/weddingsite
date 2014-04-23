package weddingsite.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;

public class AddAttendeeWidgetView extends Composite {
	private ListBox numberAttendingList;
	private Label numberAttendingLabel;
	private TextBox attendeeTextBox;
	private Label attendeeNameLabel;
	private LayoutPanel layoutPanel;
	private Button submitButton;
	
	public AddAttendeeWidgetView() {
		
		layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("100%", "100%");
		
		attendeeNameLabel = new Label("Attendee Name");
		layoutPanel.add(attendeeNameLabel);
		layoutPanel.setWidgetLeftWidth(attendeeNameLabel, 37.2, Unit.PCT, 24.6, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendeeNameLabel, 27.8, Unit.PCT, 11.2, Unit.PCT);
		
		attendeeTextBox = new TextBox();
		layoutPanel.add(attendeeTextBox);
		layoutPanel.setWidgetLeftWidth(attendeeTextBox, 37.2, Unit.PCT, 57.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendeeTextBox, 39.8, Unit.PCT, 11.2, Unit.PCT);
		
		numberAttendingLabel = new Label("Number Attending");
		layoutPanel.add(numberAttendingLabel);
		layoutPanel.setWidgetLeftWidth(numberAttendingLabel, 37.2, Unit.PCT, 14.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(numberAttendingLabel, 53.8, Unit.PCT, 8.3, Unit.PCT);
		
		numberAttendingList = new ListBox();
		layoutPanel.add(numberAttendingList);
		layoutPanel.setWidgetLeftWidth(numberAttendingList, 37.2, Unit.PCT, 24.3, Unit.PCT);
		layoutPanel.setWidgetTopHeight(numberAttendingList, 65.0, Unit.PCT, 11.2, Unit.PCT);
		
		submitButton = new Button("New button");
		submitButton.setText("Submit");
		layoutPanel.add(submitButton);
		layoutPanel.setWidgetLeftWidth(submitButton, 37.2, Unit.PCT, 32.4, Unit.PCT);
		layoutPanel.setWidgetTopHeight(submitButton, 79.3, Unit.PCT, 13.3, Unit.PCT);
		
		for (Integer i = 0; i < 20; i++) {
			numberAttendingList.addItem(i.toString());
		}
		
	}
	
	public ListBox getNumberAttendingList() {
		return numberAttendingList;
	}
	
	public TextBox getAttendeeTextBox() {
		return attendeeTextBox;
	}
	
	public Button getSubmitButton() {
		return submitButton;
	}
}
