package step.learning.OOP;

public class OOP
{
    public void Run()
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
    }
}
