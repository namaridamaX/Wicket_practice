package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.page.signed.SignedPage;
import com.example.wsbp.service.IUserService;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.w3c.dom.Text;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Objects;

//ユーザ認証を行うページであることを表す。
//まだ認証が終わっていないブラウザが、認証が必要なページにアクセスすると、このページに転送されてくる
@WicketSignInPage
@MountPath("Sign")
public class SignPage extends WebPage{

    @SpringBean
    private IUserService service;

    public SignPage(){

        var userNameModel = Model.of("");
        var userPassModel = Model.of("");

        var userInfoForm = new Form<>("userInfo"){
            @Override
            protected void onSubmit(){
                var userName = userNameModel.getObject();
                var userPass = userPassModel.getObject();
                if(service.existsUser(userName,userPass)){
                    MySession.get().sign(userName);  //セッションに、ユーザが成功したユーザ名を記録する
                }
                setResponsePage(new SignedPage());
            }
        };
        add(userInfoForm);

        var userNameField = new TextField<>("userName",userNameModel){
            @Override
            protected void onInitialize(){
                super.onInitialize();
                add(StringValidator.lengthBetween(8,32));
            }
        };
        userInfoForm.add(userNameField);

        var userPassField = new PasswordTextField("userPass",userPassModel){
            @Override
            protected void onInitialize(){
                super.onInitialize();
                add(StringValidator.lengthBetween(8,32));
            }
        };
        userInfoForm.add(userPassField);
    }
}
