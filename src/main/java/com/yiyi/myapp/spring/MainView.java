package com.yiyi.myapp.spring;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {
        LoginI18n i18 = LoginI18n.createDefault();
        i18.setAdditionalInformation("To close the login form submit non-empty username and password");

        LoginOverlay loginOverlay = new LoginOverlay(i18);
        loginOverlay.setOpened(true);
        loginOverlay.setTitle("登录");
        loginOverlay.addLoginListener((ComponentEventListener<AbstractLogin.LoginEvent>) loginEvent -> {
            System.out.println(loginEvent.getUsername());
            System.out.println(loginEvent.getPassword());
            loginOverlay.close();
            navigateToMainPage();
        });
        add(loginOverlay);
    }

    private void navigateToMainPage() {
        AppLayout appLayout = new AppLayout();
        Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
        img.setHeight("44px");
        final boolean touchOptimized = true;
        appLayout.addToNavbar(touchOptimized, new DrawerToggle(), img);
        Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        appLayout.addToDrawer(tabs);
        add(appLayout);
    }


}
