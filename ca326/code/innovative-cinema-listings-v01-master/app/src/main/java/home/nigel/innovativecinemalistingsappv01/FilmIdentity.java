package home.nigel.innovativecinemalistingsappv01;

public class FilmIdentity {
    private String name;        // ListView film class
    private String top_info;
    private String bottom_info;

    public String getTop_info() {
        return top_info;
    }       //constructors and getter, setter methods



    public String getBottom_info() {
        return bottom_info;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public FilmIdentity(String name, String top, String bottom) //Main constructor( all args )
    {
        this.name=name;
        this.top_info=top;
        this.bottom_info=bottom;

    }

}
