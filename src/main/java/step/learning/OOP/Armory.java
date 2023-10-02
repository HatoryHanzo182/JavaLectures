package step.learning.OOP;

import com.google.gson.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

// This class represents an example of using object-oriented programming (OOP) in Java,
// where Weapon and the IAutomatic and IClassified interfaces represent different types of weapons and their characteristics.
public class Armory
{
    private final List<Weapon> _weapons;

    public Armory()
    {
        _weapons = new ArrayList<>();
    }

    public void Add(Weapon weapon) { _weapons.add(weapon); }  // Method for adding weapons to your arsenal. Takes an object of type Weapon and adds it to the _weapons list.

    public void Remove(Weapon weapon) { _weapons.remove(weapon); }  // Method for removing weapons from the arsenal.

    private List<Weapon> GetSerializableWeapons()
    {
        List<Weapon> result = new LinkedList<>();

        for (Weapon weapon : _weapons)
        {
            if (weapon.getClass().isAnnotationPresent(ISerializable.class))
                result.add(weapon);
        }
        return result;
    }

    private List<Class<?>> FindSerializableClasess()
    {
        List<Class<?>> weapon_clasess = new ArrayList<>();
        String armory_name = Armory.class.getName();
        String package_name = armory_name.substring(0, armory_name.lastIndexOf('.') + 1);
        String package_path = package_name.replace('.', '/');
        String resource_path = Armory.class.getClassLoader().getResource(package_path).getPath();

        try { resource_path = URLDecoder.decode(resource_path, "UTF-8"); }
        catch (UnsupportedEncodingException ignored) { }

        File resource_dictionary = new File(resource_path);
        File[] files = resource_dictionary.listFiles();

        if (files == null)
            throw new RuntimeException(String.format("Dictionary '%s' got no file list", resource_dictionary));

        for (File file : files)
        {
            if(file.isDirectory())
                continue;
            else if(file.isFile())
            {
                String filename = file.getName();

                if (filename.endsWith(".class"))
                {
                    String class_name = package_name + filename.substring(0, filename.lastIndexOf('.'));

                    try
                    {
                        Class<?> class_type = Class.forName(class_name);

                        if(class_type.isAnnotationPresent(ISerializable.class) && Weapon.class.isAssignableFrom(class_type))
                            weapon_clasess.add(class_type);
                    }
                    catch (ClassNotFoundException ignored) { System.err.println("Not found: " + class_name); }
                }
            }
        }
        return weapon_clasess;
    }

    public void Save()
    {
        String path = URLDecoder.decode( this.getClass().getClassLoader().getResource("./").getPath());

        System.out.println(path);  // <-- /C:/My%20resources/Work/Java/JavaLectures/target/classes/

        try(FileWriter writer = new FileWriter(path + "armory.json"))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

            writer.write(gson.toJson(this.GetSerializableWeapons()));
        }
        catch (IOException ex) { throw new RuntimeException(ex); }
    }

    public void Load() throws RuntimeException
    {
        Class<?>[] weapon_classes = FindSerializableClasess().toArray(new Class[0]);

        try( InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(
                this.getClass().getClassLoader().getResourceAsStream("armory.json"))))
        {
            JsonArray json_array = JsonParser.parseReader( reader ).getAsJsonArray() ;

            for(JsonElement element : json_array)
            {
                JsonObject json_object = element.getAsJsonObject();
                Weapon weapon = null;

                for(Class<?> weapon_class : weapon_classes)
                {
                    Method isParseableFromJSON = null; // = weapon_class.getDeclaredMethod("IsParseableFromJSON", JsonObject.class);
                    Method from_json = null; // = weapon_class.getDeclaredMethod("FromJSON", JsonObject.class);

                    for (Method method : weapon_class.getDeclaredMethods())
                    {
                        if (method.isAnnotationPresent(IJsonParseChecker.class))
                        {
                            if (isParseableFromJSON != null)
                                throw new RuntimeException(String.format("Multipple methods with @%s anotation in %s class",
                                        IJsonParseChecker.class.getName(), weapon_class.getName()));

                            isParseableFromJSON = method;
                        }
                        if (method.isAnnotationPresent(IJsonFactory.class))
                        {
                            if (from_json != null)
                            {
                                throw new  RuntimeException(String.format("Multipple methods with @%s anotation in %s class",
                                        IJsonFactory.class.getName(), weapon_class.getName()));
                            }
                            from_json = method;
                        }
                    }

                    if(isParseableFromJSON == null || from_json == null)
                        continue;

                    isParseableFromJSON.setAccessible(true);

                    boolean res = (boolean) isParseableFromJSON.invoke(null, json_object);

                    if (res)
                    {
                        from_json.setAccessible(true);

                        weapon = (Weapon) from_json.invoke(null, json_object);

                        break;
                    }
                }

                if (weapon != null)
                    this._weapons.add(weapon);
                else
                    System.out.println("Weapon type unricognized");
            }
        }
        catch(IllegalAccessException | InvocationTargetException ex ) { throw new RuntimeException( "Reflection error: " + ex.getMessage() ) ; }
        catch( IOException ex ) { throw new RuntimeException( "IO error: " + ex.getMessage() ); }
        catch( NullPointerException ignored ) { throw new RuntimeException( String.format("Resource 'armory.json' not found %n"));}
        catch( IllegalArgumentException ex ) { throw new RuntimeException( "JSON parse error: " + ex.getMessage() ) ; }
    }

    public void PrintAll()  // Method for displaying information about all items in the arsenal.
    {                      // Iterates through the list of _weapons and displays information about each weapon, including its class.
        for (Weapon weapon : _weapons)
        {
            System.out.println(weapon.GetCard());
            System.out.println(weapon.getClass().getName());
        }
    }

    public void PrintAutomatic()  // Method for displaying information about all automatic weapons in the arsenal.
    {                            //  Determines whether a weapon is automatic using the IsAutomatic method and displays information about it.
        for (Weapon weapon : _weapons)
        {
            if(IsAutomatic(weapon))
                System.out.println(weapon.GetCard());
        }
    }

    public void PrintNonAutomatic()  // Method for displaying information about all non-automatic weapons in the arsenal.
    {                               //  Determines whether a weapon is automatic using the IsAutomatic method and displays information about it.
        for (Weapon weapon : _weapons)
        {
            if(!IsAutomatic(weapon))
                System.out.println(weapon.GetCard());
        }
    }

    public boolean IsAutomatic(Weapon weapon) { return weapon instanceof IAutomatic; }  // A method that checks whether the passed weapon is automatic by checking whether the weapon implements the IAutomatic interface.

    public void PrintClassified()  // Method for displaying information about classified weapons in the arsenal.
    {                             // Determines whether a weapon is classified using the IsClassifide method and displays information about it,
                                 // including its classification level.
        for (Weapon weapon : _weapons)
        {
            if(IsClassifide(weapon))
            {
                IClassified weapon_as_classifaed = (IClassified)weapon;
                System.out.println(weapon_as_classifaed.GetLevel() + " " + weapon.GetCard());
            }
        }
    }

    public boolean IsClassifide(Weapon weapon) { return weapon instanceof IClassified; }  // A method that checks whether the supplied weapon is classified by checking whether the weapon implements the IClassified interface.

    public void GetYears()
    {
        for (Weapon weapon : _weapons)
        {
            if(IsClassifide(weapon))
            {
                IUsed weapon_year = (IUsed) weapon;
                System.out.println(weapon.GetName() + " - " + weapon_year.GetYears() + "year");
            }
        }
    }
}