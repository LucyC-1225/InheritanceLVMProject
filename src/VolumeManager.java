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
    //commands - idea: maybe put all this in another Command class?
    public String installDrive(String name, String size){
        /*
        - prevent users from entering the same name
         */
        PhysicalHardDrive p = new PhysicalHardDrive(name, size);
        addPhysicalHardDrive(p);
        return "drive " + name + " installed\n";
    }
    public String listDrives(){
        String str = "";
        for (int i = 0; i < physicalHardDriveArrayList.size(); i++){
            str += physicalHardDriveArrayList.get(i).toString() + "\n";
        }
        return str;
    }
    public String pvCreate(String name, String physicalHardDriveName){
        /*
        - prevent users from entering the same name as an existing pv
        - prevent users from entering a physicalHardDriveName that is already associated with a pv
        - prevent users from entering a physicalHardDriveName that does not exist
         */
        PhysicalVolume pv = new PhysicalVolume(name, physicalHardDriveName);
        addPhysicalVolume(pv);
        findHardDrive(physicalHardDriveName).setPhysicalVolume(pv); //sets the hard drive to an associated physical volume
        return name + " created\n";
    }
    public String pvlist(){
        String str = "";
        for (int i = 0; i < physicalVolumeArrayList.size(); i++){
            str += physicalVolumeArrayList.get(i).toString() + "\n";
        }
        return str;
    }
    public String vgCreate(String name, String physicalVolumeName){
        /*
        - prevent users from entering the same name as an existing vg
        - prevent users from entering a physical volume that is already associated with a vg
        - prevent users from entering a physical volume that does not exist
         */
        VolumeGroup vg = new VolumeGroup(name, physicalVolumeName);
        addVolumeGroup(vg);
        findPhysicalVolume(physicalVolumeName).setVolumeGroup(vg); //sets the physical volume to an associated volume group
        return name + " created\n";
    }
    public String vgextend(String vgname, String physicalVolumeName){
        /*
        - prevent users from entering a physical volume that is already associated with a volume group
        - prevents the users from entering a physical volume that does not exist
        - prevents the user from entering a volume group that does not exist
         */
        findVolumeGroup(vgname).addPhysicalVolumeTovg(findPhysicalVolume(physicalVolumeName)); //be careful of which pv list im adding to
        findPhysicalVolume(physicalVolumeName).setVolumeGroup(findVolumeGroup(vgname));
        return physicalVolumeName + " added to " + vgname + "\n";
    }
    public String vglist(){
        String str = "";
        for (int i = 0; i < volumeGroupArrayList.size(); i++){
            str += volumeGroupArrayList.get(i).toString() + "\n";
        }
        return str;
    }
}
