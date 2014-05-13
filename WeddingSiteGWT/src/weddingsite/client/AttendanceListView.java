package weddingsite.client;

import java.util.ArrayList;

import weddingsite.shared.ActionType;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;
import weddingsite.shared.Attendee;
import weddingsite.shared.AttendeeQueryModel;
import weddingsite.shared.AttendeeQueryResult;
import weddingsite.shared.EditAttendanceListModel;
import weddingsite.shared.EditAttendeeModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AttendanceListView extends Composite {
	
	private LayoutPanel mainLayoutPanel;
	private FlowPanel attendanceListFlowPanel;
	private FlowPanel attendeeFlowPanel;
	private FlowPanel attendanceListAddFlowPanel;
	private FlowPanel attendanceListEditFlowPanel;
	private MenuBar attendanceListMenu;
	private MenuBar attendeeMenu;
	private Label attendanceListNameLabel;
	private FlowPanel attendanceListSubmitFlowPanel;
	private TextBox attendanceListTextBox;
	private Label attendanceListNewNameLabel;
	private FlowPanel attendanceListCancelFlowPanel;
	private FlowPanel attendanceListDeleteFlowPanel;
	private Label attendeeNameLabel;
	private FlowPanel attendeeSubmitFlowPanel;
	private Label attendeeNewNameLabel;
	private FlowPanel attendeeCancelFlowPanel;
	private FlowPanel attendeeDeleteFlowPanel;
	private FlowPanel attendeeAddFlowPanel;
	private FlowPanel attendeeEditFlowPanel;
	private Label attendeeNumberAttendingLabel;
	private TextBox attendeeNameTextBox;	
	private EditAttendanceListModel editAttendanceListModel;
	private AttendanceListQueryModel attendanceListQueryModel;
	private AttendeeQueryModel attendeeQueryModel;
	private EditAttendeeModel editAttendeeModel;
	private IntegerBox numberAttendingIntegerBox;
	
	public AttendanceListView() {
		
		editAttendeeModel = new EditAttendeeModel();
		editAttendanceListModel = new EditAttendanceListModel();
		attendeeQueryModel = new AttendeeQueryModel();
		attendanceListQueryModel = new AttendanceListQueryModel();
		
		FlowPanel mainFlowPanel = new FlowPanel();
		mainFlowPanel.setStyleName("Background");
		initWidget(mainFlowPanel);
		mainFlowPanel.setSize("100%", "100%");
		
		ScrollPanel mainScrollPanel = new ScrollPanel();
		mainScrollPanel.setStyleName("Background");
		mainFlowPanel.add(mainScrollPanel);
		mainScrollPanel.setSize("100%", "100%");
		
		mainLayoutPanel = new LayoutPanel();
		mainLayoutPanel.setStyleName("InnerBackground");
		mainScrollPanel.setWidget(mainLayoutPanel);
		mainLayoutPanel.setSize("750px", "850px");
		
		PageView pageView = new PageView();
		mainLayoutPanel.add(pageView);
		mainLayoutPanel.setWidgetTopBottom(pageView, 93.7,  Unit.PCT, 0.0, Unit.PCT);
		mainLayoutPanel.setWidgetLeftRight(pageView, 2.3, Unit.PCT, 30, Unit.PCT);
		
		attendanceListFlowPanel = new FlowPanel();
		attendanceListFlowPanel.setStyleName("Background");
		mainLayoutPanel.add(attendanceListFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListFlowPanel, 20, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListFlowPanel, 15, Unit.PCT, 45, Unit.PCT);
		
		attendanceListMenu = new MenuBar(true);
		attendanceListFlowPanel.add(attendanceListMenu);
		attendanceListMenu.setSize("100%", "100%");
		attendanceListMenu.setStyleName("gwt-MenuBar-vertical");
		
		attendeeFlowPanel = new FlowPanel();
		mainLayoutPanel.add(attendeeFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeFlowPanel, 60.0, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeFlowPanel, 15, Unit.PCT, 45, Unit.PCT);
		
		attendeeMenu = new MenuBar(true);
		attendeeFlowPanel.add(attendeeMenu);
		attendeeMenu.setSize("100%", "100%");
		attendeeMenu.setStyleName("gwt-MenuBar-vertical");
		
		Label titleLabel = new Label("Attendance Lists");
		titleLabel.setStyleName("CenterTitles");
		mainLayoutPanel.add(titleLabel);
		mainLayoutPanel.setWidgetTopHeight(titleLabel, 38.0, Unit.PX, 30.0, Unit.PX);
		titleLabel.setSize("200px", "30px");
		
		if(Site.currentUser.getIsAdmin()) {
			attendanceListAddFlowPanel = new FlowPanel();
			mainLayoutPanel.add(attendanceListAddFlowPanel);
			mainLayoutPanel.setWidgetLeftWidth(attendanceListAddFlowPanel, 17, Unit.PCT, 6, Unit.PCT);
			mainLayoutPanel.setWidgetTopHeight(attendanceListAddFlowPanel, 65, Unit.PCT, 30.0, Unit.PX);
			
			Button attendanceListAddButton = new Button("New button");
			attendanceListAddButton.setStyleName("ButtonColorScheme");
			attendanceListAddButton.setText("Add");
			attendanceListAddFlowPanel.add(attendanceListAddButton);
			attendanceListAddButton.setSize("100%", "100%");
			attendanceListAddButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleAttendanceListAdd();
				}			
			});
		
			attendanceListEditFlowPanel = new FlowPanel();
			mainLayoutPanel.add(attendanceListEditFlowPanel);
			mainLayoutPanel.setWidgetLeftWidth(attendanceListEditFlowPanel, 37, Unit.PCT, 6, Unit.PCT);
			mainLayoutPanel.setWidgetTopHeight(attendanceListEditFlowPanel, 65, Unit.PCT, 3.5, Unit.PCT);
			
			Button attendanceListEditButton = new Button("New button");
			attendanceListEditButton.setStyleName("ButtonColorScheme");
			attendanceListEditButton.setText("Edit");
			attendanceListEditFlowPanel.add(attendanceListEditButton);
			attendanceListEditButton.setSize("100%", "100%");
			attendanceListEditButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleAttendanceListEdit();				
				}			
			});
		}
		
		attendanceListNameLabel = new Label("Click on an attendance list!");
		attendanceListNameLabel.setStyleName("CenterText");
		mainLayoutPanel.add(attendanceListNameLabel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListNameLabel, 17, Unit.PCT, 26, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListNameLabel, 61.5, Unit.PCT, 2, Unit.PCT);
		
		attendanceListNewNameLabel = new Label("New name:");
		attendanceListNewNameLabel.setStyleName("TextColorScheme");
		
		attendanceListTextBox = new TextBox();
		
		attendanceListSubmitFlowPanel = new FlowPanel();
		
		Button attendanceListSubmitButton = new Button("New button");
		attendanceListSubmitButton.setStyleName("ButtonColorScheme");
		attendanceListSubmitButton.setText("Submit");
		attendanceListSubmitFlowPanel.add(attendanceListSubmitButton);
		attendanceListSubmitButton.setSize("100%", "100%");
		attendanceListSubmitButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(mainLayoutPanel.getWidgetIndex(attendanceListDeleteFlowPanel) == -1) {
					handleAttendanceListSubmitAdd();
				} else {
					handleAttendanceListSubmitEdit();
				}				
			}		
		});
		
		attendanceListCancelFlowPanel = new FlowPanel();
		
		Button attendanceListCancelButton = new Button("New button");
		attendanceListCancelButton.setStyleName("ButtonColorScheme");
		attendanceListCancelButton.setText("Cancel");
		attendanceListCancelFlowPanel.add(attendanceListCancelButton);
		attendanceListCancelButton.setSize("100%", "100%");
		attendanceListCancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				removeAttendanceListWidgets();			
			}			
		});
		
		attendanceListDeleteFlowPanel = new FlowPanel();
		
		Button attendanceListDeleteButton = new Button("New button");
		attendanceListDeleteButton.setStyleName("ButtonColorScheme");
		attendanceListDeleteButton.setText("Delete");
		attendanceListDeleteFlowPanel.add(attendanceListDeleteButton);
		attendanceListDeleteButton.setSize("100%", "100%");
		attendanceListDeleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleAttendanceListDelete();
			}		
		});
						
		if(Site.currentUser.getIsAdmin()) {
			attendeeAddFlowPanel = new FlowPanel();
			mainLayoutPanel.add(attendeeAddFlowPanel);
			mainLayoutPanel.setWidgetLeftWidth(attendeeAddFlowPanel, 57, Unit.PCT, 6, Unit.PCT);
			mainLayoutPanel.setWidgetTopHeight(attendeeAddFlowPanel, 65, Unit.PCT, 30.0, Unit.PX);
			
			Button attendeeAddButton = new Button("New button");
			attendeeAddButton.setStyleName("ButtonColorScheme");
			attendeeAddButton.setText("Add");
			attendeeAddFlowPanel.add(attendeeAddButton);
			attendeeAddButton.setSize("100%", "100%");
			attendeeAddButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleAttendeeAdd();
				}			
			});
		
			attendeeEditFlowPanel = new FlowPanel();
			mainLayoutPanel.add(attendeeEditFlowPanel);
			mainLayoutPanel.setWidgetLeftWidth(attendeeEditFlowPanel, 77, Unit.PCT, 6, Unit.PCT);
			mainLayoutPanel.setWidgetTopHeight(attendeeEditFlowPanel, 65, Unit.PCT, 3.5, Unit.PCT);
			
			Button attendeeEditButton = new Button("New button");
			attendeeEditButton.setStyleName("ButtonColorScheme");
			attendeeEditButton.setText("Edit");
			attendeeEditFlowPanel.add(attendeeEditButton);
			attendeeEditButton.setSize("100%", "100%");
			attendeeEditButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleAttendeeEdit();				
				}			
			});
		}
				
		attendeeNameLabel = new Label("Click on an attendee list!");
		attendeeNameLabel.setStyleName("CenterText");
		mainLayoutPanel.add(attendeeNameLabel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeNameLabel, 57, Unit.PCT, 26, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeNameLabel, 61.5, Unit.PCT, 2, Unit.PCT);
		
		attendeeNewNameLabel = new Label("Name:");
		attendeeNumberAttendingLabel = new Label("Number attending: ");
		attendeeNumberAttendingLabel.setStyleName("TextColorScheme");
		attendeeNewNameLabel.setStyleName("TextColorScheme");
		
		numberAttendingIntegerBox = new IntegerBox();
		attendeeNameTextBox = new TextBox();
		
		attendeeSubmitFlowPanel = new FlowPanel();
		
		Button attendeeSubmitButton = new Button("New button");
		attendeeSubmitButton.setStyleName("ButtonColorScheme");
		attendeeSubmitButton.setText("Submit");
		attendeeSubmitFlowPanel.add(attendeeSubmitButton);
		attendeeSubmitButton.setSize("100%", "100%");
		attendeeSubmitButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(mainLayoutPanel.getWidgetIndex(attendeeDeleteFlowPanel) == -1) {
					handleAttendeeSubmitAdd();
				} else {
					handleAttendeeSubmitEdit();
				}				
			}		
		});
		
		attendeeCancelFlowPanel = new FlowPanel();
		
		Button attendeeCancelButton = new Button("New button");
		attendeeCancelButton.setStyleName("ButtonColorScheme");
		attendeeCancelButton.setText("Cancel");
		attendeeCancelFlowPanel.add(attendeeCancelButton);
		attendeeCancelButton.setSize("100%", "100%");
		attendeeCancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				removeAttendeeWidgets();			
			}			
		});
		
		attendeeDeleteFlowPanel = new FlowPanel();
		
		Button attendeeDeleteButton = new Button("New button");
		attendeeDeleteButton.setStyleName("ButtonColorScheme");
		attendeeDeleteButton.setText("Delete");
		attendeeDeleteFlowPanel.add(attendeeDeleteButton);
		attendeeDeleteButton.setSize("100%", "100%");
		attendeeDeleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleAttendeeDelete();
			}		
		});
							
		setStyleName("Background");
		
		loadAttendanceList();
	}
	
	public void handleAttendanceListAdd() {
		
		removeAttendanceListWidgets();
		mainLayoutPanel.add(attendanceListCancelFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListCancelFlowPanel, 35, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListCancelFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
		mainLayoutPanel.add(attendanceListSubmitFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListSubmitFlowPanel, 17, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListSubmitFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
		mainLayoutPanel.add(attendanceListNewNameLabel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListNewNameLabel, 15, Unit.PCT, 15, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListNewNameLabel, 71.5, Unit.PCT, 3, Unit.PCT);
		mainLayoutPanel.add(attendanceListTextBox);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListTextBox, 30, Unit.PCT, 15, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListTextBox, 71.5, Unit.PCT, 3, Unit.PCT);
	}
	
	public void handleAttendanceListEdit() {
		
		removeAttendanceListWidgets();
		mainLayoutPanel.add(attendanceListCancelFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListCancelFlowPanel, 35, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListCancelFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
		mainLayoutPanel.add(attendanceListSubmitFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListSubmitFlowPanel, 17, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListSubmitFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
		mainLayoutPanel.add(attendanceListNewNameLabel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListNewNameLabel, 15, Unit.PCT, 15, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListNewNameLabel, 71.5, Unit.PCT, 3, Unit.PCT);
		mainLayoutPanel.add(attendanceListTextBox);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListTextBox, 30, Unit.PCT, 15, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListTextBox, 71.5, Unit.PCT, 3, Unit.PCT);
		mainLayoutPanel.add(attendanceListDeleteFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendanceListDeleteFlowPanel, 26, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendanceListDeleteFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
	}
	
	public void removeAttendanceListWidgets() {
		
		mainLayoutPanel.remove(attendanceListCancelFlowPanel);
		mainLayoutPanel.remove(attendanceListSubmitFlowPanel);
		mainLayoutPanel.remove(attendanceListNewNameLabel);
		mainLayoutPanel.remove(attendanceListTextBox);
		mainLayoutPanel.remove(attendanceListDeleteFlowPanel);
		attendanceListTextBox.setText("");
	}
	
	public void handleAttendeeAdd() {
		
		removeAttendeeWidgets();
		mainLayoutPanel.add(attendeeCancelFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeCancelFlowPanel, 75, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeCancelFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
		mainLayoutPanel.add(attendeeSubmitFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeSubmitFlowPanel, 57, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeSubmitFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
		
		mainLayoutPanel.add(attendeeNewNameLabel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeNewNameLabel, 55, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeNewNameLabel, 69.5, Unit.PCT, 3, Unit.PCT);
		mainLayoutPanel.add(attendeeNameTextBox);
		mainLayoutPanel.setWidgetLeftWidth(attendeeNameTextBox, 75, Unit.PCT, 15, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeNameTextBox, 69.5, Unit.PCT, 3, Unit.PCT);
		
		mainLayoutPanel.add(attendeeNumberAttendingLabel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeNumberAttendingLabel, 55, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeNumberAttendingLabel, 73.0, Unit.PCT, 3, Unit.PCT);
		mainLayoutPanel.add(numberAttendingIntegerBox);
		mainLayoutPanel.setWidgetLeftWidth(numberAttendingIntegerBox, 75, Unit.PCT, 10, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(numberAttendingIntegerBox, 73.0, Unit.PCT, 3, Unit.PCT);
		
		
	}
	
	public void handleAttendeeEdit() {
		
		removeAttendeeWidgets();
		mainLayoutPanel.add(attendeeCancelFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeCancelFlowPanel, 75, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeCancelFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
		mainLayoutPanel.add(attendeeSubmitFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeSubmitFlowPanel, 57, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeSubmitFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
		mainLayoutPanel.add(attendeeNumberAttendingLabel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeNumberAttendingLabel, 55, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeNumberAttendingLabel, 71.5, Unit.PCT, 3, Unit.PCT);
		mainLayoutPanel.add(numberAttendingIntegerBox);
		mainLayoutPanel.setWidgetLeftWidth(numberAttendingIntegerBox, 75, Unit.PCT, 10, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(numberAttendingIntegerBox, 71.5, Unit.PCT, 3, Unit.PCT);
		mainLayoutPanel.add(attendeeDeleteFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(attendeeDeleteFlowPanel, 66, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(attendeeDeleteFlowPanel, 77, Unit.PCT, 3.5, Unit.PCT);
	}
	
	public void removeAttendeeWidgets() {
		
		mainLayoutPanel.remove(attendeeCancelFlowPanel);
		mainLayoutPanel.remove(attendeeSubmitFlowPanel);
		mainLayoutPanel.remove(attendeeNewNameLabel);
		mainLayoutPanel.remove(numberAttendingIntegerBox);
		mainLayoutPanel.remove(attendeeDeleteFlowPanel);
		mainLayoutPanel.remove(attendeeNumberAttendingLabel);
		mainLayoutPanel.remove(attendeeNameTextBox);
		numberAttendingIntegerBox.setText("");
		attendeeNameTextBox.setText("");
	}
	
	public void handleAttendanceListSubmitAdd() {
		
		editAttendanceListModel.setAccountName(Site.currentUser.getAccountName());
		editAttendanceListModel.setAttendanceListName(attendanceListTextBox.getText());
		editAttendanceListModel.setType(ActionType.ADDATTENDANCELIST);
		
		RPC.editAttendanceListService.performAddAttendanceList(editAttendanceListModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with adding an attendance list...");				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					loadAttendanceList();
				}			
			}			
		});
		
		attendanceListNameLabel.setText("");
		removeAttendanceListWidgets();
	}
	
	public void handleAttendanceListSubmitEdit() {
		
		editAttendanceListModel.setAccountName(Site.currentUser.getAccountName());
		editAttendanceListModel.setAttendanceListName(attendanceListNameLabel.getText());
		editAttendanceListModel.setNewName(attendanceListTextBox.getText());
		editAttendanceListModel.setType(ActionType.EDITATTENDANCELIST);
		
		RPC.editAttendanceListService.performAddAttendanceList(editAttendanceListModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with editing an attendance list...");				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					loadAttendanceList();
				}			
			}	
		});
		
		attendanceListNameLabel.setText("");
		removeAttendanceListWidgets();
	}
	
	public void handleAttendanceListDelete() {
		
		editAttendanceListModel.setAccountName(Site.currentUser.getAccountName());
		editAttendanceListModel.setAttendanceListName(attendanceListNameLabel.getText());
		editAttendanceListModel.setType(ActionType.DELETEATTENDANCELIST);
		
		RPC.editAttendanceListService.performAddAttendanceList(editAttendanceListModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with deleting an attendance list...");				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					loadAttendanceList();
				}			
			}			
		});
		
		attendanceListNameLabel.setText("");
		removeAttendanceListWidgets();
	}
			
	public void loadAttendanceList() {
		
		attendanceListQueryModel.setWeddingName(Site.currentUser.getAccountName());
		attendanceListQueryModel.setType(ActionType.GETATTENDANCELISTS);	
		
		RPC.performAttendanceListQueryService.performAttendanceListQuery(attendanceListQueryModel, new AsyncCallback<AttendanceListQueryResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with the attendance list query...");				
			}

			@Override
			public void onSuccess(AttendanceListQueryResult result) {
				
				attendanceListMenu.clearItems();
				attendeeMenu.clearItems();
				
				ArrayList<AttendanceList> attendanceLists = result.getAttendanceLists();
				
				if(attendanceLists.isEmpty()) {
					
					attendanceListMenu.addItem("No attendance lists!", (Command) null);
				} else {
					for (final AttendanceList a : attendanceLists) {
						
						attendanceListMenu.addItem(new MenuItem(a.getName(), false, new Command() {
							public void execute() {		
								attendanceListNameLabel.setText(a.getName());
								handleAttendanceListClick(a.getName());
							}				
						}));							
					}
				}
			}
		
		});
	}
	
	public void handleAttendanceListClick(String name) {
		
		attendeeQueryModel.setAccountName(Site.currentUser.getAccountName());
		attendeeQueryModel.setAttendanceListName(name);
		attendeeQueryModel.setType(ActionType.GETATTENDEES);
		
		RPC.performAttendeeQueryService.PerformAttendeeQuery(attendeeQueryModel, new AsyncCallback<AttendeeQueryResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with the attendance list query...");				
			}

			@Override
			public void onSuccess(AttendeeQueryResult result) {
				attendeeMenu.clearItems();
				
				ArrayList<Attendee> attendees = result.getAttendees();
					
				if(attendees.isEmpty()) {
					
					attendeeMenu.addItem(new MenuItem("There is currently no one registered to this attendance list", false, (Command) null));
				}  else {
					
					for (final Attendee a : attendees) {
						attendeeMenu.addItem(new MenuItem("Party: " + a.getName() + " Attending: " + a.getNumAttending(), false, new Command() {
							public void execute() {			
								handleAttendeeListClick(a.getName());
							}				
						}));				
					}
				}
			}		
		});	
	}
	
	public void handleAttendeeSubmitAdd() {
		
		editAttendeeModel.setAccountName(Site.currentUser.getAccountName());
		editAttendeeModel.setAttendanceListName(attendanceListNameLabel.getText());
		editAttendeeModel.setName(attendeeNameTextBox.getText());
		editAttendeeModel.setNumAttending(numberAttendingIntegerBox.getValue());
		editAttendeeModel.setType(ActionType.ADDATTENDEE);
		
		RPC.performEditAttendeeService.PerformEditAttendee(editAttendeeModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: Something went wrong adding an attendee...");			
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleAttendanceListClick(editAttendeeModel.getAttendanceListName());
				}
			}			
		});
		
		attendeeNameLabel.setText("");
		removeAttendeeWidgets();
	}
	
	public void handleAttendeeSubmitEdit() {
		
		editAttendeeModel.setAccountName(Site.currentUser.getAccountName());
		editAttendeeModel.setAttendanceListName(attendanceListNameLabel.getText());
		editAttendeeModel.setName(attendeeNameLabel.getText());
		editAttendeeModel.setNumAttending(numberAttendingIntegerBox.getValue());
		editAttendeeModel.setType(ActionType.EDITATTENDEE);
		
		RPC.performEditAttendeeService.PerformEditAttendee(editAttendeeModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: Something went wrong editing an attendee...");			
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleAttendanceListClick(editAttendeeModel.getAttendanceListName());
				}
			}			
		});
		
		attendeeNameLabel.setText("");
		removeAttendeeWidgets();
	}
	
	public void handleAttendeeDelete() {
		
		editAttendeeModel.setAccountName(Site.currentUser.getAccountName());
		editAttendeeModel.setAttendanceListName(attendanceListNameLabel.getText());
		editAttendeeModel.setName(attendeeNameLabel.getText());
		editAttendeeModel.setType(ActionType.DELETEATTENDEE);
		
		RPC.performEditAttendeeService.PerformEditAttendee(editAttendeeModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: Something went wrong deleting an attendee...");			
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleAttendanceListClick(editAttendeeModel.getAttendanceListName());
				}
			}			
		});
		
		attendeeNameLabel.setText("");
		removeAttendeeWidgets();
	}
	
	
	public void handleAttendeeListClick(String name) {
		attendeeNameLabel.setText(name);
	}
}
