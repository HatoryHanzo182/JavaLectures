package step.learning.OOP;

// MachineGun represents a specific implementation of a weapon, in this case a machine gun,
// and provides methods and attributes specific to that type of weapon. It also provides information about the weapon card and
// its classification level through methods defined in the IAutomatic and IClassified interfaces.
public class MachineGun extends Weapon implements IAutomatic, IClassified, IUsed
{
    private double _fire_rate;

    public MachineGun(String name, double fire_rate)
    {
        super.SetName(name);
        this._fire_rate = fire_rate;
    }

    public void SetFireRate(double fire_rate) { this._fire_rate = fire_rate; }

    public double GetFireRate() { return _fire_rate; }

    @Override
    public String GetCard()  // This method overrides the abstract GetCard() method from the Weapon class.
    {                       //  It returns a string describing the weapon of type MachineGun, including its name and rate of fire (in rounds per second).
        return String.format("Machine gun: '%s' (fire rate %1f bps)", super.GetName(), this.GetFireRate());
    }

    @Override  // The method implements a method from the IClassified interface.
    public String GetLevel() { return "For military"; }  // It returns the classification level of the weapon of type MachineGun, which, based on the implementation, is the string "For military" (for military use).

    @Override
    public int GetYears() { return 10; }
}