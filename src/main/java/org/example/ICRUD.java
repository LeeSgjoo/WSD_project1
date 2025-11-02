package org.example;

public interface ICRUD {
    public Object add();
    public void updateItem();
    public void deleteItem();
    public void selectObject(int id);
    public void savefile();
    public void loadfile();
}