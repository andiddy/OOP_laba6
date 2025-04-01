public class PricesOfModelThread {
    private Vehicle vehicle;
    public PricesOfModelThread(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    public void run()
    {
        double[] models = vehicle.getPricesArr();
        for(int i=0; i<vehicle.getModelLength(); i++)
        {
            System.out.println(models[i]);
        }
    }
}
