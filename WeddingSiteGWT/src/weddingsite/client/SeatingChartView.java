package weddingsite.client;

import weddingsite.shared.ActionType;
import weddingsite.shared.SeatingChart;
import weddingsite.shared.SeatingChartQueryModel;
import weddingsite.shared.SeatingChartQueryResult;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.FlowPanel;

public class SeatingChartView extends Composite {
	
	private MenuBar seatingChartMenu;
	private MenuBar tableMenu;
	private MenuBar peopleMenu;
	private FlowPanel seatingChartFlowPanel;
	private FlowPanel tableFlowPanel;
	private FlowPanel peopleFlowPanel;
	private SeatingChartQueryModel seatingChartQueryModel;
	
	public SeatingChartView() {
				
		seatingChartQueryModel = new SeatingChartQueryModel();
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
				
				for(SeatingChart s : result.getSeatingCharts()) {
					seatingChartMenu.addItem(new MenuItem(s.getName(), false, new Command() {
						@Override
						public void execute() {
							//RPC call to add tables
						}					
					}));
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
	
}
