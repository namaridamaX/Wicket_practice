package com.example.wsbp.page.signed;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

// ↓ どの役割のユーザであれば表示してよいか。
// セッションのgetRoleメソッドが USER であれば表示し、それ以外は表示しない。
@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage{
    public SignedPage(){
        var name = Model.of("test");
        var userNameLabel = new Label("userName",name);
        add(userNameLabel);
    }
}
