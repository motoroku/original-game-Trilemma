package Trilemma;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table EXP__TABLE.
 */
public class EXP_TABLE {

    private Long id;
    private Integer level;
    private Integer necessary_exp;

    public EXP_TABLE() {
    }

    public EXP_TABLE(Long id) {
        this.id = id;
    }

    public EXP_TABLE(Long id, Integer level, Integer necessary_exp) {
        this.id = id;
        this.level = level;
        this.necessary_exp = necessary_exp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getNecessary_exp() {
        return necessary_exp;
    }

    public void setNecessary_exp(Integer necessary_exp) {
        this.necessary_exp = necessary_exp;
    }

}