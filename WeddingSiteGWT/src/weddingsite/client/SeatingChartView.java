package weddingsite.client;

import java.util.ArrayList;

import weddingsite.shared.ActionType;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditPersonAtTableModel;
import weddingsite.shared.EditSeatingChartModel;
import weddingsite.shared.EditTablesModel;
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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

public class SeatingChartView extends Composite {
	
	private FlowPanel mainFlowPanel;
	private ScrollPanel scrollPanel;
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
	private EditTablesModel editTableModel;
	private EditPersonAtTableModel editPersonAtTableModel;
	
	private FlowPanel addTableButtonFlowPanel;
	private FlowPanel editTableButtonFlowPanel;
	private FlowPanel submitTableFlowPanel;
	private FlowPanel cancelTableFlowPanel;
	private FlowPanel deleteTableFlowPanel;
	private Label tableNewNameLabel;
	private TextBox tableNewNameTextBox;
	private Label tableNameLabel;
	
	private FlowPanel addPersonButtonFlowPanel;
	private FlowPanel editPersonButtonFlowPanel;
	private FlowPanel submitPersonFlowPanel;
	private FlowPanel cancelPersonFlowPanel;
	private FlowPanel deletePersonFlowPanel;
	private Label personNewNameLabel;
	private TextBox personNewNameTextBox;
	private Label personNameLabel;
	
