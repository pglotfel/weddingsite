package weddingsite.client;

import com.google.gwt.core.shared.GWT;

public class RPC {
	public static final PerformLoginServiceAsync performLoginService = GWT.create(PerformLoginService.class);
	public static final PerformAttendanceListQueryServiceAsync performAttendanceListQueryService = GWT.create(PerformAttendanceListQueryService.class);
	public static final PerformAttendeeQueryServiceAsync performAttendeeQueryService = GWT.create(PerformAttendeeQueryService.class);
	public static final PerformEditAttendeeServiceAsync performEditAttendeeService = GWT.create(PerformEditAttendeeService.class);
	public static final PerformSeatingChartQueryServiceAsync performSeatingChartQueryService = GWT.create(PerformSeatingChartQueryService.class);
}
