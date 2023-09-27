package step.learning.OOP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

// The document reflects the work of some of the principles of OOP.
public class OOP
{
    public void Run()
    {
        String json_string = "{\"_name\":\"Colt defender\", \"_cartrige\":8}";
        Gson gson = new Gson();
        Gun gun = gson.fromJson(json_string, Gun.class);

        System.out.println(gun.GetCard());
        System.out.println(gson.toJson(gun));

        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson2.toJson(gun));
    }

    public void Run1()
    {
        Armory armory = new Armory();

        armory.Add(new Gun("Colt Defender", 8));
        armory.Add(new MachineGun("M249 SAW", 8.5D));
        armory.Add(new Rifle("L96A1", 7.62F));

        armory.PrintAll();
        System.out.println("----------------AUTOMATIC----------------");
        armory.PrintAutomatic();
        System.out.println("----------------NON AUTOMATIC----------------");
        armory.PrintNonAutomatic();
        System.out.println("----------------CLASSIFIED----------------");
        armory.PrintClassified();
        System.out.println("----------------YEARS----------------");
        armory.GetYears();
    }
}
