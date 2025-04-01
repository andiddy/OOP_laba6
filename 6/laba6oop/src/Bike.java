import java.util.Arrays;
import java.util.Random;
import java.util.Date;
import java.io.Serializable;

public class Bike implements Vehicle
{
    private class Model implements Serializable{
        private String modelName = null;
        public String getModelName(){return modelName;}
        public void setModelName(String newName) {modelName=newName;}
        private double price = Double.NaN;
        public double getModelPrice(){return price;}
        public void setModelPrice(double newPrice){price=newPrice;}
        Model prev = null;
        Model next = null;
        public Model(){}
        public Model(String modelName, double price){
            this.modelName=modelName;
            this.price=price;
        }
    }
    private int size = 0;
    private Model head;
    private Date lastModified = new Date();

    private String markBike;

    @Override
    public String getMark()
    {
        return markBike;
    }

    @Override
    public void setMark(String newMark)
    {
        markBike=newMark;
    }

    public Bike(String newMark, int size)
    {
        markBike=newMark;
        this.size=size;
        head= new Model();
        head.next=head;
        head.prev=head;
        Random rnd = new Random();
        Model add;
        if(size!=0){
            for(int i=0;i<size;i++){
                add = new Model(("N" + (i+1)), rnd.nextDouble()*100_000_000);
                add.next=head;
                add.prev=head.prev;
                head.prev.next=add;
                head.prev=add;
            }
        }
    }

    @Override
    public void setNewNameModel(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException
    {
        Model p = head.next;
        Model s =null;
        while (p!=head){
            if(p.getModelName().equals(newName)){
                throw new DuplicateModelNameException(newName);
            }
            else if(p.getModelName().equals(oldName)||oldName.equals(newName)){
                s=p;
            }
            p=p.next;
        }
        if(s==null){
            throw new NoSuchModelNameException(oldName);
        }
        s.setModelName(newName);
        lastModified=new Date();
    }

    @Override
    public String[] getModelNameArr()
    {
        String[] modelNameArr=new String[size];
        Model p = head.next;
        int i=0;
        while(p!=head && i<size){
            modelNameArr[i]=p.getModelName();
            i++;
            p=p.next;
        }
        return modelNameArr;
    }

    @Override
    public double getPrice(String nameModel) throws NoSuchModelNameException
    {
        double priceHere = 0;
        Model p=head.next;
        boolean sup=false;
        while(p!=head){
            if(p.getModelName().equals(nameModel)){
                priceHere=p.getModelPrice();
                sup=true;
                break;
            }
            p=p.next;
        }
        if(!sup){
            throw new NoSuchModelNameException(nameModel);
        }
        return priceHere;
    }

    @Override
    public void setPrice(String nameModel, double newPrice) throws NoSuchModelNameException
    {
        boolean sup=false;
        if(newPrice>0){
            Model p = head.next;
            while (p!=head){
                if(p.getModelName().equals(nameModel)){
                    p.setModelPrice(newPrice);
                    lastModified=new Date();
                    sup=true;
                    break;
                }
                p=p.next;
            }
            if(!sup){
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
        double[] pricesModel = new double[size];
        Model p = head.next;
        int i=0;
        while(p!=head && i<size){
            pricesModel[i]=p.getModelPrice();
            i++;
            p=p.next;
        }
        return pricesModel;
    }

    @Override
    public void addNewModel(String newModel, double newPrice) throws DuplicateModelNameException
    {
        if (newPrice > 0) {
            Model s=head.next;
            while (s!=head){
                if(s.modelName.equals(newModel)){
                    throw new DuplicateModelNameException(newModel);
                }
                s=s.next;
            }
            Model p = new Model(newModel,newPrice);
            p.next=head;
            p.prev=head.prev;
            head.prev.next=p;
            head.prev=p;
            size++;
            lastModified=new Date();
        }
        else{
            throw new ModelPriceOutOfBoundsException();
        }
    }

    @Override
    public void deleteCurrModelInArr(String nameModel) throws NoSuchModelNameException
    {
        Model p;
        boolean sup = false;
        if(head!=null){
            p=head.next;
            while (p!=head){
                if(p.modelName.equals(nameModel)){
                    p.prev.next=p.next;
                    p.next.prev=p.prev;
                    lastModified=new Date();
                    sup=true;
                    size--;
                }
                p=p.next;
            }
            if(!sup){
                throw new NoSuchModelNameException(nameModel);
            }
        }
    }

    @Override
    public int getModelLength()
    {
        return size;
    }

    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Марка мотоцикла: " + markBike + "\n");
        for (int i = 0; i < getModelNameArr().length; i++)
        {
            stringBuffer.append("Название модели: " + getModelNameArr()[i] + " , ");
            stringBuffer.append("Цена модели: " + getPricesArr()[i] + "\n");
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        if (obj instanceof Bike)
        {
            Bike ts = (Bike) obj;
            if (!ts.getMark().equals(getMark()))
            {
                return false;
            }
            if (ts.getModelLength() != getModelLength())
            {
                return false;
            }
            Model p = head.next;
            Model q = ts.head.next;
            while (p != head && q != ts.head)
            {
                if(!q.getModelName().equals(p.getModelName()) || q.getModelPrice() != p.getModelPrice())
                {
                    return false;
                }
                p = p.next;
                q = q.next;
            }
            if (p == head && q == ts.head)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * markBike.hashCode() * size * Arrays.hashCode(getModelNameArr()) * Arrays.hashCode(getPricesArr());
    }

    @Override
    public Bike clone() throws CloneNotSupportedException {
        Bike newBike = (Bike)super.clone();
        if (head != null) {
            newBike.head = new Model("", Double.NaN);
            newBike.head.next = newBike.head;
            newBike.head.prev = newBike.head;
            for (Model current = head.next; current != head; current = current.next) {
                Model newM = new Model(current.getModelName(), current.getModelPrice());
                newM.next = newBike.head;
                newM.prev = newBike.head.prev;
                newBike.head.prev.next = newM;
                newBike.head.prev = newM;
            }
        }
        else {
            newBike.head = null;
            return newBike;
        }
        return newBike;
    }
}