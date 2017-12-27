package ml.ledv.library.rest.params;

public class BookParams {

    private String name;

    public BookParams(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