	public SeatingChartView() {
		
		editPersonAtTableModel = new EditPersonAtTableModel();
		editTableModel = new EditTablesModel();
		editSeatingChartModel = new EditSeatingChartModel();
		getTablesModel = new GetTablesModel();
		seatingChartQueryModel = new SeatingChartQueryModel();
		getPeopleAtTableModel = new GetPeopleAtTableModel();
		
		mainFlowPanel = new FlowPanel();
		mainFlowPanel.setStyleName("Background");
		initWidget(mainFlowPanel);
		mainFlowPanel.setSize("100%", "100%");
		
		scrollPanel = new ScrollPanel();
		scrollPanel.setStyleName("Background");
		mainFlowPanel.add(scrollPanel);
		scrollPanel.setSize("800px", "900px");
		
		layoutPanel = new LayoutPanel();
		layoutPanel.setStyleName("InnerBackground");
		scrollPanel.add(layoutPanel);
		layoutPanel.setSize("750px", "850px");
		
		PageView pageView = new PageView();
		layoutPanel.add(pageView);
		layoutPanel.setWidgetTopBottom(pageView, 93.7,  Unit.PCT, 0.0, Unit.PCT);
		layoutPanel.setWidgetLeftRight(pageView, 0.0, Unit.PCT, 30, Unit.PCT);
		
		Label titleLable = new Label("Seating Charts");
		titleLable.setStyleName("CenterTitles");
		layoutPanel.add(titleLable);
		layoutPanel.setWidgetLeftWidth(titleLable, 25, Unit.PCT, 50, Unit.PCT);
		layoutPanel.setWidgetTopHeight(titleLable, 5, Unit.PCT, 10, Unit.PCT);
		
		seatingChartFlowPanel = new FlowPanel();
		seatingChartFlowPanel.setStyleName("Background");
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
		tableFlowPanel.setStyleName("InnerBackground");
		layoutPanel.add(tableFlowPanel);
		layoutPanel.setWidgetLeftWidth(tableFlowPanel, 40, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(tableFlowPanel, 20, Unit.PCT, 40, Unit.PCT);
		
		tableMenu = new MenuBar(true);
		tableFlowPanel.add(tableMenu);
		tableMenu.setSize("100%", "100%");
		tableMenu.setStyleName("gwt-MenuBar-vertical");
		
		peopleFlowPanel = new FlowPanel();
		peopleFlowPanel.setStyleName("InnerBackground");
		layoutPanel.add(peopleFlowPanel);
		layoutPanel.setWidgetLeftWidth(peopleFlowPanel, 70, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(peopleFlowPanel, 20, Unit.PCT, 40, Unit.PCT);
		
		peopleMenu = new MenuBar(true);
		peopleFlowPanel.add(peopleMenu);
		peopleMenu.setSize("100%", "100%");
		peopleMenu.setStyleName("gwt-MenuBar-vertical");
		
		if(Site.currentUser.getIsAdmin()) {
			addSeatingChartButtonFlowPanel = new FlowPanel();
			layoutPanel.add(addSeatingChartButtonFlowPanel);
			layoutPanel.setWidgetLeftWidth(addSeatingChartButtonFlowPanel, 12.9, Unit.PCT, 5.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(addSeatingChartButtonFlowPanel, 66.7, Unit.PCT, 3.2, Unit.PCT);
			
			Button addSeatingChartButton = new Button("New button");
			addSeatingChartButton.setStyleName("ButtonColorScheme");
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
			layoutPanel.setWidgetLeftWidth(editSeatingChartButtonFlowPanel, 21.9, Unit.PCT, 5.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(editSeatingChartButtonFlowPanel, 66.7, Unit.PCT, 3.2, Unit.PCT);
			
			Button editSeatingChartButton = new Button("New button");
			editSeatingChartButton.setStyleName("ButtonColorScheme");
			editSeatingChartButtonFlowPanel.add(editSeatingChartButton);
			editSeatingChartButton.setSize("100%", "100%");
			editSeatingChartButton.setText("Edit");
			
			editSeatingChartButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleEditSeatingChartClick();
				}		
			});		
			
			seatingChartNameLabel = new Label("Click on a seating chart!");
			seatingChartNameLabel.setStyleName("TextColorScheme");
			layoutPanel.add(seatingChartNameLabel);
			layoutPanel.setWidgetLeftWidth(seatingChartNameLabel, 10.0, Unit.PCT, 20.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(seatingChartNameLabel, 61.2, Unit.PCT, 4.9, Unit.PCT);
		}
		
		submitSeatingChartFlowPanel = new FlowPanel();
		
		Button submitSeatingChartButton = new Button("New button");
		submitSeatingChartButton.setStyleName("ButtonColorScheme");
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
		cancelSeatingChartButton.setStyleName("ButtonColorScheme");
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
		deleteSeatingChartButton.setStyleName("ButtonColorScheme");
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
		
		
		if(Site.currentUser.getIsAdmin()) {
			addTableButtonFlowPanel = new FlowPanel();
			layoutPanel.add(addTableButtonFlowPanel);
			layoutPanel.setWidgetLeftWidth(addTableButtonFlowPanel, 42.9, Unit.PCT, 5.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(addTableButtonFlowPanel, 66.7, Unit.PCT, 3.2, Unit.PCT);
			
			Button addTableButton = new Button("New button");
			addTableButton.setStyleName("ButtonColorScheme");
			addTableButtonFlowPanel.add(addTableButton);
			addTableButton.setSize("100%", "100%");
			addTableButton.setText("Add");
			
			addTableButton.addClickHandler(new ClickHandler() {
	
				@Override
				public void onClick(ClickEvent event) {
					handleAddTableClick();			
				}
				
			});
		
			editTableButtonFlowPanel = new FlowPanel();
			layoutPanel.add(editTableButtonFlowPanel);
			layoutPanel.setWidgetLeftWidth(editTableButtonFlowPanel, 51.9, Unit.PCT, 5.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(editTableButtonFlowPanel, 66.7, Unit.PCT, 3.2, Unit.PCT);
			
			Button editTableButton = new Button("New button");
			editTableButton.setStyleName("ButtonColorScheme");
			editTableButtonFlowPanel.add(editTableButton);
			editTableButton.setSize("100%", "100%");
			editTableButton.setText("Edit");
			
			editTableButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleEditTableClick();
				}		
			});
			
			
			tableNameLabel = new Label("Click on a table!");
			tableNameLabel.setStyleName("TextColorScheme");
			layoutPanel.add(tableNameLabel);
			layoutPanel.setWidgetLeftWidth(tableNameLabel, 40.0, Unit.PCT, 20.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(tableNameLabel, 61.2, Unit.PCT, 4.9, Unit.PCT);
		}
		
		submitTableFlowPanel = new FlowPanel();
		
		Button submitTableButton = new Button("New button");
		submitTableButton.setStyleName("ButtonColorScheme");
		submitTableFlowPanel.add(submitTableButton);
		submitTableButton.setText("Submit");
		submitTableButton.setSize("100%", "100%");
		
		submitTableButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(layoutPanel.getWidgetIndex(deleteTableFlowPanel) == -1) {
					handleSubmitAddTableClick();
					
				} else {
					handleSubmitEditTableClick();
				}
				
			}		
		});
		
