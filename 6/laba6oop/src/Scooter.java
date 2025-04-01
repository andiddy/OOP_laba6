import java.util.Arrays;
import java.util.Random;
import java.io.Serializable;
import java.util.*;

public class Scooter implements Vehicle {
    private String mark;
    private HashMap<String, Double> models;

    public Scooter(String newMark, int size){
        mark = newMark;
        models = new HashMap<>();
        Random rnd = new Random();
        for(int i = 0; i < size; i++){
            models.put("S" + (i+1), rnd.nextDouble(10000000));
        }
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public void setNewNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (models.containsKey(oldName)){
            double price = models.get(oldName);
            models.put(newName, price);
        }
        else{
            throw new NoSuchModelNameException(oldName);
        }
    }

    @Override
    public double getPrice(String name) throws NoSuchModelNameException {
        if (models.containsKey(name)) {
            return models.get(name);
        } else {
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public void setPrice(String name, double price) throws NoSuchModelNameException {
        if (models.containsKey(name)) {
            models.put(name, price);
        } else {
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public String[] getModelNameArr() {
        return models.keySet().toArray(new String[0]);
    }

    @Override
    public double[] getPricesArr() {
        double[] prices = new double[models.size()];
        int i = 0;
        for (double price : models.values()) {
            prices[i++] = price;
        }
        return prices;
    }

    @Override
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0){
            throw new ModelPriceOutOfBoundsException();
        }
        if (models.containsKey(name)){
            throw new DuplicateModelNameException(name);
        }
        models.put(name, price);
    }

    @Override
    public void deleteCurrModelInArr(String name) throws NoSuchModelNameException {
        if (!models.containsKey(name)) {
            throw new NoSuchModelNameException(name);
        }
        models.remove(name);
    }

    @Override
    public int getModelLength() {
        return models.size();
    }

}
