package Trilemma;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DUNGEON.
 */
public class DUNGEON {

    private Long id;
    private String dungeon_name;

    public DUNGEON() {
    }

    public DUNGEON(Long id) {
        this.id = id;
    }

    public DUNGEON(Long id, String dungeon_name) {
        this.id = id;
        this.dungeon_name = dungeon_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDungeon_name() {
        return dungeon_name;
    }

    public void setDungeon_name(String dungeon_name) {
        this.dungeon_name = dungeon_name;
    }

}