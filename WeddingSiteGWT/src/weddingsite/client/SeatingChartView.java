package weddingsite.client;

import java.util.ArrayList;

import weddingsite.shared.ActionType;
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
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

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
	
	public SeatingChartView() {
				
		getTablesModel = new GetTablesModel();
		seatingChartQueryModel = new SeatingChartQueryModel();
		getPeopleAtTableModel = new GetPeopleAtTableModel();
		
		LayoutPanel layoutPanel = new LayoutPanel();
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
		layoutPanel.setWidgetTopHeight(seatingChartFlowPanel, 20, Unit.PCT, 60, Unit.PCT);
		
		seatingChartMenu = new MenuBar(true);
		seatingChartFlowPanel.add(seatingChartMenu);
		seatingChartMenu.setSize("100%", "100%");
		seatingChartMenu.setStyleName("gwt-MenuBar-vertical");
		
		
		//Seating chart RPC call
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
				
					for(final SeatingChart s : result.getSeatingCharts()) {					
						
						seatingChartMenu.addItem(new MenuItem(s.getName(), false, new Command() {
							@Override
							public void execute() {
								//RPC call to add tables							
								handleSeatingChartClick(s);
							}					
						}));
					}
					
				}
			}
			
		});
		
		
		tableFlowPanel = new FlowPanel();
		layoutPanel.add(tableFlowPanel);
		layoutPanel.setWidgetLeftWidth(tableFlowPanel, 40, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(tableFlowPanel, 20, Unit.PCT, 60, Unit.PCT);
		
		tableMenu = new MenuBar(true);
		tableFlowPanel.add(tableMenu);
		tableMenu.setSize("100%", "100%");
		tableMenu.setStyleName("gwt-MenuBar-vertical");
		
		peopleFlowPanel = new FlowPanel();
		layoutPanel.add(peopleFlowPanel);
		layoutPanel.setWidgetLeftWidth(peopleFlowPanel, 70, Unit.PCT, 20, Unit.PCT);
		layoutPanel.setWidgetTopHeight(peopleFlowPanel, 20, Unit.PCT, 60, Unit.PCT);
		
		peopleMenu = new MenuBar(true);
		peopleFlowPanel.add(peopleMenu);
		peopleMenu.setSize("100%", "100%");
		peopleMenu.setStyleName("gwt-MenuBar-vertical");
	}
	
	public void handleSeatingChartClick(final SeatingChart seatingChart) {
		getTablesModel.setAccountName(Site.currentUser.getAccountName());
		getTablesModel.setSeatingChartName(seatingChart.getName());
		getTablesModel.setType(ActionType.GETTABLES);
		
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
								handleTableClick(seatingChart.getName(), t);
							}					
						}));
					}
				}
				
			}			
		});
		
	}
	
	public void handleTableClick(String seatingChartName, Table t) {
		
		getPeopleAtTableModel.setAccountName(Site.currentUser.getAccountName());
		getPeopleAtTableModel.setSeatingChartName(seatingChartName);
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
