package com.example.wsbp.chat;

import com.example.wsbp.data.ChatUser;
import com.example.wsbp.page.HomePage;
import com.example.wsbp.repository.IAuthUserRepository;
import com.example.wsbp.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;


@AuthorizeInstantiation(Roles.USER)
@MountPath("UserChat")
public class UserChatPage extends WebPage {

    @SpringBean
    private IUserService userService;

    public UserChatPage(){

        var userNameModel = Model.of("");
        var userMessageModel = Model.of("");

        var toHomelink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomelink);

        Form<Void> messageInform = new Form<>("messageInfo"){
            @Override
            protected void onSubmit(){ //投稿ボタンを押したときの動作
                var userName = userNameModel.getObject();
                var userMessage = userMessageModel.getObject();
                var msg = "送信データ："
                        + userName
                        +":"
                        +userMessage;
                System.out.println(msg);
                //名前とチャット内容を登録するメソッド作成
                userService.registerMessage(userName,userMessage);
                //登録後に遷移するUserChatCompPage作成
                setResponsePage(new UserChatPage());

            }
        };
        add(messageInform);

        var userNameField = new TextField<>("userName", userNameModel){
            // onInitialize() は全てのコンポーネントに備わっている、初期化時の処理。
            // オーバーライドするときは super.onInitialize() を忘れずに残しておく。
            @Override
            protected void onInitialize(){
                super.onInitialize();
                // 文字列の長さを8〜32文字に制限するバリデータ
                var validator = StringValidator.lengthBetween(8,32);
                add(validator);
            }
        };
        messageInform.add(userNameField);

        var userMessageField = new TextField<>("userMessage", userMessageModel){
            // onInitialize() は全てのコンポーネントに備わっている、初期化時の処理。
            // オーバーライドするときは super.onInitialize() を忘れずに残しておく。
            @Override
            protected void onInitialize(){
                super.onInitialize();
                // 文字列の長さを8〜32文字に制限するバリデータ
                var validator = StringValidator.lengthBetween(8,32);
                add(validator);
            }
        };
        messageInform.add(userMessageField);

        var chatModel = Model.ofList(userService.findChat());

        var usersLV = new ListView<>("m",chatModel){

            @Override
            protected void populateItem(ListItem<ChatUser> listItem){

                var itemModel = listItem.getModel();
                var chatUser = itemModel.getObject();

                var userNameModel = Model.of(chatUser.getUserName());
                var userNameLabel = new Label("usercompName",userNameModel);
                listItem.add(userNameLabel);

                var userMessageModel = Model.of(chatUser.getUserMessage());
                var userMessageLabel = new Label("usercompMessage",userMessageModel);
                listItem.add(userMessageLabel);
            }
        };
        add(usersLV);
    }
}
