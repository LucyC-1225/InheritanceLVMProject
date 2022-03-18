public class PhysicalVolume extends VolumeManager{
    PhysicalHardDrive physicalHardDrive;
    VolumeGroup volumeGroup;

    public PhysicalVolume(String name, String physicalHardDriveName){
        super(name);
        physicalHardDrive = findHardDrive(physicalHardDriveName);
    }

    public PhysicalHardDrive getPhysicalHardDrive() {
        return physicalHardDrive;
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
        str += "[" + getUuid() + "]";
        return str;
    }
}
