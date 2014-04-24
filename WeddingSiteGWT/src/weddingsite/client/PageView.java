package weddingsite.client;

import weddingsite.client.Site.Pages;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuItemSeparator;

public class PageView extends Composite {
	
	private MenuBar backAndHomeMenu;
	private FlowPanel layoutPanel;
	
	public PageView() {
		
		layoutPanel = new FlowPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("116px", "32px");
		
		backAndHomeMenu = new MenuBar(false);
		layoutPanel.add(backAndHomeMenu);
		backAndHomeMenu.setSize("100%", "100%");
		
		MenuItem backMenuItem = new MenuItem("Back", false, new Command() {
			public void execute() {
				Site.search(Pages.BACK);
			}
		});
		backAndHomeMenu.addItem(backMenuItem);
		
		MenuItemSeparator separator = new MenuItemSeparator();
		backAndHomeMenu.addSeparator(separator);
		
		MenuItem homeMenuItem = new MenuItem("Home", false, new Command() {
			public void execute() {
				Site.search(Pages.HOMEPAGE);
			}
		});
		backAndHomeMenu.addItem(homeMenuItem);
	}
}
