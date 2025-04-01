import java.util.Arrays;
import java.util.Random;
import java.io.Serializable;

public class Cars implements Vehicle
{
    private String markCar;
    private int size;
    private Model[] models;

    public class Model implements Serializable{
        private String modelName;
        public String getModelName(){return modelName;}
        public void setModelName(String newName) {modelName=newName;}
        private double price;
        public double getModelPrice(){return price;}
        public void setModelPrice(double newPrice){price=newPrice;}
        public Model(String name, double price){
            this.modelName=name;
            this.price=price;
        }
    }

    public Cars (String newMark, int size){
        markCar=newMark;
        this.size=size;
        models=new Model[size];
        Random rnd = new Random();
        for(int i=0; i<size;i++){
            models[i]=new Model(("C" + (i+1)), rnd.nextDouble()*10000000);
        }
    }

    @Override
    public String getMark()
    {
        return markCar;
    }

    @Override
    public void setMark(String newMark)
    {
        markCar=newMark;
    }

    @Override
    public void setNewNameModel(String oldName, String newName) throws DuplicateModelNameException,NoSuchModelNameException
    {
        int sup=-1;
        for(int i=0;i<models.length;i++){
            if(models[i].modelName.equals(newName)){
                throw new DuplicateModelNameException(newName);
            }
            else if(models[i].modelName.equals(oldName)) {

                sup=i;

            }
        }
        if(sup==-1){
            throw new NoSuchModelNameException(oldName);
        }
        else
            models[sup].setModelName(newName);
    }

    @Override
    public String[] getModelNameArr()
    {
        String[] modelNameArr=new String[models.length];
        for(int i=0;i< models.length; i++){
            modelNameArr[i]=models[i].modelName;
        }
        return modelNameArr;
    }

    @Override
    public double getPrice(String nameModel) throws NoSuchModelNameException
    {
        double priceHere = 0;
        boolean sup=false;
        for(int i =0;i<models.length;i++){
            if(models[i].getModelName().equals(nameModel)){
                priceHere=models[i].getModelPrice();
                sup=true;
            }
        }
        if (!sup) {
            throw new NoSuchModelNameException(nameModel);
        }
        return priceHere;
    }

    @Override
    public void setPrice(String nameModel, double newPrice) throws NoSuchModelNameException
    {
        boolean sup=false;
        if(newPrice>0){
            for(int i =0;i<models.length;i++){
                if(models[i].getModelName().equals(nameModel)){
                    models[i].setModelPrice(newPrice);
                    sup=true;
                    break;
                }
            }
            if (!sup) {
                throw new NoSuchModelNameException(nameModel);
            }
        }
        else{
            throw new ModelPriceOutOfBoundsException();
        }
    }

    @Override
    public double[] getPricesArr()
    {
        double[] pricesModel = new double[models.length];
        for(int i=0;i< models.length;i++){
            pricesModel[i]=models[i].price;
        }
        return pricesModel;
    }

    @Override
    public void addNewModel(String newModel, double newPrice) throws DuplicateModelNameException
    {
        if(newPrice>0){
            for(int i=0;i<models.length;i++){
                if (models[i].modelName.equals(newModel)){
                    throw new DuplicateModelNameException(newModel);
                }
            }
            Model anotherModel = new Model(newModel,newPrice);
            models = Arrays.copyOf(models,models.length+1);
            models[models.length-1]=anotherModel;
            //    models=newModelArr;
        }
        else{
            throw new ModelPriceOutOfBoundsException();
        }
    }

    @Override
    public void deleteCurrModelInArr(String nameModel) throws NoSuchModelNameException
    {
        boolean sup=false;
        for(int i=0; i<models.length; i++){
            if(models[i].modelName.equals((nameModel))){
                System.arraycopy(models,i+1,models,i,models.length-i-1);
                models=Arrays.copyOf(models,models.length-1);
                sup=true;
                break;
            }
        }
        if(!sup){
            throw new NoSuchModelNameException(nameModel);
        }
    }

    @Override
    public int getModelLength()
    {
        return models.length;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Марка авто: " + markCar + "\n");
        for (int i = 0; i < getModelNameArr().length; i++)
        {
            stringBuffer.append("Название модели: " + getModelNameArr()[i] + " , ");
            stringBuffer.append("Цена модели: " + getPricesArr()[i] + "\n");
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        if (obj instanceof Cars)
        {
            Cars ts = (Cars) obj;
            if (!ts.getMark().equals(getMark()))
            {
                return false;
            }
            if (ts.getModelLength() != getModelLength())
            {
                return false;
            }
            for (int i = 0; i < getModelLength(); i++) {
                Model thisModel = models[i];
                Model otherModel = ts.models[i];
                if (!thisModel.getModelName().equals(otherModel.getModelName()) ||
                        thisModel.getModelPrice() != otherModel.getModelPrice()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * markCar.hashCode() * getModelLength() * Arrays.hashCode(getModelNameArr()) * Arrays.hashCode(getPricesArr());
    }

    @Override
    public Cars clone() throws CloneNotSupportedException {
        Cars newCar = (Cars)super.clone();
        newCar.models = new Model[models.length];
        for(int i = 0; i < models.length; i++) {
            newCar.models[i] = new Model(models[i].getModelName(), models[i].getModelPrice());
        }
        return newCar;
    }
}