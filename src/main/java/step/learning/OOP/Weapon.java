package step.learning.OOP;

public abstract class Weapon
{
    String _name;

    public void SetName(String _name) { this._name = _name; }

    public String GetName() { return _name; }

    public abstract String GetCard();
}