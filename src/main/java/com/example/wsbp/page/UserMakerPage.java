package com.example.wsbp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import org.apache.wicket.model.Model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import com.example.wsbp.service.IUserService;

@MountPath("UserMaker")
public class UserMakerPage extends WebPage {

    //IUserService を IoC/DI する
    @SpringBean
    private IUserService userService;

    public UserMakerPage() {

        var userNameModel = Model.of("");
        var userPassModel = Model.of("");

        //id=toHomeを押すとHomePageに画面遷移する
        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

        //userInfoというひとまとまりのフォームを作るイメージ？その中での動作を書く
        Form<Void> userInfoForm = new Form<Void>("userInfo") {
            @Override
            //type=submitを押したときの動作
            protected void onSubmit() {
                var userName = userNameModel.getObject();  //userNameModelには名前入力欄に入れた内容が入っている
                var userPass = userPassModel.getObject();  //userPassModelにはパスワード入力欄に入れた内容が入っている
//                var msg = "送信データ："
//                        + userName
//                        + ","
//                        + userPass;
//                System.out.println(msg);
                // IoC/DI した userService のメソッドを呼び出す
                userService.registerUser(userName, userPass); //->IUserServiceクラスのregisterUserメソッド呼び出し
                setResponsePage(new UserMakerCompPage(userNameModel)); //遷移先の画面指定
            }
        };
        add(userInfoForm);

        //テキストフィールドの作成
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
        userInfoForm.add(userNameField);

        //パスワードテキストフィールドの作成
        var userPassField = new PasswordTextField("userPass", userPassModel){

          @Override
          protected void onInitialize(){
              super.onInitialize();
              var validator = StringValidator.lengthBetween(8,32);
              add(validator);
          }
        };
        userInfoForm.add(userPassField);

    }
}
