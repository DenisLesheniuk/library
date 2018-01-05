package ml.ledv.library.rest.params;

public class UserParams {

    private String id;

    private String login;

    public UserParams() {
    }

    public UserParams(final String id, final String login) {
        this.id = id;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }
}
