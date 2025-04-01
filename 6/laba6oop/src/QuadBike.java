import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.Serializable;
import java.util.*;

public class QuadBike implements Vehicle{
    private String mark;
    private ArrayList<Model> models;

    public QuadBike(String mark, int size){
        this.mark = mark;
        this.models = new ArrayList<Model>();
        Random rnd = new Random();
        for(int i = 0; i < size; i++){
            models.add(new Model("Q" + (i+1), rnd.nextDouble(10000000)));
        }
    }

    private class Model implements Serializable, Cloneable{
        String name;
        double price;
        public Model() {
        }
        public Model(String newName, double newPrice){
            name = newName;
            price = newPrice;
        }
    }

    @Override
    public String getMark(){
        return mark;
    }

    @Override
    public void setMark(String newMark){
        mark = newMark;
    }

    @Override
    public void setNewNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        int i = 0;
        int savedOld = -1;
        while ( i < models.size()) {
            if (models.get(i).name.equals(oldName)){
                savedOld = i;

            }
            if(models.get(i).name.equals(newName)){
                throw new DuplicateModelNameException(newName);
            }
            i++;
        }
        if (savedOld != -1){

            models.get(savedOld).name = newName;
        }

        else{
            throw new NoSuchModelNameException(oldName);
        }
    }

    @Override
    public String[] getModelNameArr(){
        int size = models.size();
        String[] names = new String[size];
        for (int i = 0;i< size; i++){
            names[i] = models.get(i).name;
        }
        return names;
    }

    @Override
    public double getPrice(String name) throws NoSuchModelNameException {
        double price = 0;
        int i = 0;
        while( i< models.size() && !(models.get(i).name.equals(name))){
            i++;
        }
        if (i < models.size()){
            price = models.get(i).price;
        }
        else{
            throw new NoSuchModelNameException(name);
        }
        return price;
    }

    @Override
    public void setPrice(String name, double price) throws NoSuchModelNameException {
        if (price < 0){
            throw new ModelPriceOutOfBoundsException();
        }
        int i = 0;
        while( i< models.size() && !(models.get(i).name.equals(name))){
            i++;
        }
        if (i < models.size()){
            models.get(i).price = price;
        }
        else{
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public double[] getPricesArr(){
        int size = models.size();
        double[] prices = new double[size];
        for (int i = 0;i< size; i++){
            prices[i] = models.get(i).price;
        }
        return prices;
    }

    @Override
    public void addNewModel(String name, double price) throws DuplicateModelNameException{
        int j = 0;
        if (price < 0){
            throw new ModelPriceOutOfBoundsException();
        }
        while ( j< models.size() && !models.get(j).name.equals(name)){
            j++;
        }
        if (j < models.size()){
            throw new DuplicateModelNameException(name);
        }
        models.add(new Model(name, price));
    }

    @Override
    public void deleteCurrModelInArr(String name) throws NoSuchModelNameException {
        int size = models.size();
        int i = 0;
        while(i < size && !models.get(i).name.equals(name)){
            i++;
        }

        if (i<size) {
            models.remove(i);
        }
        else{
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public int getModelLength(){
        return models.size();
    }
}
