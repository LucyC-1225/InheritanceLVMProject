import java.util.ArrayList;

public class VolumeGroup {
    ArrayList<PhysicalVolume> physicalVolume; //whenever a physical volume is created, it has to be added here
    ArrayList<LogicalVolume> logicalVolume; //whenever a logical volume is created, it has to be added here
    String UUID;
    int size; //sum of each physicalVolume size
    int sizeLeft; //the size left ot create logicalVolume

}
