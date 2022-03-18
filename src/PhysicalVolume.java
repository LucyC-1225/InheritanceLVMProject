public class PhysicalVolume extends VolumeManager{
    PhysicalHardDrive physicalHardDrive;
    String uuid;
    VolumeGroup volumeGroup;

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
    public void setVolumeGroup(VolumeGroup volumeGroup){
        this.volumeGroup = volumeGroup;
    }
    public VolumeGroup getVolumeGroup(){
        return volumeGroup;
    }

    public String toString(){
        String str = getName() + ": [" + physicalHardDrive.getSize() + "] ";
        if (volumeGroup != null){
            str += "[" + volumeGroup.getName() + "] ";
        }
        str += "[" + uuid + "]";
        return str;
    }
}
