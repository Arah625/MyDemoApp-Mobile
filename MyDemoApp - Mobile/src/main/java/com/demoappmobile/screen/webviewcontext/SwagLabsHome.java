package com.demoappmobile.screen.webviewcontext;

import com.demoappmobile.screen.BasicScreen;
import com.demoappmobile.screen.webviewcontext.component.SwagLabsSideNavigationBar;
import com.demoappmobile.screen.webviewcontext.component.SwagLabsTopNavigationBar;
import org.openqa.selenium.WebDriver;

public class SwagLabsHome extends BasicScreen {

    private SwagLabsTopNavigationBar swagLabsTopNavigationBar;
    private SwagLabsSideNavigationBar swagLabsSideNavigationBar;

    public SwagLabsHome(WebDriver driver) {
        super(driver);
        this.swagLabsTopNavigationBar = new SwagLabsTopNavigationBar(driver);
        this.swagLabsSideNavigationBar = new SwagLabsSideNavigationBar(driver);
    }

    public boolean isScreenHeaderVisible() {
        return swagLabsTopNavigationBar.isScreenHeaderVisible();
    }
}
