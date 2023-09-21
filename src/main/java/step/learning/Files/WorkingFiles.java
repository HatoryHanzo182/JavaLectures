package step.learning.Files;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkingFiles
{
    private final File _directory;

    public WorkingFiles(String path)
    {
        this._directory = new File(path);
    }

    public void Run()  // Executes all existing logic.
    {
        if(Availability())
            ShowMessage(GenerateOutput());
        else
            ShowMessage("Sorry, I didn't find anything in your path.");
    }

    private boolean Availability()  // The method checks the availability (existence) of the directory and
    {                              // returns true if the directory exists and is a valid directory (folder),
                                  // otherwise it returns false.
        if(_directory.exists() && _directory.isDirectory())
            return true;
        else
            return false;
    }

    private String GenerateOutput()  // Generates formatted output of the contents of a directory and returns it as a string.
    {
        StringBuilder build = new StringBuilder();
        SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yy HH:mm");

        build.append("Model\tLastWriteTime\t  Lenght\tName\n");
        build.append("-----\t-------------\t  ------\t----\n");

        File[] files = _directory.listFiles();

        for(File i : files)
        {
            String type =  i.isDirectory() ? "d-----" : "-a----";
            String date = date_format.format(new Date(i.lastModified()));
            String size = i.isDirectory() ? " \t" : String.valueOf(i.length());
            String name = i.getName();

            build.append(String.format("%s\t%s\t  %s\t   %s\t\n", type, date, size, name));
        }
        return build.toString();
    }

    private void ShowMessage(String message) { System.out.println(message); }  // Once this method is called with a specific message as an argument, it will be displayed on the console.
}

// +  +  +  +  +  Client code.  +  +  +  +  +
//
// public static void main( String[] args )
// {
//     WorkingFiles wf = new WorkingFiles("./");
//
//     wf.Run();
// }