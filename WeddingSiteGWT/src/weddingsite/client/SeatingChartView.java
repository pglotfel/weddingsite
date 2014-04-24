package weddingsite.client;

import java.util.ArrayList;

import weddingsite.shared.ActionType;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditSeatingChartModel;
import weddingsite.shared.GetPeopleAtTableModel;
import weddingsite.shared.GetPeopleAtTableResult;
import weddingsite.shared.GetTablesModel;
import weddingsite.shared.GetTablesResult;
import weddingsite.shared.Person;
import weddingsite.shared.SeatingChart;
import weddingsite.shared.SeatingChartQueryModel;
import weddingsite.shared.SeatingChartQueryResult;
import weddingsite.shared.Table;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TextBox;

public class SeatingChartView extends Composite {
	
	private MenuBar seatingChartMenu;
	private MenuBar tableMenu;
	private MenuBar peopleMenu;
	private FlowPanel seatingChartFlowPanel;
	private FlowPanel tableFlowPanel;
	private FlowPanel peopleFlowPanel;
	private SeatingChartQueryModel seatingChartQueryModel;
	private GetTablesModel getTablesModel;
	private GetPeopleAtTableModel getPeopleAtTableModel;
	private FlowPanel addSeatingChartButtonFlowPanel;
	private FlowPanel editSeatingChartButtonFlowPanel;
	private FlowPanel submitSeatingChartFlowPanel;
	private FlowPanel cancelSeatingChartFlowPanel;
	private FlowPanel deleteSeatingChartFlowPanel;
	private Label seatingChartNewNameLabel;
	private TextBox newNameTextBox;
	private Label seatingChartNameLabel;
	private LayoutPanel layoutPanel;
	private EditSeatingChartModel editSeatingChartModel;
	
