package step.learning.OOP;

public class MachineGun extends Weapon implements IAutomatic, IClassified
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
    public String GetCard()
    {
        return String.format("Machine gun: '%s' (fire rate %1f bps)", super.GetName(), this.GetFireRate());
    }

    @Override
    public String GetLevel() { return "For military"; }
}