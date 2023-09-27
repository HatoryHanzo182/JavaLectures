package step.learning.OOP;

import com.google.gson.JsonObject;

// The Gun class represents a specific implementation of a weapon, in this case a pistol,
// and provides methods and attributes specific to that type of weapon. It also provides information about a weapon's card and
// its classification level through methods defined in the IClassified interface.
public class Gun extends Weapon implements IClassified, IUsed
{
    private int _cartridge;

    public Gun(String name, int cartridge)
    {
        super.SetName(name);
        this._cartridge = cartridge;
    }

    public void SetCartrige(int cartrige) { this._cartridge = cartrige; }

    public int GetCatrige() { return _cartridge; }

    public static Gun FromJSON(JsonObject json_object) throws IllegalArgumentException
    {
        String[] required_fields = { "_name", "_cartridge" } ;

        for( String field : required_fields )
        {
            if(!json_object.has(field))
                throw new IllegalArgumentException( "Gun construct error: Missing required field: " + field ) ;
        }
        return new Gun(json_object.get(required_fields[0]).getAsString(), json_object.get(required_fields[1]).getAsInt()) ;
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

    @Override  // Returns a string describing the Gun, including its name and number of rounds.
    public String GetCard() { return String.format("Gun: '%s' (cartridge %d)", super.GetName(), this.GetCatrige()); }

    @Override  // Implements a method from the IClassified interface. It returns the classification level of the weapon type Gun, which,
    public String GetLevel() { return "For civil"; }  // based on the implementation, is the string "For civil".

    @Override
    public int GetYears() { return 1; }
}
