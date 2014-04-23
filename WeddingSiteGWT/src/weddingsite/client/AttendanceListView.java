package weddingsite.client;


import weddingsite.model.Attendee;
import weddingsite.shared.ActionType;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;
import weddingsite.shared.AttendeeQueryModel;
import weddingsite.shared.AttendeeQueryResult;
import weddingsite.shared.EditAttendeeModel;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;
import weddingsite.shared.Login;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class AttendanceListView  extends Composite implements ISubscriber {
	
	private enum ViewModes {
		INIT,
		NORMAL,
		EDITING,
	}
	
	private MenuBar attendanceListMenu;
	private AttendanceListQueryModel attendanceListModel;
	private AttendeeQueryModel attendeeModel;
	private LayoutPanel layoutPanel;
	private Label attendanceListLabel;
	private MenuBar attendeeMenu;
	private FlowPanel attendeeFlowPanel;
	private FlowPanel attendanceListFlowPanel;
	private Button manageAttendeeButton;
	private boolean isValidAttendanceList;
	private EditAttendeeModel editAttendeeModel;
	private EditAttendeeWidgetView editAttendeeView;
	private AddAttendeeWidgetView addAttendeeView;
	private ViewModes currentView;
	private Button addAttendeeButton;
	private Button closeEditingButton;
	
	public AttendanceListView() {
		
		addAttendeeButton = new Button();
		addAttendeeButton.setText("Add Attendee");
		addAttendeeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {				
				removeEditAttendeeFromView();	
				if(layoutPanel.getWidgetIndex(addAttendeeView) == -1) {
					addAddAttendeeToView();
				}
			}
		});
		
		editAttendeeModel = new EditAttendeeModel();
		editAttendeeModel.setAccountName(Site.currentUser.getAccountName());
		
		layoutPanel = new LayoutPanel();
		layoutPanel.setStyleName("layout");
		initWidget(layoutPanel);
		layoutPanel.setSize("100%", "100%");
		
		addAttendeeView = new AddAttendeeWidgetView();
		addAttendeeView.getSubmitButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				editAttendeeModel.setType(ActionType.ADDATTENDEE);
				editAttendeeModel.setName(addAttendeeView.getAttendeeTextBox().getText());
				editAttendeeModel.setNumAttending(Integer.parseInt(addAttendeeView.getNumberAttendingList().getItemText(addAttendeeView.getNumberAttendingList().getSelectedIndex())));
				RPC.performEditAttendeeService.PerformEditAttendee(editAttendeeModel, new AsyncCallback<EditDataResult>() {

					@Override
					public void onFailure(Throwable caught) {
						
					}

					@Override
					public void onSuccess(EditDataResult result) {		
						if(result.getResult()) {
							handleAttendanceListClick(editAttendeeModel.getAttendanceListName());
						}
					}							
				});
			}			
		});
		
		editAttendeeView = new EditAttendeeWidgetView();
		
		closeEditingButton = new Button();
		closeEditingButton.setText("Close Editing");
		closeEditingButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				switchViewMode(ViewModes.NORMAL);
				removeEditAttendeeFromView();
			}
		});		
			
		attendanceListFlowPanel = new FlowPanel();
		attendanceListMenu = new MenuBar(true);
		attendanceListFlowPanel.add(attendanceListMenu);
		attendanceListMenu.setSize("100%", "100%");	

		PageView pageView = new PageView();
		layoutPanel.add(pageView);
		layoutPanel.setWidgetTopBottom(pageView, 93.7,  Unit.PCT, 0.0, Unit.PCT);
		layoutPanel.setWidgetLeftRight(pageView, 0.0, Unit.PCT, 30, Unit.PCT);
		
		attendanceListLabel = new Label("Attendance Lists");
		layoutPanel.add(attendanceListLabel);
		layoutPanel.setWidgetLeftWidth(attendanceListLabel, 25, Unit.PCT, 50, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendanceListLabel, 5, Unit.PCT, 10, Unit.PCT);
		attendanceListLabel.setStyleName("attendanceList");
		
		attendeeFlowPanel = new FlowPanel();	
		attendeeMenu = new MenuBar(true);
		attendeeFlowPanel.add(attendeeMenu);
		attendeeMenu.setSize("100%", "100%");
		isValidAttendanceList = false;
		
		manageAttendeeButton = new Button("New button");
		manageAttendeeButton.setText("Manage Parties");
		manageAttendeeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(isValidAttendanceList) {					
					switchViewMode(ViewModes.EDITING);
				}
			}
		});
		
		//TEST
		//addManageAttendeeButtonToView();
		//addAttendanceListToView();
		//addAttendeeListToViewNormal();
		//TEST
		
		switchViewMode(ViewModes.INIT);
		
		this.setAttendanceListQueryModel(new AttendanceListQueryModel());
		attendanceListModel.setWeddingName(Site.currentUser.getAccountName());
		attendanceListModel.setType(ActionType.GETATTENDANCELISTS);
		
		this.setAttendeeQueryModel(new AttendeeQueryModel());
		
		RPC.performAttendanceListQueryService.performAttendanceListQuery(attendanceListModel, new AsyncCallback<AttendanceListQueryResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with the attendance list query...");				
			}

			@Override
			public void onSuccess(AttendanceListQueryResult result) {
				for (final AttendanceList a : result.getAttendanceLists()) {
					
					MenuItem m = new MenuItem(a.getName(), false, new Command() {
						public void execute() {			
							handleAttendanceListClick(a.getName());
						}				
					});				
					
					attendanceListMenu.addItem(m);
				}
			}
		
		});
	}
	
	public void addCloseEditingButtonToView() {
		layoutPanel.add(closeEditingButton);
		layoutPanel.setWidgetLeftWidth(closeEditingButton, 0, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(closeEditingButton, 0,  Unit.PCT, 12,  Unit.PCT);
	}
	
	public void removeCloseEditingButtonFromView() {
		layoutPanel.remove(closeEditingButton);
	}
	
	public void addAddAttendeeToView() {
		layoutPanel.add(addAttendeeView);
		layoutPanel.setWidgetLeftWidth(addAttendeeView, 30, Unit.PCT, 40, Unit.PCT);
		layoutPanel.setWidgetTopHeight(addAttendeeView, 15,  Unit.PCT, 40,  Unit.PCT);	
	}
	
	public void removeAddAttendeeFromView() {
		layoutPanel.remove(addAttendeeView);
	}
	
	public void addEditAttendeeToView() {
		layoutPanel.add(editAttendeeView);
		layoutPanel.setWidgetLeftWidth(editAttendeeView, 30, Unit.PCT, 40, Unit.PCT);
		layoutPanel.setWidgetTopHeight(editAttendeeView, 15,  Unit.PCT, 40,  Unit.PCT);	
	}
	
	public void removeEditAttendeeFromView() {
		layoutPanel.remove(editAttendeeView);
	}
	
	public void addAttendanceListToView() {		
		layoutPanel.add(attendanceListFlowPanel);
		layoutPanel.setWidgetLeftWidth(attendanceListFlowPanel, 15, Unit.PCT, 25, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendanceListFlowPanel, 15,  Unit.PCT, 60,  Unit.PCT);		
	}
	
	public void removeAttendanceListFromView() {
		layoutPanel.remove(attendanceListFlowPanel);
	}
	
	public void addAttendeeListToViewNormal() {
		layoutPanel.add(attendeeFlowPanel);
		layoutPanel.setWidgetLeftWidth(attendeeFlowPanel, 60, Unit.PCT, 25, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendeeFlowPanel, 15,  Unit.PCT, 60,  Unit.PCT);
	}
	
	public void removeAttendeeListFromView() {
		layoutPanel.remove(attendeeFlowPanel);
	}
	
	public void addManageAttendeeButtonToView() {
		layoutPanel.add(manageAttendeeButton);
		layoutPanel.setWidgetLeftWidth(manageAttendeeButton, 70, Unit.PCT, 15, Unit.PCT);
		layoutPanel.setWidgetTopHeight(manageAttendeeButton, 80, Unit.PCT, 8, Unit.PCT);
	}
	
	public void addAddAttendeeButtonToView() {
		layoutPanel.add(addAttendeeButton);
		layoutPanel.setWidgetLeftWidth(addAttendeeButton, 2.5, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(addAttendeeButton, 80,  Unit.PCT, 15,  Unit.PCT);
	}
	
	public void removeAddAttendeeButtonFromView() {
		layoutPanel.remove(addAttendeeButton);
	}
	
	public void addAttendeeListToViewEditing() {
		layoutPanel.add(attendeeFlowPanel);
		layoutPanel.setWidgetLeftWidth(attendeeFlowPanel, 0, Unit.PCT, 25, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendeeFlowPanel, 15,  Unit.PCT, 60,  Unit.PCT);
	}
	
	public void removeManageAttendeeButtonFromView() {
		layoutPanel.remove(manageAttendeeButton);
	}
	
	public void setAttendanceListQueryModel(AttendanceListQueryModel model) {
		this.attendanceListModel = model;
		this.attendanceListModel.subscribe(Login.Events.VALUE_OR_ACTION_TYPE_CHANGED, this);
	}
	
	public void setAttendeeQueryModel(AttendeeQueryModel model) {
		this.attendeeModel = model;
		this.attendeeModel.subscribe(Login.Events.VALUE_OR_ACTION_TYPE_CHANGED, this);
	}
	
	public void handleAttendanceListClick(final String attendanceListName) {
		attendeeModel.setAccountName(Site.currentUser.getAccountName());
		attendeeModel.setAttendanceListName(attendanceListName);
		attendeeModel.setType(ActionType.GETATTENDEES);
		
		RPC.performAttendeeQueryService.PerformAttendeeQuery(attendeeModel, new AsyncCallback<AttendeeQueryResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with the attendance list query...");				
			}

			@Override
			public void onSuccess(AttendeeQueryResult result) {
				attendeeMenu.clearItems();
				for (final Attendee a : result.getAttendees()) {
					attendeeMenu.addItem(new MenuItem("Party: " + a.getName() + " Attending: " + a.getNumAttending(), false, new Command() {
						public void execute() {			
							removeAddAttendeeFromView();
							handleAttendeeListClick(a);
							editAttendeeModel.setAttendanceListName(attendanceListName);
						}				
					}));				
				}
				
				if(result.getAttendees().size() == 0) {
					attendeeMenu.addItem(new MenuItem("There is currently no one registered to this attendance list", false, (Command) null));
					isValidAttendanceList = false;
				} else {
					isValidAttendanceList = true;
				}
			}		
		});		
	}
	
	public void handleAttendeeListClick(final Attendee a) {
		
		if(currentView == ViewModes.EDITING) {
			if(layoutPanel.getWidgetIndex(editAttendeeView) == -1) {
				addEditAttendeeToView();
			}
						
			editAttendeeView.getAttendeeLabel().setText("Party Name: \n" + a.getName());			
			
			editAttendeeView.getNumberAttendingTextBox().setText("" + a.getNumAttending());;
			
			editAttendeeModel.setName(a.getName());
			editAttendeeModel.setNumAttending(a.getNumAttending());
			
			editAttendeeView.getSubmitChangesButton().addClickHandler(new ClickHandler() {			
				public void onClick(ClickEvent event) {
					if(isValidAttendanceList) {				
						
						editAttendeeModel.setType(ActionType.EDITATTENDEE);
						editAttendeeModel.setNumAttending(Integer.parseInt(editAttendeeView.getNumberAttendingTextBox().getText()));
						
						RPC.performEditAttendeeService.PerformEditAttendee(editAttendeeModel, new AsyncCallback<EditDataResult>() {

							@Override
							public void onFailure(Throwable caught) {
								
							}

							@Override
							public void onSuccess(EditDataResult result) {		
								if(result.getResult()) {
									handleAttendanceListClick(editAttendeeModel.getAttendanceListName());
								}
							}							
						});
												
						removeEditAttendeeFromView();
					}
				}
			});
			
			editAttendeeView.getRemoveButton().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					
					editAttendeeModel.setType(ActionType.DELETEATTENDEE);
					
					if(isValidAttendanceList) {
						
						RPC.performEditAttendeeService.PerformEditAttendee(editAttendeeModel, new AsyncCallback<EditDataResult>() {

							@Override
							public void onFailure(Throwable caught) {
								
							}

							@Override
							public void onSuccess(EditDataResult result) {
								if(result.getResult()) {
									handleAttendanceListClick(editAttendeeModel.getAttendanceListName());
								}							
							}
							
						});
						
						
						removeEditAttendeeFromView();
					}
				}			
			});
			
			
		}
	}
	
	public void switchViewMode(ViewModes v) {
		switch(v) {
		
			case INIT:
				addAttendanceListToView();
				addAttendeeListToViewNormal();
				
				if(Site.currentUser.getIsAdmin()) {
					addManageAttendeeButtonToView();
				}
				currentView = ViewModes.INIT;
			break;
		
			case NORMAL:
				removeCloseEditingButtonFromView();
				removeAttendeeListFromView();
				removeAddAttendeeButtonFromView();
				removeEditAttendeeFromView();
				addAttendanceListToView();
				addAttendeeListToViewNormal();	
				if(Site.currentUser.getIsAdmin()) {
					addManageAttendeeButtonToView();
				}
				currentView = ViewModes.NORMAL;
			break;
			
			case EDITING:
				if(Site.currentUser.getIsAdmin()) {
					removeManageAttendeeButtonFromView();
				}
				addCloseEditingButtonToView();
				removeAttendanceListFromView();
				removeAttendeeListFromView();
				addAddAttendeeButtonToView();
				addAttendeeListToViewEditing();
				currentView = ViewModes.EDITING;
			break;
			
			default:
				throw new UnsupportedOperationException("Unknown operation type: " + v);
		
		}
	}
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		//Nothing for now	
	}
}
