package com.pcl.myapplication.bean;

import java.util.List;

/**
 * 模拟数据，本地 depts.json
 */
public class Depts {

    /**
     * 下级行
     */
    private List<Children> children;

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public static class Children {

        /**
         * 当前行 id
         */
        private Integer id;

        /**
         * 当前行 名称
         */
        private String name;

        /**
         * 下级行
         */
        private List<Children> children;

        /**
         * 第几级行
         */
        private int depth;

        /**
         * 是否勾选 默认false
         */
        private boolean isChecked;

        /**
         * 是否展开 默认false
         */
        private boolean isExpand;

        /**
         * 父级id
         */
        private Integer parentId;

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public boolean isCanCheck() {
            return depth != 2;
        }

        public boolean isCanExpand() {
            return children != null;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }

        public List<Children> getChildren() {
            return children;
        }

        public void setChildren(List<Children> children) {
            this.children = children;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
