public class ModelPriceOutOfBoundsException extends RuntimeException
{
    public ModelPriceOutOfBoundsException()
    {
        System.err.println("цена должна быть положительным числом");
    }
}