package com.test.demosendfiles;

public class FileItem {

    private boolean isSelected;
    private String fileName;

    public FileItem(boolean isSelected, String fileName) {
        this.isSelected = isSelected;
        this.fileName = fileName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
