package step.learning.OOP;

public class Rifle extends Weapon
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
    public String GetCard()
    {
        return String.format("Rifle: '%s' (caliber %.2f mm)", super.GetName(), this.GetCaliber());
    }
}
