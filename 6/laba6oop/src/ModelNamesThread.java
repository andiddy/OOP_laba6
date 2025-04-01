public class ModelNamesThread {
    private Vehicle vehicle;
    public ModelNamesThread(Vehicle vehicle)
    {
        this.vehicle=vehicle;
    }
    public void run()
    {
        String[] models = vehicle.getModelNameArr();
        for(int i=0; i<vehicle.getModelLength(); i++)
        {
            System.out.println(models[i]);
        }
    }
}
