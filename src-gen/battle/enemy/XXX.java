package battle.enemy;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table XXX.
 */
public class XXX {

    private Long id;
    /** Not-null value. */
    private String thumnail;
    /** Not-null value. */
    private String title;
    /** Not-null value. */
    private String link;
    private String archives;
    /** Not-null value. */
    private java.util.Date lastUpdatetime;

    public XXX() {
    }

    public XXX(Long id) {
        this.id = id;
    }

    public XXX(Long id, String thumnail, String title, String link, String archives, java.util.Date lastUpdatetime) {
        this.id = id;
        this.thumnail = thumnail;
        this.title = title;
        this.link = link;
        this.archives = archives;
        this.lastUpdatetime = lastUpdatetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getThumnail() {
        return thumnail;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Not-null value. */
    public String getLink() {
        return link;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLink(String link) {
        this.link = link;
    }

    public String getArchives() {
        return archives;
    }

    public void setArchives(String archives) {
        this.archives = archives;
    }

    /** Not-null value. */
    public java.util.Date getLastUpdatetime() {
        return lastUpdatetime;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLastUpdatetime(java.util.Date lastUpdatetime) {
        this.lastUpdatetime = lastUpdatetime;
    }

}
