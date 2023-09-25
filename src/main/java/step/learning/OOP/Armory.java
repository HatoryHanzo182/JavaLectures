package step.learning.OOP;

import java.util.ArrayList;
import java.util.List;

public class Armory
{
    private final List<Weapon> _weapons;

    public Armory()
    {
        _weapons = new ArrayList<>();
    }

    public void Add(Weapon weapon) { _weapons.add(weapon); }

    public void Remove(Weapon weapon) { _weapons.remove(weapon); }

    public void PrintAll()
    {
        for (Weapon weapon : _weapons)
        {
            System.out.println(weapon.GetCard());
            System.out.println(weapon.getClass().getName());
        }
    }

    public void PrintAutomatic()
    {
        for (Weapon weapon : _weapons)
        {
            if(IsAutomatic(weapon))
                System.out.println(weapon.GetCard());
        }
    }

    public void PrintNonAutomatic()
    {
        for (Weapon weapon : _weapons)
        {
            if(!IsAutomatic(weapon))
                System.out.println(weapon.GetCard());
        }
    }

    public boolean IsAutomatic(Weapon weapon) { return weapon instanceof IAutomatic; }

    public void PrintClassified()
    {
        for (Weapon weapon : _weapons)
        {
            if(IsClassifide(weapon))
            {
                IClassified weapon_as_classifaed = (IClassified)weapon;
                System.out.println(weapon_as_classifaed.GetLevel() + " " + weapon.GetCard());
            }
        }
    }

    public boolean IsClassifide(Weapon weapon) { return weapon instanceof IClassified; }
}