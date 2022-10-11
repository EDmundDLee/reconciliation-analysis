package com.rongxin.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rongxin.cms.domain.BizColumnTree;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CommonTree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String label;
    private Long parentId;
    private String type;
    @JsonInclude(Include.NON_EMPTY)
    private List<CommonTree> children;

    public CommonTree() {
    }

    public CommonTree(BizColumnTree col) {
        this.id = col.getId();
        this.label = col.getName();
        this.parentId = col.getParentId();
        this.children = (List)col.getChildren().stream().map(CommonTree::new).collect(Collectors.toList());
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<CommonTree> getChildren() {
        return this.children;
    }

    public void setChildren(List<CommonTree> children) {
        this.children = children;
    }
}