	public SeatingChartView() {
		
		editSeatingChartModel = new EditSeatingChartModel();
		getTablesModel = new GetTablesModel();
		seatingChartQueryModel = new SeatingChartQueryModel();
		getPeopleAtTableModel = new GetPeopleAtTableModel();
		
		layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("100%", "100%");
		
		Label titleLable = new Label("Seating Charts");
		titleLable.setStyleName("attendanceList");
		layoutPanel.add(titleLable);
		layoutPanel.setWidgetLeftWidth(titleLable, 25, Unit.PCT, 50, Unit.PCT);
		layoutPanel.setWidgetTopHeight(titleLable, 5, Unit.PCT, 10, Unit.PCT);
		
		seatingChartFlowPanel = new FlowPanel();
		layoutPanel.add(seatingChartFlowPanel);
		layoutPanel.setWidgetLeftWidth(seatingChartFlowPanel, 10, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(seatingChartFlowPanel, 20, Unit.PCT, 40, Unit.PCT);
		
		seatingChartMenu = new MenuBar(true);
		seatingChartFlowPanel.add(seatingChartMenu);
		seatingChartMenu.setSize("100%", "100%");
		seatingChartMenu.setStyleName("gwt-MenuBar-vertical");
		
		
		//Seating chart RPC call
		loadSeatingChart();
		
		
		tableFlowPanel = new FlowPanel();
		layoutPanel.add(tableFlowPanel);
		layoutPanel.setWidgetLeftWidth(tableFlowPanel, 40, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(tableFlowPanel, 20, Unit.PCT, 40, Unit.PCT);
		
		tableMenu = new MenuBar(true);
		tableFlowPanel.add(tableMenu);
		tableMenu.setSize("100%", "100%");
		tableMenu.setStyleName("gwt-MenuBar-vertical");
		
		peopleFlowPanel = new FlowPanel();
		layoutPanel.add(peopleFlowPanel);
		layoutPanel.setWidgetLeftWidth(peopleFlowPanel, 70, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(peopleFlowPanel, 20, Unit.PCT, 40, Unit.PCT);
		
		peopleMenu = new MenuBar(true);
		peopleFlowPanel.add(peopleMenu);
		peopleMenu.setSize("100%", "100%");
		peopleMenu.setStyleName("gwt-MenuBar-vertical");
		
		addSeatingChartButtonFlowPanel = new FlowPanel();
		layoutPanel.add(addSeatingChartButtonFlowPanel);
		layoutPanel.setWidgetLeftWidth(addSeatingChartButtonFlowPanel, 12.9, Unit.PCT, 5.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(addSeatingChartButtonFlowPanel, 66.6, Unit.PCT, 5.9, Unit.PCT);
		
		Button addSeatingChartButton = new Button("New button");
		addSeatingChartButtonFlowPanel.add(addSeatingChartButton);
		addSeatingChartButton.setSize("100%", "100%");
		addSeatingChartButton.setText("Add");
		
		addSeatingChartButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleAddSeatingChartClick();			
			}
			
		});
		
		editSeatingChartButtonFlowPanel = new FlowPanel();
		layoutPanel.add(editSeatingChartButtonFlowPanel);
		layoutPanel.setWidgetLeftWidth(editSeatingChartButtonFlowPanel, 21.9, Unit.PCT, 5.1, Unit.PCT);
		layoutPanel.setWidgetTopHeight(editSeatingChartButtonFlowPanel, 66.7, Unit.PCT, 5.9, Unit.PCT);
		
		Button editSeatingChartButton = new Button("New button");
		editSeatingChartButtonFlowPanel.add(editSeatingChartButton);
		editSeatingChartButton.setSize("100%", "100%");
		editSeatingChartButton.setText("Edit");
		
		editSeatingChartButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleEditSeatingChartClick();
			}		
		});
		
		submitSeatingChartFlowPanel = new FlowPanel();
		
		Button submitSeatingChartButton = new Button("New button");
		submitSeatingChartFlowPanel.add(submitSeatingChartButton);
		submitSeatingChartButton.setText("Submit");
		submitSeatingChartButton.setSize("100%", "100%");
		
		submitSeatingChartButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(layoutPanel.getWidgetIndex(deleteSeatingChartFlowPanel) == -1) {
					handleSubmitAddSeatingChartClick();
					
				} else {
					handleSubmitEditSeatingChartClick();
				}
				
			}		
		});
		
		cancelSeatingChartFlowPanel = new FlowPanel();
		
		Button cancelSeatingChartButton = new Button("New button");
		cancelSeatingChartFlowPanel.add(cancelSeatingChartButton);
		cancelSeatingChartButton.setText("Cancel");
		cancelSeatingChartButton.setSize("100%", "100%");
		
		cancelSeatingChartButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleCancelSeatingChartClick();
			}
			
		});
		
		deleteSeatingChartFlowPanel = new FlowPanel();
		
		Button deleteSeatingChartButton = new Button("New button");
		deleteSeatingChartButton.setText("Delete");
		deleteSeatingChartFlowPanel.add(deleteSeatingChartButton);
		deleteSeatingChartButton.setSize("100%", "100%");
		
		deleteSeatingChartButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleDeleteSeatingChartClick();			
			}
			
		});
		
		seatingChartNewNameLabel = new Label("New name:");
		
		newNameTextBox = new TextBox();
		
		seatingChartNameLabel = new Label("Click on a seating chart!");
		layoutPanel.add(seatingChartNameLabel);
		layoutPanel.setWidgetLeftWidth(seatingChartNameLabel, 10.0, Unit.PCT, 20.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(seatingChartNameLabel, 61.2, Unit.PCT, 4.6, Unit.PCT);
	}
	
	public void addNewNameTextBoxToView() {
		layoutPanel.add(newNameTextBox);
		layoutPanel.setWidgetLeftWidth(newNameTextBox, 21.8, Unit.PCT, 13.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(newNameTextBox, 73.4, Unit.PCT, 4.4, Unit.PCT);
		layoutPanel.add(seatingChartNewNameLabel);
		layoutPanel.setWidgetLeftWidth(seatingChartNewNameLabel, 9.2, Unit.PCT, 8.6, Unit.PCT);
		layoutPanel.setWidgetTopHeight(seatingChartNewNameLabel, 73.6, Unit.PCT, 4.3, Unit.PCT);
	}
	
	public void removeNewNameTextBoxFromView() {
		layoutPanel.remove(newNameTextBox);
		layoutPanel.remove(seatingChartNewNameLabel);
		newNameTextBox.setText("");
	}
	
	public void addDeleteSeatingChartFlowPanelToView() {
		layoutPanel.add(deleteSeatingChartFlowPanel);
		layoutPanel.setWidgetLeftWidth(deleteSeatingChartFlowPanel, 16.5, Unit.PCT, 6.8, Unit.PCT);
		layoutPanel.setWidgetTopHeight(deleteSeatingChartFlowPanel, 85.7, Unit.PCT, 5.9, Unit.PCT);
	}
	
	public void removeDeleteSeatingChartFlowPanelFromView() {
		layoutPanel.remove(deleteSeatingChartFlowPanel);
	}
	
	public void addCancelSeatingChartFlowPanelToView() {
		layoutPanel.add(cancelSeatingChartFlowPanel);
		layoutPanel.setWidgetLeftWidth(cancelSeatingChartFlowPanel, 21.8, Unit.PCT, 6.9, Unit.PCT);
		layoutPanel.setWidgetTopHeight(cancelSeatingChartFlowPanel, 78.8, Unit.PCT, 5.9, Unit.PCT);
	}
	
	public void removeCancelSeatingChartFlowPanelFromView() {
		layoutPanel.remove(cancelSeatingChartFlowPanel);
	}
	
	public void addSubmitSeatingChartFlowPanelToView() {
		layoutPanel.add(submitSeatingChartFlowPanel);
		layoutPanel.setWidgetLeftWidth(submitSeatingChartFlowPanel, 11.2, Unit.PCT, 6.8, Unit.PCT);
		layoutPanel.setWidgetTopHeight(submitSeatingChartFlowPanel, 78.8, Unit.PCT, 5.9, Unit.PCT);
	}
	
	public void removeSubmitSeatingChartFlowPanelFromView() {
		layoutPanel.remove(submitSeatingChartFlowPanel);
	}
	
	public void loadSeatingChart() {
		
		seatingChartQueryModel.setAccountName(Site.currentUser.getAccountName());
		seatingChartQueryModel.setType(ActionType.GETSEATINGCHARTS);
		RPC.performSeatingChartQueryService.performSeatingChartQuery(seatingChartQueryModel, new AsyncCallback<SeatingChartQueryResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(SeatingChartQueryResult result) {
				
				seatingChartMenu.clearItems();
				tableMenu.clearItems();
				peopleMenu.clearItems();
				
				ArrayList<SeatingChart> seatingCharts = result.getSeatingCharts();			
				
				if(seatingCharts.isEmpty()) {
					
					seatingChartMenu.addItem(new MenuItem("There are currently no seating charts", false, (Command) null));
				} else {
				
					for(final SeatingChart s : seatingCharts) {	
												
						seatingChartMenu.addItem(new MenuItem(s.getName(), false, new Command() {
							@Override
							public void execute() {							
								handleSeatingChartClick(s);
							}					
						}));
					}
					
				}
			}
			
		});
	}
	
	public void handleDeleteSeatingChartClick() {	
		
		editSeatingChartModel.setAccountName(Site.currentUser.getAccountName());
		editSeatingChartModel.setSeatingChartName(seatingChartNameLabel.getText());
		editSeatingChartModel.setType(ActionType.DELETESEATINGCHART);
		
		RPC.editSeatingChartService.addSeatingChart(editSeatingChartModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					loadSeatingChart();
				}			
			}		
		});
		
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
	}
	
	public void handleSubmitAddSeatingChartClick() {
		
		editSeatingChartModel.setAccountName(Site.currentUser.getAccountName());
		editSeatingChartModel.setSeatingChartName(newNameTextBox.getValue());
		editSeatingChartModel.setType(ActionType.ADDSEATINGCHART);
		
		RPC.editSeatingChartService.addSeatingChart(editSeatingChartModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					loadSeatingChart();
				}			
			}
			
		});	
		
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
	}
	
	public void handleSubmitEditSeatingChartClick() {
		
		editSeatingChartModel.setAccountName(Site.currentUser.getAccountName());
		editSeatingChartModel.setSeatingChartName(seatingChartNameLabel.getText());
		editSeatingChartModel.setNewName(newNameTextBox.getValue());
		editSeatingChartModel.setType(ActionType.EDITSEATINGCHART);
		
		RPC.editSeatingChartService.addSeatingChart(editSeatingChartModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					loadSeatingChart();
				}			
			}
			
		});	
		
		
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
	}
	
	public void handleCancelSeatingChartClick() {
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
	}
	
	public void handleAddSeatingChartClick() {
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
		addNewNameTextBoxToView();
		addSubmitSeatingChartFlowPanelToView();
		addCancelSeatingChartFlowPanelToView();
	}
	
	public void handleEditSeatingChartClick() {
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
		addNewNameTextBoxToView();
		addSubmitSeatingChartFlowPanelToView();
		addCancelSeatingChartFlowPanelToView();
		addDeleteSeatingChartFlowPanelToView();
	}
	
	public void handleSeatingChartClick(SeatingChart seatingChart) {
		
		seatingChartNameLabel.setText(seatingChart.getName());
		
		getTablesModel.setAccountName(Site.currentUser.getAccountName());
		getTablesModel.setSeatingChartName(seatingChart.getName());
		getTablesModel.setType(ActionType.GETTABLES);
		getPeopleAtTableModel.setSeatingChartName(seatingChart.getName());
		
		RPC.getTablesService.getTables(getTablesModel, new AsyncCallback<GetTablesResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(GetTablesResult result) {
				
				ArrayList<Table> tables = result.getTables();
				tableMenu.clearItems();	
				peopleMenu.clearItems();
				
				if (tables.isEmpty()) {
					tableMenu.addItem(new MenuItem("There are currently no tables in this list", false, (Command) null));
				} else {
					for (final Table t : tables) {
						tableMenu.addItem(new MenuItem(t.getName(), false, new Command() {
							@Override
							public void execute() {
								handleTableClick(t);							
							}					
						}));
					}
				}
				
			}			
		});
		
	}
	
	public void handleTableClick(Table t) {
		
		getPeopleAtTableModel.setAccountName(Site.currentUser.getAccountName());
		getPeopleAtTableModel.setTableName(t.getName());
		getPeopleAtTableModel.setType(ActionType.GETPEOPLEATTABLE);
		
		RPC.getPeopleAtTable.getPeopleAtTable(getPeopleAtTableModel, new AsyncCallback<GetPeopleAtTableResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(GetPeopleAtTableResult result) {
				
				ArrayList<Person> people = result.getPeople();
				peopleMenu.clearItems();
				
				if(people.isEmpty()) {
					peopleMenu.addItem(new MenuItem("No people are currently at this table", false, (Command) null));
				} else {
					for (final Person p : people) {
						peopleMenu.addItem(new MenuItem(p.getName(), false, (Command) null));
					}
				}
				
			}
			
		});
		
	}
}
