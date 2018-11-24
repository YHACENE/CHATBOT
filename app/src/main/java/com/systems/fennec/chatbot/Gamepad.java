package com.systems.fennec.chatbot;

public class Gamepad
{
    private String title;
    private String description;
    private int image;

    // Constructeur

    public Gamepad(String title, String description, int image)
    {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    // Getters

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public int getImage()
    {
        return image;
    }

    // Setters

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setImage(int image)
    {
        this.image = image;
    }


    /*
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setImage(int image)
    {
        this.image = image;
    }
    */
}
