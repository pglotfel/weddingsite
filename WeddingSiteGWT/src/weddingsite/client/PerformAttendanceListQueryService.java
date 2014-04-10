package weddingsite.client;

import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("performAttendanceListQuery")
public interface PerformAttendanceListQueryService extends RemoteService {
	public AttendanceListQueryResult performAttendanceListQuery (AttendanceListQueryModel attendanceListQueryModel);

}
