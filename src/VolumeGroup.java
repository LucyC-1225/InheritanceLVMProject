import java.util.ArrayList;

public class VolumeGroup extends VolumeManager{
    static ArrayList<PhysicalVolume> physicalVolume = new ArrayList<PhysicalVolume>(); //whenever a physical volume is created, it has to be added here
    private String uuid;
    private int size; //sum of each physicalVolume size
    private int sizeLeft; //the size left ot create logicalVolume

    public VolumeGroup(String name, String physicalVolumeName){
        super(name);
        physicalVolume.add(findPhysicalVolume(physicalVolumeName));
        uuid = generateUUID();
    }

    public static ArrayList<PhysicalVolume> getPhysicalVolume() {
        return physicalVolume;
    }

    public String getUuid() {
        return uuid;
    }

    public int getSize() {
        return size;
    }

    public int getSizeLeft() {
        return sizeLeft;
    }
}
