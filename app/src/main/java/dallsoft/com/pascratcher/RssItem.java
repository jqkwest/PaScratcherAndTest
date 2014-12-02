package dallsoft.com.pascratcher;

/**
 * Created by jkwest on 11/20/2014.
 */
public class RssItem {

    private String link;
    private String gameNumber;
    private String description = "";
    private String title;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(String gameNumber) {
        this.gameNumber = gameNumber;
    }

    public String getDescription() {
        return description.trim();
    }

    public void setDescription(String description) {
        this.description = this.description + description + " ";
      //  this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
