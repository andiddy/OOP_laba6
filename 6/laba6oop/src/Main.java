import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException, DuplicateModelNameException, NoSuchModelNameException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        System.out.println("_______________");
        System.out.println("1 задание");
        Cars car =  new Cars("BMV", 3);
        car.addNewModel("test", 1234);
        if (args.length == 4) {
            try {
                Class c = Class.forName(args[0]);
                Method m = c.getMethod(args[1], new Class[]{String.class, double.class});
                String mark = args[2];
                double price = Double.valueOf(args[3]);
                m.invoke(car, new Object[] {mark, price});
                System.out.println(car);

            } catch (ClassNotFoundException e) {
                System.out.println("Класс не найден");
            } catch (NoSuchMethodException e) {
                System.out.println("Метод не найден");
            } catch (IllegalAccessException e) {
                System.out.println("Метод недоступен");
            } catch (InvocationTargetException e) {
                System.out.println("При вызове возникло исключение");
            }
        }

        System.out.println("_______________");
        System.out.println("2 задание");
        Vehicle vehicle1 =VehicleProcess.createVehicle("CAR",3,car);
        System.out.println(car.getClass());
        System.out.println(vehicle1.getClass());
        System.out.println(vehicle1);

        System.out.println("_______________");
        System.out.println("3 задание");
        Vehicle scooter = new Scooter("mark", 5);
        scooter.addNewModel("Скутер", 200);
        scooter.setNewNameModel("Скутер", "моперд");
        scooter.addNewModel("Скутер 1", 200);
        scooter.addNewModel("Скутер 2", 200);
        scooter.setPrice("Скутер 2", 888);
        scooter.deleteCurrModelInArr("Скутер 1");
        VehicleProcess.printPrices(scooter);

        System.out.println("_______________");
        System.out.println("4 задание");
        Vehicle quadBike = new QuadBike("mark", 5);
        quadBike.addNewModel("Квадроцикл",200);
        quadBike.setNewNameModel("Квадроцикл", "Квадроцикл 2");
        quadBike.addNewModel("Квадроцикл 1", 200);
        quadBike.setPrice("Квадроцикл 1", 888);
        quadBike.deleteCurrModelInArr("Квадроцикл 2");
        VehicleProcess.printPrices(quadBike);

        System.out.println("_______________");
        System.out.println("5 задание");
        Vehicle moped = new Moped("mark", 5);
        moped.addNewModel("Мопед",200);
        moped.setNewNameModel("Мопед", "Мопед 2");
        moped.addNewModel("Мопед", 200);
        moped.setPrice("Мопед 2", 888);
        moped.deleteCurrModelInArr("Мопед 2");
        VehicleProcess.printPrices(moped);

        System.out.println("_______________");
        System.out.println("6 задание");
        System.out.println("Средняя стоимость: " + VehicleProcess.averagePriceVehicles(scooter, moped, quadBike));

        System.out.println("_______________");
        System.out.println("7 задание");
        Vehicle vehicle2 = VehicleProcess.readVehicle();
        VehicleProcess.writeVehicle(vehicle2);
    }
}