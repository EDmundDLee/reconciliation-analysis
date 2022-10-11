package com.rongxin.cms.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ColSelect implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String label;
    @JsonInclude(Include.NON_EMPTY)
    private List<com.rongxin.cms.domain.ColSelect> children;

    public ColSelect() {
    }

    public ColSelect(BizColumn col) {
        this.id = col.getId();
        this.label = col.getName();
        this.children = (List)col.getChildren().stream().map(com.rongxin.cms.domain.ColSelect::new).collect(Collectors.toList());
    }

    public ColSelect(Object o) {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<com.rongxin.cms.domain.ColSelect> getChildren() {
        return this.children;
    }

    public void setChildren(List<com.rongxin.cms.domain.ColSelect> children) {
        this.children = children;
    }
}
