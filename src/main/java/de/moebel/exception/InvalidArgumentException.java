package de.moebel.exception;

public class InvalidArgumentException extends RuntimeException
{
    public InvalidArgumentException(String message)
    {
        super(message);
    }
}
