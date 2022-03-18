import java.util.ArrayList;

public class VolumeGroup extends VolumeManager{
    static ArrayList<PhysicalVolume> physicalVolume = new ArrayList<PhysicalVolume>(); //whenever a physical volume is created, it has to be added here

    private String uuid;
    private int total; //sum of each physicalVolume size
    private int available; //the size left ot create logicalVolume

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

    public int getTotal() {
        total = 0;
        for (int i = 0; i < physicalVolume.size(); i++){
            String sizeAsStr = physicalVolume.get(i).getPhysicalHardDrive().getSize();
            total += Integer.valueOf(sizeAsStr.substring(0, sizeAsStr.indexOf("G")));
        }
        return total;
    }

    public int getAvailable() {
        return available;
    }
    public void addPhysicalVolumeTovg(PhysicalVolume p){
        physicalVolume.add(p);
    }
    public String toString(){
        String str = "";
        str += getName() + ": total: [" + getTotal() + "G] " + "available: " + "[" + getAvailable() + "] [";
        for (int i = 0; i < physicalVolume.size() - 1; i++){
            str += physicalVolume.get(i).getName() + ",";
        }
        str += physicalVolume.get(physicalVolume.size() - 1).getName() + "] " + getUuid();
        return str;
    }
}
