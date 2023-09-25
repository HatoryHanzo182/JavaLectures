package step.learning.OOP;

public class Gun extends Weapon implements IClassified
{
    private int _cartride;

    public Gun(String name, int _cartride)
    {
        super.SetName(name);
        this._cartride = _cartride;
    }

    public void SetCartrige(int cartride) { this._cartride = cartride; }

    public int GetCatrige() { return _cartride; }

    @Override
    public String GetCard()
    {
        return String.format("Gun: '%s' (cartridge %d)", super.GetName(), this.GetCatrige());
    }

    @Override
    public String GetLevel() { return "For civil"; }
}