		cancelTableFlowPanel = new FlowPanel();
		
		Button cancelTableButton = new Button("New button");
		cancelTableButton.setStyleName("ButtonColorScheme");
		cancelTableFlowPanel.add(cancelTableButton);
		cancelTableButton.setText("Cancel");
		cancelTableButton.setSize("100%", "100%");
		
		cancelTableButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleCancelTableClick();
			}
			
		});
		
		deleteTableFlowPanel = new FlowPanel();
		
		Button deleteTableButton = new Button("New button");
		deleteTableButton.setStyleName("ButtonColorScheme");
		deleteTableButton.setText("Delete");
		deleteTableFlowPanel.add(deleteTableButton);
		deleteTableButton.setSize("100%", "100%");
		
		deleteTableButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleDeleteTableClick();			
			}
			
		});
		
		tableNewNameLabel = new Label("New name:");
		
		tableNewNameTextBox = new TextBox();
		
		if(Site.currentUser.getIsAdmin()) {
			addPersonButtonFlowPanel = new FlowPanel();
			layoutPanel.add(addPersonButtonFlowPanel);
			layoutPanel.setWidgetLeftWidth(addPersonButtonFlowPanel, 72.9, Unit.PCT, 5.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(addPersonButtonFlowPanel, 66.7, Unit.PCT, 3.2, Unit.PCT);
			
			Button addPersonButton = new Button("New button");
			addPersonButton.setStyleName("ButtonColorScheme");
			addPersonButtonFlowPanel.add(addPersonButton);
			addPersonButton.setSize("100%", "100%");
			addPersonButton.setText("Add");
			
			addPersonButton.addClickHandler(new ClickHandler() {
	
				@Override
				public void onClick(ClickEvent event) {
					handleAddPersonClick();			
				}
				
			});
		
			editPersonButtonFlowPanel = new FlowPanel();
			layoutPanel.add(editPersonButtonFlowPanel);
			layoutPanel.setWidgetLeftWidth(editPersonButtonFlowPanel, 81.9, Unit.PCT, 5.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(editPersonButtonFlowPanel, 66.7, Unit.PCT, 3.2, Unit.PCT);
			
			Button editPersonButton = new Button("New button");
			editPersonButton.setStyleName("ButtonColorScheme");
			editPersonButtonFlowPanel.add(editPersonButton);
			editPersonButton.setSize("100%", "100%");
			editPersonButton.setText("Edit");
			
			editPersonButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleEditPersonClick();
				}		
			});
			
			personNameLabel = new Label("Click on a person!");
			personNameLabel.setStyleName("TextColorScheme");
			layoutPanel.add(personNameLabel);
			layoutPanel.setWidgetLeftWidth(personNameLabel, 70.0, Unit.PCT, 20.0, Unit.PCT);
			layoutPanel.setWidgetTopHeight(personNameLabel, 61.2, Unit.PCT, 4.9, Unit.PCT);
		}
		
		submitPersonFlowPanel = new FlowPanel();
		
		Button submitPersonButton = new Button("New button");
		submitPersonButton.setStyleName("ButtonColorScheme");
		submitPersonFlowPanel.add(submitPersonButton);
		submitPersonButton.setText("Submit");
		submitPersonButton.setSize("100%", "100%");
		
		submitPersonButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(layoutPanel.getWidgetIndex(deletePersonFlowPanel) == -1) {
					handleSubmitAddPersonClick();					
				} else {
					handleSubmitEditPersonClick();
				}
				
			}		
		});
		
		cancelPersonFlowPanel = new FlowPanel();
		
		Button cancelPersonButton = new Button("New button");
		cancelPersonButton.setStyleName("ButtonColorScheme");
		cancelPersonFlowPanel.add(cancelPersonButton);
		cancelPersonButton.setText("Cancel");
		cancelPersonButton.setSize("100%", "100%");
		
		cancelPersonButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleCancelPersonClick();
			}
			
		});
		
		deletePersonFlowPanel = new FlowPanel();
		
		Button deletePersonButton = new Button("New button");
		deletePersonButton.setStyleName("ButtonColorScheme");
		deletePersonButton.setText("Delete");
		deletePersonFlowPanel.add(deletePersonButton);
		deletePersonButton.setSize("100%", "100%");
		
		deletePersonButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleDeletePersonClick();			
			}
			
		});
		
		personNewNameLabel = new Label("New name:");
		
		personNewNameTextBox = new TextBox();
		
	}
	
	public void addPersonNewNameTextBoxToView() {
		layoutPanel.add(personNewNameTextBox);
		layoutPanel.setWidgetLeftWidth(personNewNameTextBox, 81.8, Unit.PCT, 10.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(personNewNameTextBox, 73.4, Unit.PCT, 3.2, Unit.PCT);
		layoutPanel.add(personNewNameLabel);
		layoutPanel.setWidgetLeftWidth(personNewNameLabel, 69.2, Unit.PCT, 10.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(personNewNameLabel, 73.6, Unit.PCT, 3.2, Unit.PCT);	
	}
	
	public void removePersonNewNameTextBoxFromView() {
		layoutPanel.remove(personNewNameTextBox);
		layoutPanel.remove(personNewNameLabel);
		personNewNameTextBox.setText("");
	}
	
	public void addDeletePersonFlowPanelToView() {
		layoutPanel.add(deletePersonFlowPanel);
		layoutPanel.setWidgetLeftWidth(deletePersonFlowPanel, 76.5, Unit.PCT, 6.8, Unit.PCT);
		layoutPanel.setWidgetTopHeight(deletePersonFlowPanel, 85.7, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeDeletePersonFlowPanelFromView() {
		layoutPanel.remove(deletePersonFlowPanel);
	}
	
	public void addCancelPersonFlowPanelToView() {
		layoutPanel.add(cancelPersonFlowPanel);
		layoutPanel.setWidgetLeftWidth(cancelPersonFlowPanel, 81.8, Unit.PCT, 6.9, Unit.PCT);
		layoutPanel.setWidgetTopHeight(cancelPersonFlowPanel, 78.8, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeCancelPersonFlowPanelFromView() {
		layoutPanel.remove(cancelPersonFlowPanel);
	}
	
	public void addSubmitPersonFlowPanelToView() {
		layoutPanel.add(submitPersonFlowPanel);
		layoutPanel.setWidgetLeftWidth(submitPersonFlowPanel, 71.2, Unit.PCT, 6.8, Unit.PCT);
		layoutPanel.setWidgetTopHeight(submitPersonFlowPanel, 78.8, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeSubmitPersonFlowPanelFromView() {
		layoutPanel.remove(submitPersonFlowPanel);
	}
	
	public void addTableNewNameTextBoxToView() {
		layoutPanel.add(tableNewNameTextBox);
		layoutPanel.setWidgetLeftWidth(tableNewNameTextBox, 51.8, Unit.PCT, 10.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(tableNewNameTextBox, 73.4, Unit.PCT, 3.2, Unit.PCT);
		layoutPanel.add(tableNewNameLabel);
		layoutPanel.setWidgetLeftWidth(tableNewNameLabel, 39.2, Unit.PCT, 10.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(tableNewNameLabel, 73.6, Unit.PCT, 3.2, Unit.PCT);	
	}
	
	public void removeTableNewNameTextBoxFromView() {
		layoutPanel.remove(tableNewNameTextBox);
		layoutPanel.remove(tableNewNameLabel);
		tableNewNameTextBox.setText("");
	}
	
	public void addDeleteTableFlowPanelToView() {
		layoutPanel.add(deleteTableFlowPanel);
		layoutPanel.setWidgetLeftWidth(deleteTableFlowPanel, 46.5, Unit.PCT, 6.8, Unit.PCT);
		layoutPanel.setWidgetTopHeight(deleteTableFlowPanel, 85.7, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeDeleteTableFlowPanelFromView() {
		layoutPanel.remove(deleteTableFlowPanel);
	}
	
	public void addCancelTableFlowPanelToView() {
		layoutPanel.add(cancelTableFlowPanel);
		layoutPanel.setWidgetLeftWidth(cancelTableFlowPanel, 51.8, Unit.PCT, 6.9, Unit.PCT);
		layoutPanel.setWidgetTopHeight(cancelTableFlowPanel, 78.8, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeCancelTableFlowPanelFromView() {
		layoutPanel.remove(cancelTableFlowPanel);
	}
	
	public void addSubmitTableFlowPanelToView() {
		layoutPanel.add(submitTableFlowPanel);
		layoutPanel.setWidgetLeftWidth(submitTableFlowPanel, 41.2, Unit.PCT, 6.8, Unit.PCT);
		layoutPanel.setWidgetTopHeight(submitTableFlowPanel, 78.8, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeSubmitTableFlowPanelFromView() {
		layoutPanel.remove(submitTableFlowPanel);
	}
	
	public void addSeatingChartNewNameTextBoxToView() {
		layoutPanel.add(newNameTextBox);
		layoutPanel.setWidgetLeftWidth(newNameTextBox, 21.8, Unit.PCT, 10.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(newNameTextBox, 73.4, Unit.PCT, 3.2, Unit.PCT);
		layoutPanel.add(seatingChartNewNameLabel);
		layoutPanel.setWidgetLeftWidth(seatingChartNewNameLabel, 9.2, Unit.PCT, 8.6, Unit.PCT);
		layoutPanel.setWidgetTopHeight(seatingChartNewNameLabel, 73.6, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeSeatingChartNewNameTextBoxFromView() {
		layoutPanel.remove(newNameTextBox);
		layoutPanel.remove(seatingChartNewNameLabel);
		newNameTextBox.setText("");
	}
	
	public void addDeleteSeatingChartFlowPanelToView() {
		layoutPanel.add(deleteSeatingChartFlowPanel);
		layoutPanel.setWidgetLeftWidth(deleteSeatingChartFlowPanel, 16.5, Unit.PCT, 6.8, Unit.PCT);
		layoutPanel.setWidgetTopHeight(deleteSeatingChartFlowPanel, 85.7, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeDeleteSeatingChartFlowPanelFromView() {
		layoutPanel.remove(deleteSeatingChartFlowPanel);
	}
	
	public void addCancelSeatingChartFlowPanelToView() {
		layoutPanel.add(cancelSeatingChartFlowPanel);
		layoutPanel.setWidgetLeftWidth(cancelSeatingChartFlowPanel, 21.8, Unit.PCT, 6.9, Unit.PCT);
		layoutPanel.setWidgetTopHeight(cancelSeatingChartFlowPanel, 78.8, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeCancelSeatingChartFlowPanelFromView() {
		layoutPanel.remove(cancelSeatingChartFlowPanel);
	}
	
	public void addSubmitSeatingChartFlowPanelToView() {
		layoutPanel.add(submitSeatingChartFlowPanel);
		layoutPanel.setWidgetLeftWidth(submitSeatingChartFlowPanel, 11.2, Unit.PCT, 6.8, Unit.PCT);
		layoutPanel.setWidgetTopHeight(submitSeatingChartFlowPanel, 78.8, Unit.PCT, 3.2, Unit.PCT);
	}

	
	public void removeSubmitSeatingChartFlowPanelFromView() {
		layoutPanel.remove(submitSeatingChartFlowPanel);
	}
	
	public void addNewNameTextBoxToView() {
		layoutPanel.add(newNameTextBox);
		layoutPanel.setWidgetLeftWidth(newNameTextBox, 21.8, Unit.PCT, 10.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(newNameTextBox, 73.4, Unit.PCT, 3.2, Unit.PCT);
		layoutPanel.add(seatingChartNewNameLabel);
		layoutPanel.setWidgetLeftWidth(seatingChartNewNameLabel, 9.2, Unit.PCT, 10.0, Unit.PCT);
		layoutPanel.setWidgetTopHeight(seatingChartNewNameLabel, 73.6, Unit.PCT, 3.2, Unit.PCT);
	}
	
	public void removeNewNameTextBoxFromView() {
		layoutPanel.remove(newNameTextBox);
		layoutPanel.remove(seatingChartNewNameLabel);
		newNameTextBox.setText("");
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
								handleSeatingChartClick(s.getName());
							}					
						}));
					}
					
				}
			}
			
		});
	}
	
	public void handleDeletePersonClick() {
		
		editPersonAtTableModel.setAccountName(Site.currentUser.getAccountName());
		editPersonAtTableModel.setTableName(tableNameLabel.getText());
		editPersonAtTableModel.setSeatingChartName(seatingChartNameLabel.getText());
		editPersonAtTableModel.setPersonName(personNameLabel.getText());
		editPersonAtTableModel.setType(ActionType.DELETEPERSON);
		
		RPC.editPersonAtTableService.editPersonAtTable(editPersonAtTableModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleTableClick(editPersonAtTableModel.getTableName());
				}
				
			}
			
		});
		
		removeSubmitPersonFlowPanelFromView();
		removeCancelPersonFlowPanelFromView();
		removeDeletePersonFlowPanelFromView();
		removePersonNewNameTextBoxFromView();
	}
	
	public void handleDeleteTableClick() {
		
		editTableModel.setAccountName(Site.currentUser.getAccountName());
		editTableModel.setTableName(tableNameLabel.getText());
		editTableModel.setSeatingChartName(seatingChartNameLabel.getText());
		editTableModel.setType(ActionType.DELETETABLE);
		
		RPC.editTablesService.editTable(editTableModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleSeatingChartClick(editTableModel.getSeatingChartName());
				}
				
			}
			
		});
		
		removeSubmitTableFlowPanelFromView();
		removeCancelTableFlowPanelFromView();
		removeDeleteTableFlowPanelFromView();
		removeTableNewNameTextBoxFromView();
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
	
	public void handleSubmitAddPersonClick() {
		
		editPersonAtTableModel.setAccountName(Site.currentUser.getAccountName());
		editPersonAtTableModel.setTableName(tableNameLabel.getText());
		editPersonAtTableModel.setSeatingChartName(seatingChartNameLabel.getText());
		editPersonAtTableModel.setPersonName(personNewNameTextBox.getText());
		editPersonAtTableModel.setType(ActionType.ADDPERSON);
		
		RPC.editPersonAtTableService.editPersonAtTable(editPersonAtTableModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleTableClick(editPersonAtTableModel.getTableName());
				}
				
			}
			
		});
		
		removeSubmitPersonFlowPanelFromView();
		removeCancelPersonFlowPanelFromView();
		removeDeletePersonFlowPanelFromView();
		removePersonNewNameTextBoxFromView();
	}
	
	public void handleSubmitAddTableClick() {
		
		editTableModel.setAccountName(Site.currentUser.getAccountName());
		editTableModel.setTableName(tableNewNameTextBox.getText());
		editTableModel.setSeatingChartName(seatingChartNameLabel.getText());
		editTableModel.setType(ActionType.ADDTABLE);
		
		RPC.editTablesService.editTable(editTableModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleSeatingChartClick(editTableModel.getSeatingChartName());
				}
				
			}
			
		});
		
		removeSubmitTableFlowPanelFromView();
		removeCancelTableFlowPanelFromView();
		removeDeleteTableFlowPanelFromView();
		removeTableNewNameTextBoxFromView();
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
	
	public void handleSubmitEditPersonClick() {
		
		editPersonAtTableModel.setAccountName(Site.currentUser.getAccountName());
		editPersonAtTableModel.setTableName(tableNameLabel.getText());
		editPersonAtTableModel.setPersonName(personNameLabel.getText());
		editPersonAtTableModel.setNewName(personNewNameTextBox.getText());
		editPersonAtTableModel.setSeatingChartName(seatingChartNameLabel.getText());
		editPersonAtTableModel.setType(ActionType.EDITPERSON);
				
		RPC.editPersonAtTableService.editPersonAtTable(editPersonAtTableModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleTableClick(editPersonAtTableModel.getTableName());
				}
				
			}
			
		});
		
		removeSubmitPersonFlowPanelFromView();
		removeCancelPersonFlowPanelFromView();
		removeDeletePersonFlowPanelFromView();
		removePersonNewNameTextBoxFromView();
	}
	
	public void handleSubmitEditTableClick() {
		
		editTableModel.setAccountName(Site.currentUser.getAccountName());
		editTableModel.setTableName(tableNameLabel.getText());
		editTableModel.setNewName(tableNewNameTextBox.getText());
		editTableModel.setSeatingChartName(seatingChartNameLabel.getText());
		editTableModel.setType(ActionType.EDITTABLE);
		
		RPC.editTablesService.editTable(editTableModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				if(result.getResult()) {
					handleSeatingChartClick(editTableModel.getSeatingChartName());
				}
				
			}
			
		});
		
		removeSubmitTableFlowPanelFromView();
		removeCancelTableFlowPanelFromView();
		removeDeleteTableFlowPanelFromView();
		removeTableNewNameTextBoxFromView();
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
	
	private void handleCancelPersonClick() {
		removeSubmitPersonFlowPanelFromView();
		removeCancelPersonFlowPanelFromView();
		removeDeletePersonFlowPanelFromView();
		removePersonNewNameTextBoxFromView();
	}
	
	private void handleAddPersonClick() {
		removeSubmitPersonFlowPanelFromView();
		removeCancelPersonFlowPanelFromView();
		removeDeletePersonFlowPanelFromView();
		removePersonNewNameTextBoxFromView();
		addPersonNewNameTextBoxToView();
		addSubmitPersonFlowPanelToView();
		addCancelPersonFlowPanelToView();
	}
	
	private void handleEditPersonClick() {
		removeSubmitPersonFlowPanelFromView();
		removeCancelPersonFlowPanelFromView();
		removeDeletePersonFlowPanelFromView();
		removePersonNewNameTextBoxFromView();
		addPersonNewNameTextBoxToView();
		addSubmitPersonFlowPanelToView();
		addCancelPersonFlowPanelToView();
		addDeletePersonFlowPanelToView();
	}
	
	private void handleCancelTableClick() {
		removeSubmitTableFlowPanelFromView();
		removeCancelTableFlowPanelFromView();
		removeDeleteTableFlowPanelFromView();
		removeTableNewNameTextBoxFromView();
	}
	
	private void handleAddTableClick() {
		removeSubmitTableFlowPanelFromView();
		removeCancelTableFlowPanelFromView();
		removeDeleteTableFlowPanelFromView();
		removeTableNewNameTextBoxFromView();
		addTableNewNameTextBoxToView();
		addSubmitTableFlowPanelToView();
		addCancelTableFlowPanelToView();
	}
	
	private void handleEditTableClick() {
		removeSubmitTableFlowPanelFromView();
		removeCancelTableFlowPanelFromView();
		removeDeleteTableFlowPanelFromView();
		removeTableNewNameTextBoxFromView();
		addTableNewNameTextBoxToView();
		addSubmitTableFlowPanelToView();
		addCancelTableFlowPanelToView();
		addDeleteTableFlowPanelToView();
	}
	
	private void handleCancelSeatingChartClick() {
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
	}
	
	private void handleAddSeatingChartClick() {
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
		addNewNameTextBoxToView();
		addSubmitSeatingChartFlowPanelToView();
		addCancelSeatingChartFlowPanelToView();
	}
	
	private void handleEditSeatingChartClick() {
		removeSubmitSeatingChartFlowPanelFromView();
		removeCancelSeatingChartFlowPanelFromView();
		removeDeleteSeatingChartFlowPanelFromView();
		removeNewNameTextBoxFromView();
		addNewNameTextBoxToView();
		addSubmitSeatingChartFlowPanelToView();
		addCancelSeatingChartFlowPanelToView();
		addDeleteSeatingChartFlowPanelToView();
	}
	
	public void handleSeatingChartClick(String name) {
		
		seatingChartNameLabel.setText(name);
		
		getTablesModel.setAccountName(Site.currentUser.getAccountName());
		getTablesModel.setSeatingChartName(name);
		getTablesModel.setType(ActionType.GETTABLES);
		getPeopleAtTableModel.setSeatingChartName(name);
		
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
								handleTableClick(t.getName());							
							}					
						}));
					}
				}
				
			}			
		});
		
	}
	
	public void handleTableClick(String name) {
		
		getPeopleAtTableModel.setAccountName(Site.currentUser.getAccountName());
		getPeopleAtTableModel.setTableName(name);
		getPeopleAtTableModel.setType(ActionType.GETPEOPLEATTABLE);
		tableNameLabel.setText(name);
		
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
						peopleMenu.addItem(new MenuItem(p.getName(), false, new Command() {
							@Override
							public void execute() {
								handlePersonClick(p.getName());
							}							
						}));
					}
				}
				
			}
			
		});
		
	}
	
	public void handlePersonClick(String name) {
		personNameLabel.setText(name);		
	}
}
