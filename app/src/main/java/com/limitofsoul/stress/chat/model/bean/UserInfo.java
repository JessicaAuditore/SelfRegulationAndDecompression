package com.limitofsoul.stress.chat.model.bean;

// 用户账号信息的bean类
public class UserInfo {

    private String name;// 用户名称
    private String hxid;// 环信id
    private String nick;// 用户的昵称
    private String photo;// 头像

    private String id;
    private String password;
    private String sex;
    private String age;
    private String question;
    private String answer;

    public UserInfo() {

    }

    public UserInfo(String name) {
        this.name = name;
        this.hxid = name;
        this.nick = name;
    }

    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserInfo(String name, String password, String sex, String age, String question, String answer) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.question = question;
        this.answer = answer;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHxid() {
        return hxid;
    }

    public void setHxid(String hxid) {
        this.hxid = hxid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", hxid='" + hxid + '\'' +
                ", nick='" + nick + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
