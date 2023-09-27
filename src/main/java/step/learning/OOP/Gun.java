package step.learning.OOP;

// The Gun class represents a specific implementation of a weapon, in this case a pistol,
// and provides methods and attributes specific to that type of weapon. It also provides information about a weapon's card and
// its classification level through methods defined in the IClassified interface.
public class Gun extends Weapon implements IClassified, IUsed
{
    private int _cartrige;

    public Gun(String name, int cartrige)
    {
        super.SetName(name);
        this._cartrige = cartrige;
    }

    public void SetCartrige(int cartrige) { this._cartrige = cartrige; }

    public int GetCatrige() { return _cartrige; }

    @Override  // Returns a string describing the Gun, including its name and number of rounds.
    public String GetCard() { return String.format("Gun: '%s' (cartridge %d)", super.GetName(), this.GetCatrige()); }

    @Override  // Implements a method from the IClassified interface. It returns the classification level of the weapon type Gun, which,
    public String GetLevel() { return "For civil"; }  // based on the implementation, is the string "For civil".

    @Override
    public int GetYears() { return 1; }
}
