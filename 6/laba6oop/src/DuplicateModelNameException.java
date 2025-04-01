public class DuplicateModelNameException extends Exception
{
    private String name;
    public DuplicateModelNameException(String name) {
        super("модель с именем " + name + " уже существует");
        this.name=name;
    }
}