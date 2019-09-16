import java.util.ArrayList;
public class Store{
    private int myId;
    private int myInv;

    public Store(int id, int inv){
        myId = id;
        myInv = inv;
    }

    public int getId(){}

    public int getInv(){}

    public int compareTo(Store other){ }

    public boolean equals(Store other){ }

    public String toString(){}
}

class Item{
    private ArrayList <Item> myStore;

    public Item(String fName){ }

    public Item(){}
    private void loadFile(String inFileName){ }
    public void displayStore(){}
    public void Sort(){} //to get recursive sort going
    private void merge(ArrayList <Item> a, int first, int mid, int last){}
    public void mergeSort(ArrayList <Item> a, int first, int last){ }
    private void swap(ArrayList <Item> list, int a, int b){}
}