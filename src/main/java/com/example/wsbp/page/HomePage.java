package com.example.wsbp.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.core.request.handler.BookmarkableListenerRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import com.example.wsbp.service.ISampleService;
import org.apache.wicket.spring.injection.annot.SpringBean;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

@WicketHomePage
@MountPath("Home")

public class HomePage extends WebPage {
    public HomePage(){

        //HomePage.htmlのid=youにyouModelの情報を上書き・・・(1)
        var youModel= Model.of("Wicket-Spring-Boot");
        var youLabel = new Label("you",youModel);
        add(youLabel);

        //(1)と同様
        var gakuseki_model = Model.of("b2200170");
        var gakuseki = new Label("gakuseki",gakuseki_model);
        add(gakuseki);

        //(1)
        var name_model = Model.of("新木魁");
        var name = new Label("name",name_model);
        add(name);

        //(1)
        var timeModel = Model.of(service.makeCurrentHMS());
        var timeLabel = new Label("time", timeModel);
        add(timeLabel);

        //(1)
        var random_Model = Model.of(service.makeRandInt());
        var random_Label = new Label("random",random_Model);
        add(random_Label);

        //id=toUserMakerを押すとUserMakePageに画面遷移する
        var toUserMakerLink = new BookmarkablePageLink<>("toUserMaker", UserMakerPage.class);
        add(toUserMakerLink);

        //id=toUserDeleteを押すとUserDeletePageに画面遷移する
        var toUserDeleteLink = new BookmarkablePageLink<>("toUserDelete",UserDeletePage.class);
        add(toUserDeleteLink);
    }

    @SpringBean
    private ISampleService service;
}
