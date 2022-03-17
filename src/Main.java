public class Main {
    public static void main(String[] args) {
        //create volume manager
        VolumeManager v = new VolumeManager("Volume Manager");
        //whenever you create a physical hard drive, you have to add it to volume manager
        PhysicalHardDrive sda = new PhysicalHardDrive("SDA1", "100G");
        v.addPhysicalHardDrive(sda);
        //whenever you create a physical volume, you have to add it to volume manager
        PhysicalVolume pv = new PhysicalVolume("PV1", "SDA1");
        v.addPhysicalVolume(pv);
        //whenever you create a volume group, you have to add it to volume manager
        VolumeGroup vg = new VolumeGroup("VG1", "PV1");
        v.addVolumeGroup(vg);
        System.out.println(vg.getPhysicalVolume().get(0).toString());
    }
}
