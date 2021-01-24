@HtmlForm(method = "post", actoin = "/users")
public class User {
    @HtmlInput(name = "nickname", placeholder = "Ваш ник")
    private String nickname;
    @HtmlInput(name = "email",type = "email",  placeholder = "Ваш email")
    private String email;
    @HtmlInput(name = "password",type = "password", placeholder = "Ваш ник")
    private String password;
}
