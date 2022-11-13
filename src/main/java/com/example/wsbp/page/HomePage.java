package com.example.wsbp.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

@WicketHomePage
@MountPath("Home")

public class HomePage extends WebPage {
    public HomePage(){
        var youModel= Model.of("Wicket-Spring-Boot");
        var youLabel = new Label("you",youModel);
        add(youLabel);
    }
}
