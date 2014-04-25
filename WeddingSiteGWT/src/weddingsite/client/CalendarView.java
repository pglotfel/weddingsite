package weddingsite.client;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.dom.client.Style.Unit;

public class CalendarView extends Composite {
	
	private Grid grid;
	private Button [] buttons;
	
	public CalendarView() {
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		LayoutPanel mainLayoutPanel = new LayoutPanel();
		initWidget(mainLayoutPanel);
		mainLayoutPanel.setSize("800px", "900px");
		
		buttons = new Button[42];
		
		for(int i = 0; i < 42; i++) {
			buttons[i] = new Button();		
		}
		
		grid = new Grid(7, 6);
		grid.setCellSpacing(10);
		grid.setCellPadding(10);
		mainLayoutPanel.add(grid);
		grid.setSize("400px", "400px");
		mainLayoutPanel.setWidgetLeftWidth(grid, 31.0, Unit.PX, 420.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(grid, 185.0, Unit.PX, 428.0, Unit.PX);
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				grid.setWidget(j, i, buttons[(6 * i) + j]);
			}
		}
		
		updateCalendar(2014, 3);
	}
	
	
	private void updateCalendar(int year, int month) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(year, month, 1);

//		int year       = calendar.get(Calendar.YEAR);
//		int month      = calendar.get(Calendar.MONTH); 
//		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // Jan = 0, not 1
//		int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
//		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
//		int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);
//
//		int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
//		int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
//		int minute     = calendar.get(Calendar.MINUTE);
//		int second     = calendar.get(Calendar.SECOND);
//		int millisecond= calendar.get(Calendar.MILLISECOND);
		
		calendar.getFirstDayOfWeek();
		
		calendar.getMaximum(Calendar.MONTH);
		
		for (Integer i = 0; i < 42; i++) {
			if(i > calendar.get(GregorianCalendar.DAY_OF_WEEK) && i < calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)) {
				buttons[i].setText(i.toString());
				buttons[i].setEnabled(true);
			} else {
				buttons[i].setText("");
				buttons[i].setEnabled(false);
			}
		}
		
		
	}
}
