package models;

import annotation.*;

@Age(20)
public class Etudiant {

    private int age;
    private String name;
    private String nickname;

    public Etudiant(int age ,String name, String nickname){
        this.age = age;
        this.name = name;
        this.nickname = nickname;
    }

    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
}
