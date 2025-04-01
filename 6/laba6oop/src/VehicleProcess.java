import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Scanner;

public class VehicleProcess
{
    static public double getArithmeticMeanOfPrices(Vehicle vehicle) {
        double res = 0;
        double[] arrayPrice = vehicle.getPricesArr();
        for(int i = 0; i < arrayPrice.length; i++)
        {
            res += arrayPrice[i];
        }
        return res/arrayPrice.length;
    }
    static public void modelOutput(Vehicle vehicle) {
        String[] arrayModel = vehicle.getModelNameArr();
        for(int i = 0; i < arrayModel.length; i++)
        {
            System.out.print(arrayModel[i] + " ");
        }
        System.out.println();
    }


    public static Vehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException, Exception {
        DataInputStream d = new DataInputStream(in);
        Vehicle a;
        switch(d.readUTF()) {
            case("Car"):
                a = new Cars("", 0);
                break;
            default:
                a = new Bike("", 0);
                break;
        }
        int j = d.read();
        a.setMark(new String(d.readNBytes(j)));
        int l = d.read();
        for (int i = 0; i<l; i++) {
            int h = d.read();
            a.addNewModel(new String(d.readNBytes(h)), d.readDouble());
        }
        return a;
    }
    public static void outputVehicle(Vehicle a, OutputStream out) throws IOException {
        DataOutputStream d = new DataOutputStream(out);
        int l = a.getModelLength();
        String[] names = a.getModelNameArr();
        double[] prices = a.getPricesArr();
        d.writeUTF(a.getClass().getSimpleName());
        d.write(a.getMark().length());
        d.write(a.getMark().getBytes());
        d.write(l);
        for (int i = 0; i < l; i++) {
            d.write(names[i].length());
            d.write(names[i].getBytes());
            d.writeDouble(prices[i]);
        }
    }
    /*
    public static void writeVehicle(Vehicle a, Writer out) throws IOException {
        PrintWriter h = new PrintWriter(out);
        int l = a.getModelLength();
        String[] names = a.getModelNameArr();
        double[] prices = a.getPricesArr();
        h.println(a.getClass().getSimpleName());
        h.println(a.getMark());
        h.println(l);
        for (int i = 0; i<l; i++) {
            h.println(names[i]);
            h.println(prices[i]);
        }
        h.flush();
    }
    public static Vehicle readVehicle(Reader in) throws IOException, DuplicateModelNameException {
        BufferedReader d = new BufferedReader(in);
        Vehicle a;
        switch(d.readLine()) {
            case("Car"):
                a = new Cars("", 0);
                break;
            default:
                a = new Bike("", 0);
                break;
        }
        a.setMark(d.readLine());
        int size = Integer.parseInt(d.readLine());
        for (int i = 0; i<size; i++) {
            a.addNewModel(d.readLine(), Double.parseDouble(d.readLine()));
        }
        return a;
    }
     */

    public static void writeVehicle(Vehicle vehicle) throws IOException {
        System.out.printf("Транспорт: %s,%n Марка: %s,%n Количество моделей %d,%n", vehicle.getClass().getName(), vehicle.getMark(), vehicle.getModelLength());

        for (int i = 0; i < vehicle.getModelLength(); i++) {
            System.out.printf("Название модели: %s, Цена: %.2f%n", vehicle.getModelNameArr()[i], vehicle.getPricesArr()[i]);
        }
    }
    static public void printPrices(Vehicle vehicle) {
        double[] prices = vehicle.getPricesArr();
        String[] names = vehicle.getModelNameArr();
        for (int i = 0; i < vehicle.getModelLength(); i++) {
            System.out.println("Модель " + names[i] + ": " + prices[i] + "$");
        }
    }
    public static Vehicle createVehicle(String mark, int size, Vehicle vehicle) {
        Class cls = null;
        try{
            cls = vehicle.getClass();
            Constructor con = cls.getConstructor(new Class[] {String.class, int.class});
            Vehicle newVehicle = (Vehicle) con.newInstance(mark, size);
            return newVehicle;
        }
        catch (Exception ex) {
            return null;
        }
    }
    public static Vehicle readVehicle() throws IOException, DuplicateModelNameException {
        Vehicle vehicle = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Транспорт: ");
        String transport = scanner.nextLine();
        System.out.println("Марка: ");
        String mark = scanner.nextLine();
        System.out.println("Количество моделей: ");
        int size = scanner.nextInt();
        switch(transport){
            case "Cars":
                vehicle = new Cars(mark, 0);
                break;
            case "Bike":
                vehicle = new Bike(mark, 0);
                break;
            case "Scooter":
                vehicle = new Scooter(mark, 0);
                break;
            case "Moped":
                vehicle = new Moped(mark, 0);
                break;
            case "QuadBike":
                vehicle = new QuadBike(mark, 0);
                break;
        }
        for (int i = 0; i < size; i++) {
            System.out.println("Название Модели: ");
            String name = scanner.next();
            System.out.println("Цена: ");
            double price = scanner.nextDouble();
            vehicle.addNewModel(name, price);
        }
        return vehicle;
    }
    public static double averagePriceVehicles(Vehicle...vehicles){
        double average = 0;
        double sum = 0;
        for (int i = 0; i < vehicles.length; i++) {
            double[] prices = vehicles[i].getPricesArr();
            for (int j = 0; j < prices.length; j++) {
                sum += prices[j];
            }
        }
        average = sum / vehicles.length;
        return average;
    }
}
