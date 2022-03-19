public class LogicalVolume extends VolumeManager{
    private String size;
    private VolumeGroup volumeGroup;

    public LogicalVolume(String name, String size, String volumeGroupName){
        super(name);
        this.size = size;
        volumeGroup = findVolumeGroup(volumeGroupName);
    }
    public void setVolumeGroup(VolumeGroup volumeGroup){
        this.volumeGroup = volumeGroup;
    }
    public VolumeGroup getVolumeGroup(){
        return volumeGroup;
    }

    public String getSize() {
        return size;
    }
    public String toString(){
        String str = getName() + ": [" + size + "] [" + volumeGroup.getName() + "] [" + getUuid() + "]";
        return str;
    }

    public String dataInfo(){
        return getName() + ";" + getUuid() + ";" + getSize() + ";" + getVolumeGroup().getName();
    }
}
