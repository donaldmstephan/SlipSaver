package com.dartmouth.cs.slipsaver;

/**
 * Created by Donald on 3/1/2016.
 */
public class Contact {
    String FirstName;
    String LastName;
    String Email;
    int Selected;
    long id;

    public Contact(String fn, String ln, String em, long i, int select) {
        FirstName = fn;
        LastName = ln;
        Email = em;
        Selected = select;
        id = i;

    }
}
