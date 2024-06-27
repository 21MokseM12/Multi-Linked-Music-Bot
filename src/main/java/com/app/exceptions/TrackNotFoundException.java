package com.app.exceptions;

public class TrackNotFoundException extends Exception{
    public TrackNotFoundException() {super();}
    public TrackNotFoundException(String message) {super(message);}
    public TrackNotFoundException(Throwable cause) {super(cause);}
    public TrackNotFoundException(String message, Throwable cause) {super(message, cause);}
}
