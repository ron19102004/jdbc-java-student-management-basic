package models;

public class Grade {
    private long id;
    private String name;

    public Grade() {
    }

    public Grade(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String toStringHeader(){
        return String.format(
                "+----+----------------------+\n" +
                "| ID | Name                 |\n" +
                "+----+----------------------+\n"
        );
    }
    @Override
    public String toString() {
        return String.format(
                "| %-2d | %-20s |\n" +
                "+----+----------------------+\n",
                id, name
        );
    }
}
