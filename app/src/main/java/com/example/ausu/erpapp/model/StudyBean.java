package com.example.ausu.erpapp.model;

import java.util.List;

/**
 * Created by Lanxumit on 2016/7/28.
 */
public class StudyBean {
    private String typeName;
    private List<ItemBean> items;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ItemBean> getItems() {
        return items;
    }

    public void setItems(List<ItemBean> items) {
        this.items = items;
    }

    public class ItemBean {
        private String itemName;
        private String itemId;
        private boolean isComplete;

        public boolean isComplete() {
            return isComplete;
        }

        public void setIsComplete(boolean isComplete) {
            this.isComplete = isComplete;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }
    }
}
