public class PhysicalVolume extends VolumeManager{
    private PhysicalHardDrive physicalHardDrive;
    private VolumeGroup volumeGroup;
    private String volumeGroupName; //for data retrieval


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


    public String getVolumeGroupName() {
        return volumeGroupName;
    }


    public void setVolumeGroupName(String volumeGroupName) {
        this.volumeGroupName = volumeGroupName;
    }

    public String toString(){
        String str = getName() + ": [" + physicalHardDrive.getSize() + "] ";
        if (volumeGroup != null){
            str += "[" + volumeGroup.getName() + "] ";
        }
        str += "[" + getUuid() + "]";
        return str;
    }
    public String dataInfo(){
        if (getVolumeGroup() != null){
            return getName() + ";" + getUuid() + ";" + getPhysicalHardDrive().getName() + ";" + getVolumeGroup().getName();
        }
        return getName() + ";" + getUuid() + ";" + getPhysicalHardDrive().getName() + ";" + null;
        //note that volume group can be null
    }
}
