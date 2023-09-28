package step.learning.OOP;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

// The document reflects the work of some of the principles of OOP.
public class OOP
{
    public void Run()
    {
        Armory armory = new Armory();
        armory.Load();
        armory.PrintAll();
    }

    public void Run1()
    {
        Armory armory = new Armory();

        // armory.Add(new Gun("Colt Defender", 8));
        // armory.Add(new MachineGun("M249 SAW", 8.5D));
        // armory.Add(new Rifle("L96A1", 7.62F));

        armory.Add(new Shotgun("Remington 870 Express", 5));
        armory.Add(new Shotgun("Browning BPS", 4));
        armory.Add(new Shotgun("Mossberg 500", 6));

        armory.PrintAll();
        System.out.println("----------------AUTOMATIC----------------");
        armory.PrintAutomatic();
        System.out.println("----------------NON AUTOMATIC----------------");
        armory.PrintNonAutomatic();
        System.out.println("----------------CLASSIFIED----------------");
        armory.PrintClassified();
        System.out.println("----------------YEARS----------------");
        armory.GetYears();
        armory.Save();
    }

    public void Run2()
    {
        String json_string = "{\"_name\":\"Colt defender\", \"_cartrige\":8}";
        Gson gson = new Gson();
        Gun gun = gson.fromJson(json_string, Gun.class);

        System.out.println(gun.GetCard());
        System.out.println(gson.toJson(gun));

        Gson gson2 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        System.out.println(gson2.toJson(gun));
    }

    public void Run3()
    {
        try(InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(
                this.getClass().getClassLoader().getResourceAsStream("colt.json"))))
        {
            JsonObject json_object =  JsonParser.parseReader(reader).getAsJsonObject();
            Weapon weapon = null;

            if(json_object.has("_cartrige"))
                weapon = new Gun(json_object.get("_name").getAsString(), json_object.get("_cartrige").getAsInt());
            else if (json_object.has("fireRate"))
                weapon = new MachineGun(json_object.get("name").getAsString(), json_object.get("fireRate").getAsDouble());
            else if (json_object.has("caliber"))
                weapon = new Rifle(json_object.get("name").getAsString(), json_object.get("Caliber").getAsFloat());
            else
                System.out.println("Weapon type unricognized");

            if (weapon != null)
                System.out.println(weapon.GetCard());
        }
        catch (IOException ex)  { System.out.println("IO error: " + ex.getMessage()); }
        catch (NullPointerException ignored) { System.err.printf("Resource 'colt.json' not found %n"); }
    }
}
