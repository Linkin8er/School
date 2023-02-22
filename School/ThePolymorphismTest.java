//////////////////////////////////////////////////////////////////////////////
//                                                                          //
//                          Author: Nicholas Stephani                       //
//                          Project: The Polymorphism Test                  //
//                          Date: 09/25/2020                                //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////
public class ThePolymorphismTest
{
    //This is simply the testing space for Computer and Notebook
    public static void main(String[] args)
    {
        //Initializes theComputer as type Computer
        Computer theComputer;

        //Constructs theComputer into a new Computer with values
        theComputer = new Computer("Intel", "Very-Good-Processor", 36.0, 5, 123.0);

        System.out.println(theComputer.toString());

        theComputer.getRamSize();

        //Re-Constructs theComputer into a Notebook
        theComputer = new Notebook("Not Intel", "A-Kinda-Meh-Processor", 2.0, 543, 15.0, 500.0, 0.03);

        System.out.println(theComputer.toString());

        //theComputer.getScreenSize();
    }
}

class Computer 
{
    //This class makes a Computer object and is the super class to Notebook. Nothing really remarkable.

    // Data Fields
    protected String manufacturer;
    protected String processor;
    protected double ramSize;
    protected int diskSize;
    protected double processorSpeed;
    // Methods
    /** Initializes a Computer object with all properties specified.
    @param man The computer manufacturer
    @param processor The processor type
    @param ram The RAM size
    @param disk The disk size
    @param procSpeed The processor speed
    */

    Computer(String man, String processor, double ram, int disk, double procSpeed) 
    {
        manufacturer = man;
        this.processor = processor;
        ramSize = ram;
        diskSize = disk;
        processorSpeed = procSpeed;
    }

    //Directly prints the ram size to the monitor
    public void getRamSize() {System.out.println("RAM is: " + ramSize + " gigabytes.");}

    //The toString pints out the 5 data fields of Computer
    public String toString() 
    {
        String result = "Manufacturer: " + manufacturer +
                        "\nCPU: " + processor +
                        "\nRAM: " + ramSize + " gigabytes" +
                        "\nDisk: " + diskSize + " gigabytes" +
                        "\nProcessor speed: " + processorSpeed + " gigahertz";
        return result;
    }
}

class Notebook extends Computer {

    //The Notebook class, the subclass of Computer. Again, nothing remarkable though with more data.
    // Data Fields
    private double screenSize;
    private double weight;

    // Methods
    /** Initializes a Notebook object with all properties specified.
    @param man The computer manufacturer
    @param proc The processor type
    @param ram The RAM size
    @param disk The disk size
    @param procSpeed The processor speed
    @param screen The screen size
    @param wei The weight
    */
    public void getScreenSize() {System.out.println("The screen's size is: " + screenSize + " inches.");}

    //Constructs a Notebook using the constructor for a Computer but with two more data fields
    public Notebook(String man, String proc, double ram, int disk, double procSpeed, double screen, double wei) 
    {
        super(man, proc, ram, disk, procSpeed);
        screenSize = screen;
        weight = wei;
    }

    //Prints out all of the data fields using the super classes toString plus it's own @return result is the complete string
    public String toString() 
    {
        String result = super.toString() +
                        "\nScreen size: " + screenSize + " in" + 
                        "\nWeight: " + weight + " lbs";
        return result;
    }
}

// Polymorphism did not handle this exact scenario as the class Computer does not have access to the getScreenSize method.
// This is due to the fact that subclasses have all the features of super classes, but the super classes do not have access to the features of subclasses.
// This could be fixed by casting theComputer to a Notebook type, so the computer knows to treat theComputer as a Notebook for that line.