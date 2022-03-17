import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.UUID;

public class VolumeManager {
    private String name;
    private static ArrayList<PhysicalHardDrive> physicalHardDriveArrayList = new ArrayList<PhysicalHardDrive>();
    private static ArrayList<PhysicalVolume> physicalVolumeArrayList = new ArrayList<PhysicalVolume>();
    private static ArrayList<VolumeGroup> volumeGroupArrayList = new ArrayList<VolumeGroup>();

    public VolumeManager(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String generateUUID(){
        UUID u = UUID.randomUUID();
        return u.toString();
    }
    public PhysicalHardDrive findHardDrive(String name){
        for (PhysicalHardDrive p : physicalHardDriveArrayList){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    public void addPhysicalHardDrive(PhysicalHardDrive p){
        physicalHardDriveArrayList.add(p);
    }
    public PhysicalVolume findPhysicalVolume(String name){
        for (PhysicalVolume p : physicalVolumeArrayList){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    public void addPhysicalVolume(PhysicalVolume p){
        physicalVolumeArrayList.add(p);
    }
    public VolumeGroup findVolumeGroup(String name){
        for (VolumeGroup v : volumeGroupArrayList){
            if (v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }
    public void addVolumeGroup(VolumeGroup v){
        volumeGroupArrayList.add(v);
    }
}
