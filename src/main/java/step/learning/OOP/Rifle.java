package step.learning.OOP;

// The Rifle class extends the Weapon class and is a specific implementation of a weapon in the form of a rifle.
public class Rifle extends Weapon implements IUsed
{
    private float _caliber;

    public Rifle(String name, float _caliber)
    {
        super.SetName(name);
        this._caliber = _caliber;
    }

    public float GetCaliber() { return _caliber; }

    public void SetCaliber(float caliber) { this._caliber = caliber; }

    @Override
    public String GetCard()  // Overrides the abstract GetCard method from the Weapon class.
    {                       // It returns a string describing the rifle, including its name and caliber in millimeters.
        return String.format("Rifle: '%s' (caliber %.2f mm)", super.GetName(), this.GetCaliber());
    }

    @Override
    public int GetYears() { return 3; }
}
