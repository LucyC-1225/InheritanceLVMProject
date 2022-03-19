import java.util.ArrayList;

public class VolumeGroup extends VolumeManager{
    //set this while retrieving data. No need to save this
    static ArrayList<PhysicalVolume> vgPhysicalVolume = new ArrayList<PhysicalVolume>(); //whenever a physical volume is created, it has to be added here
    static ArrayList<LogicalVolume> vgLogicalVolumes = new ArrayList<LogicalVolume>(); //whenever a logical volume is created, it has to be added here

    private int total; //sum of each physicalVolume size
    private int available; //the size left ot create logicalVolume

    public VolumeGroup(String name, String physicalVolumeName){
        super(name);
        vgPhysicalVolume.add(findPhysicalVolume(physicalVolumeName));
    }

    //for data retrieval - did not save the physicalVolumeName b/c volumeGroup can have multiple physical volumes
    //set the vgPhysicalVolume by searching through all the created physical volume for the current vg name
    public VolumeGroup(String name){
        super(name);
    }

    public static ArrayList<PhysicalVolume> getVgPhysicalVolume() {
        return vgPhysicalVolume;
    }


    public int getTotal() {
        total = 0;
        for (int i = 0; i < vgPhysicalVolume.size(); i++){
            String sizeAsStr = vgPhysicalVolume.get(i).getPhysicalHardDrive().getSize();
            total += Integer.valueOf(sizeAsStr.substring(0, sizeAsStr.indexOf("G")));
        }
        return total;
    }

    public int getAvailable() {
        available = getTotal();
        for (int i = 0; i < vgLogicalVolumes.size(); i++){
            String sizeAsStr = vgLogicalVolumes.get(i).getSize();
            available -= Integer.valueOf(sizeAsStr.substring(0, sizeAsStr.indexOf("G")));
        }
        return available;
    }
    public void addPhysicalVolumeTovg(PhysicalVolume p){
        vgPhysicalVolume.add(p);
    }
    public String toString(){
        String str = "";
        str += getName() + ": total: [" + getTotal() + "G] " + "available: " + "[" + getAvailable() + "G] [";
        for (int i = 0; i < vgPhysicalVolume.size() - 1; i++){
            str += vgPhysicalVolume.get(i).getName() + ",";
        }
        str += vgPhysicalVolume.get(vgPhysicalVolume.size() - 1).getName() + "] [" + getUuid() + "]";
        return str;
    }
    public void addLogicalVolumeTovg(LogicalVolume l){
        vgLogicalVolumes.add(l);
    }
    public String dataInfo(){
        return getName() + ";" + getUuid();
    }
}
