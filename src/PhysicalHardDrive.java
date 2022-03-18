public class PhysicalHardDrive {
    private String name;
    private String size;
    private PhysicalVolume physicalVolume;


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
    public String toString(){
        String str = name + " [" + size + "]";
        return str;
    }
}
