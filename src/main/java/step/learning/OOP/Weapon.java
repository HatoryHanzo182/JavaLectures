package step.learning.OOP;

// Abstract base class to represent a weapon, but it does not perform any specific functionality on its own.
// Instead, it defines common attributes (such as _name) and an abstract GetCard() method that must be implemented by its subclasses.
public abstract class Weapon
{
    String _name;

    public void SetName(String _name) { this._name = _name; }

    public String GetName() { return _name; }

    public abstract String GetCard();  // The GetCard method should return a string and will presumably be used to obtain weapon card information.
}