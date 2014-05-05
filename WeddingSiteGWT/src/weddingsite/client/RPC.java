package weddingsite.client;

import com.google.gwt.core.shared.GWT;

public class RPC {
	public static final PerformLoginServiceAsync performLoginService = GWT.create(PerformLoginService.class);
	public static final PerformAttendanceListQueryServiceAsync performAttendanceListQueryService = GWT.create(PerformAttendanceListQueryService.class);
	public static final PerformAttendeeQueryServiceAsync performAttendeeQueryService = GWT.create(PerformAttendeeQueryService.class);
	public static final PerformEditAttendeeServiceAsync performEditAttendeeService = GWT.create(PerformEditAttendeeService.class);
	public static final PerformSeatingChartQueryServiceAsync performSeatingChartQueryService = GWT.create(PerformSeatingChartQueryService.class);
	public static final GetTablesServiceAsync getTablesService = GWT.create(GetTablesService.class);
	public static final GetPeopleAtTableAsync getPeopleAtTable = GWT.create(GetPeopleAtTable.class);
	public static final EditAttendanceListServiceAsync editAttendanceListService = GWT.create(EditAttendanceListService.class);
	public static final EditTablesServiceAsync editTablesService = GWT.create(EditTablesService.class);
	public static final EditSeatingChartServiceAsync editSeatingChartService = GWT.create(EditSeatingChartService.class);
	public static final EditPersonAtTableServiceAsync editPersonAtTableService = GWT.create(EditPersonAtTableService.class);
	public static final CreateAccountServiceAsync createAccountService = GWT.create(CreateAccountService.class);
	public static final PerformUserQueryServiceAsync performUserQueryService = GWT.create(PerformUserQueryService.class);
	public static final GetEventsServiceAsync getEventsService = GWT.create(GetEventsService.class);
}
