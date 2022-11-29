package com.example.wsbp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("UserMaker")
public class UserMakePage extends WebPage {
    public UserMakePage(){

        var toHomeLink = new BookmarkablePageLink<>("toHome",HomePage.class);
        add(toHomeLink);
    }
}
