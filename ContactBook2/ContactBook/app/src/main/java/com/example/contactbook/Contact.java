package com.example.contactbook;

public class Contact
{
    int id ;
    String name,email,phno;

    public void setId(int id)
    {
        this.id=id;
    }
    public void setName(String  name)
    {
        this.name=name;
    }
    public void setEmail(String  email)
    {
        this.email=email;
    }
    public void setPhno(String  phno)
    {
        this.phno=phno;
    }

    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhno()
    {
        return phno;
    }
}
