package Trilemma;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table ARMOR.
 */
public class ARMOR {

    private Long id;
    private String armor_name;
    private Integer defense_point;

    public ARMOR() {
    }

    public ARMOR(Long id) {
        this.id = id;
    }

    public ARMOR(Long id, String armor_name, Integer defense_point) {
        this.id = id;
        this.armor_name = armor_name;
        this.defense_point = defense_point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArmor_name() {
        return armor_name;
    }

    public void setArmor_name(String armor_name) {
        this.armor_name = armor_name;
    }

    public Integer getDefense_point() {
        return defense_point;
    }

    public void setDefense_point(Integer defense_point) {
        this.defense_point = defense_point;
    }

}
