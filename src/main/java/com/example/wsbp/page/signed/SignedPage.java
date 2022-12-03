package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.chat.UserChatPage;
import com.example.wsbp.data.AuthUser;
import com.example.wsbp.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

// ↓ どの役割のユーザであれば表示してよいか。
// セッションのgetRoleメソッドが USER であれば表示し、それ以外は表示しない。
@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage{

    @SpringBean
    private IUserService userService;


    public SignedPage(){
        var name = Model.of(MySession.get().getUserName());
        var userNameLabel = new Label("userName",name);
        add(userNameLabel);

        var toChatPageLink = new BookmarkablePageLink<>("toChatPage", UserChatPage.class);
        add(toChatPageLink);

        Link<Void> signoutLink = new Link<Void>("sign-out"){
            @Override
            public void onClick(){
                //セッションの破棄
                MySession.get().invalidate();
                //SignPageへ移動
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);

        //Service からデータベースのユーザ一覧をもらい、Modelにする
        //List型のモデルはModel.ofList(...)で作成する
        var authUsersModel = Model.ofList(userService.findAuthUser());

        //List型のモデルを表示するListView
        var usersLV = new ListView<>("users", authUsersModel){
            @Override
            protected void populateItem(ListItem<AuthUser> listItem){
                //List型のモデルから、<li>...</li> 一つ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                var authUser = itemModel.getObject(); // 元々のListのn番目の要素

                //インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとに取り出して表示する
                //addする先がlistItemになることに注意
                var userNameModel = Model.of(authUser.getUserName());
                var userNameLabel = new Label("userName",userNameModel);
                listItem.add(userNameLabel);

                var userPassModel = Model.of(authUser.getUserPass());
                var userPassLabel = new Label("userPass",userPassModel);
                listItem.add(userPassLabel);
            }
        };
        add(usersLV);
    }
}
