public class PhysicalHardDrive {
    private String name;
    private String size;
    private PhysicalVolume physicalVolume;
    private String physicaVolumeName; //for retrieving data

    public PhysicalHardDrive(String name, String size){
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }
    public void setPhysicalVolume(PhysicalVolume physicalVolume){
        this.physicalVolume = physicalVolume;
    }
    public PhysicalVolume getPhysicalVolume(){
        return physicalVolume;
    }

    public String getPhysicaVolumeName() {
        return physicaVolumeName;
    }

    public void setPhysicaVolumeName(String physicaVolumeName) {
        this.physicaVolumeName = physicaVolumeName;
    }

    public String toString(){
        String str = name + " [" + size + "]";
        return str;
    }
    public String dataInfo(){
        if (getPhysicalVolume() != null){
            return name + ";" + size + ";" + getPhysicalVolume().getName();
        }
        return name + ";" + size + ";" + null; //note that physical volume can be null
    }
}
