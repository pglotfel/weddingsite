package weddingsite.client;

import com.google.gwt.core.shared.GWT;

public class RPC {
	public static final PerformLoginServiceAsync performLoginService = GWT.create(PerformLoginService.class);
	public static final PerformAttendanceListQueryServiceAsync performAttendanceListQueryService = GWT.create(PerformAttendanceListQueryService.class);
	public static final PerformAttendeeQueryServiceAsync performAttendeeQueryService = GWT.create(PerformAttendeeQueryService.class);
}
