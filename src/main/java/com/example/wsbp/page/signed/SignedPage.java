package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

// ↓ どの役割のユーザであれば表示してよいか。
// セッションのgetRoleメソッドが USER であれば表示し、それ以外は表示しない。
@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage{
    public SignedPage(){
        var name = Model.of(MySession.get().getUserName());
        var userNameLabel = new Label("userName",name);
        add(userNameLabel);

        Link<Void> signoutLink = new Link<Void>("signout"){
            @Override
            public void onClick(){
                //セッションの破棄
                MySession.get().invalidate();
                //SignPageへ移動
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);
    }
}
