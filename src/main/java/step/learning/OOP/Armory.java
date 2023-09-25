package step.learning.OOP;

import java.util.ArrayList;
import java.util.List;

// This class represents an example of using object-oriented programming (OOP) in Java,
//  where Weapon and the IAutomatic and IClassified interfaces represent different types of weapons and their characteristics.
public class Armory
{
    private final List<Weapon> _weapons;

    public Armory()
    {
        _weapons = new ArrayList<>();
    }

    public void Add(Weapon weapon) { _weapons.add(weapon); }  // Method for adding weapons to your arsenal. Takes an object of type Weapon and adds it to the _weapons list.

    public void Remove(Weapon weapon) { _weapons.remove(weapon); }  // Method for removing weapons from the arsenal.

    public void PrintAll()  // Method for displaying information about all items in the arsenal.
    {                      // Iterates through the list of _weapons and displays information about each weapon, including its class.
        for (Weapon weapon : _weapons)
        {
            System.out.println(weapon.GetCard());
            System.out.println(weapon.getClass().getName());
        }
    }

    public void PrintAutomatic()  // Method for displaying information about all automatic weapons in the arsenal.
    {                            //  Determines whether a weapon is automatic using the IsAutomatic method and displays information about it.
        for (Weapon weapon : _weapons)
        {
            if(IsAutomatic(weapon))
                System.out.println(weapon.GetCard());
        }
    }

    public void PrintNonAutomatic()  // Method for displaying information about all non-automatic weapons in the arsenal.
    {                               //  Determines whether a weapon is automatic using the IsAutomatic method and displays information about it.
        for (Weapon weapon : _weapons)
        {
            if(!IsAutomatic(weapon))
                System.out.println(weapon.GetCard());
        }
    }

    public boolean IsAutomatic(Weapon weapon) { return weapon instanceof IAutomatic; }  // A method that checks whether the passed weapon is automatic by checking whether the weapon implements the IAutomatic interface.

    public void PrintClassified()  // Method for displaying information about classified weapons in the arsenal.
    {                             // Determines whether a weapon is classified using the IsClassifide method and displays information about it,
                                 // including its classification level.
        for (Weapon weapon : _weapons)
        {
            if(IsClassifide(weapon))
            {
                IClassified weapon_as_classifaed = (IClassified)weapon;
                System.out.println(weapon_as_classifaed.GetLevel() + " " + weapon.GetCard());
            }
        }
    }

    public boolean IsClassifide(Weapon weapon) { return weapon instanceof IClassified; }  // A method that checks whether the supplied weapon is classified by checking whether the weapon implements the IClassified interface.

    public void GetYears()
    {
        for (Weapon weapon : _weapons)
        {
            if(IsClassifide(weapon))
            {
                IUsed weapon_year = (IUsed) weapon;
                System.out.println(weapon.GetName() + " - " + weapon_year.GetYears() + "year");
            }
        }
    }
}