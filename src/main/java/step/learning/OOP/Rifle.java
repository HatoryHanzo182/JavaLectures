package step.learning.OOP;

import com.google.gson.JsonObject;

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

    public static Gun FromJSON(JsonObject json_object) throws IllegalAccessException
    {
        String [] required_fields = {"_name", "_caliber"};

        for (String field : required_fields)
        {
            if (!json_object.has(field))
                throw new IllegalAccessException("Rifle construct error: Missing required filed - " + field);
        }

        return new Gun(json_object.get(required_fields[0]).getAsString(),  json_object.get(required_fields[1]).getAsInt());
    }

    public static boolean IsParseableFromJSON(JsonObject json_object)
    {
        String[] required_fields = {"_name", "_cartridge"};

        for (String field : required_fields)
        {
            if (!json_object.has(field))
                return false;
        }

        return true;
    }

    @Override
    public String GetCard()  // Overrides the abstract GetCard method from the Weapon class.
    {                       // It returns a string describing the rifle, including its name and caliber in millimeters.
        return String.format("Rifle: '%s' (caliber %.2f mm)", super.GetName(), this.GetCaliber());
    }

    @Override
    public int GetYears() { return 3; }
}
