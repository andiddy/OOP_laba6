import java.io.Serializable;
public interface Vehicle extends Serializable, Cloneable
{
    String getMark();
    void setMark(String newMark);
    void setNewNameModel(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException;
    String[] getModelNameArr();
    double getPrice(String nameModel) throws NoSuchModelNameException;
    void setPrice(String nameModel, double newPrice) throws NoSuchModelNameException;
    double[] getPricesArr();
    void addNewModel(String newModel, double newPrice) throws DuplicateModelNameException;
    void deleteCurrModelInArr(String nameModel) throws NoSuchModelNameException;
    int getModelLength();
}
