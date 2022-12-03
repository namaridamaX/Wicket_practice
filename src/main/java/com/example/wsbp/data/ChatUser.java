package com.example.wsbp.data;

import java.io.Serializable;

// CHAT テーブルのデータを入れるクラス
public class ChatUser implements Serializable{

        private final String userName;
        private final String userChat;

        public ChatUser(String userName,String userChat){
            this.userName = userName;
            this.userChat = userChat;
        }

        public String getUserName(){
            return userName;
        }

        public String getUserMessage(){
            return userChat;
        }

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;

            com.example.wsbp.data.ChatUser chatUser = (com.example.wsbp.data.ChatUser) o;

            if(!userName.equals(chatUser.userName)) return false;
            return userChat.equals(chatUser.userChat);
        }

        @Override
        public int hashCode(){
            int result = userName.hashCode();
            result = 31 * result + userChat.hashCode();
            return result;
        }
}
