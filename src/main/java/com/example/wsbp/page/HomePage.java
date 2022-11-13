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

        var gakuseki_model = Model.of("b2200170");
        var gakuseki = new Label("gakuseki",gakuseki_model);
        add(gakuseki);

        var name_model = Model.of("新木魁");
        var name = new Label("name",name_model);
        add(name);
    }
}
