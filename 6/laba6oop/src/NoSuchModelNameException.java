public class NoSuchModelNameException extends Exception
{
  private String name;
  public NoSuchModelNameException(String name) {
    super("модели с именем " + name + " не существует");
    this.name=name;
  }
}