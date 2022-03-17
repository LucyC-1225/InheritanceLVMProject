public class PhysicalVolume extends VolumeManager{
    PhysicalHardDrive physicalHardDrive;
    String uuid;

    public PhysicalVolume(String name, String physicalHardDriveName){
        super(name);
        physicalHardDrive = findHardDrive(physicalHardDriveName);
        uuid = generateUUID();
    }

    public PhysicalHardDrive getPhysicalHardDrive() {
        return physicalHardDrive;
    }

    public String getUuid() {
        return uuid;
    }
    //FINISH THIS
    public String toString(){
        return getName() + ": [" + physicalHardDrive.getSize() + "] ";
    }
}
