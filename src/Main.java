import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VolumeManager v = new VolumeManager("Volume Manager");
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        while (!quit){
            System.out.print("cmd# ");
            String input = sc.nextLine();
            String choice;
            if (input.indexOf(" ") == -1){
                choice = input;
            } else {
                choice = input.substring(0, input.indexOf(" "));
            }
            String info = input.substring(input.indexOf(" ") + 1);
            if (choice.equals("install-drive")){
                String name = info.substring(0, info.indexOf(" "));
                String size = info.substring(info.indexOf(" ") + 1);
                System.out.println(v.installDrive(name, size));
            } else if (choice.equals("list-drives")){
                System.out.println(v.listDrives());
            } else if (choice.equals("pvcreate")){
                String name = info.substring(0, info.indexOf(" "));
                String physicalHardDriveName = info.substring(info.indexOf(" ") + 1);
                System.out.println(v.pvCreate(name, physicalHardDriveName));
            } else if (choice.equals("pvlist")){
                System.out.println(v.pvlist());
            } else if (choice.equals("vgcreate")){
                String name = info.substring(0, info.indexOf(" "));
                String physicalHardDriveName = info.substring(info.indexOf(" ") + 1);
                System.out.println(v.vgCreate(name, physicalHardDriveName));
            } else if (choice.equals("vgextend")){
                String vgName = info.substring(0, info.indexOf(" "));
                String physicalVolumeName = info.substring(info.indexOf(" ") + 1);
                System.out.println(v.vgextend(vgName, physicalVolumeName));
            } else if (choice.equals("vglist")){
                System.out.println(v.vglist());
            } else if (choice.equals("lvcreate")){
                String name = info.substring(0, info.indexOf(" "));
                info = info.substring(info.indexOf(" ") + 1);
                String size = info.substring(0, info.indexOf(" "));
                String volumeGroupName = info.substring(info.indexOf(" ") + 1);
                System.out.println(v.lvcreate(name, size, volumeGroupName));
            } else if (choice.equals("lvlist")){
                System.out.println(v.lvlist());
            } else if (choice.equals("exit")){
                System.out.println("Saving data. Good-bye!");
                quit = true;
            }
        }

    }
}
/*
        //create volume manager
        VolumeManager v = new VolumeManager("Volume Manager");
        //whenever you create a physical hard drive, you have to add it to volume manager
        PhysicalHardDrive sda = new PhysicalHardDrive("SDA1", "100G");
        v.addPhysicalHardDrive(sda);
        //whenever you create a physical volume, you have to add it to volume manager
        PhysicalVolume pv = new PhysicalVolume("PV1", "SDA1");
        v.addPhysicalVolume(pv);
        //whenever you create a volume group, you have to add it to volume manager
        //whenever you create a volume group or add (extend) a physical volume to the volume group, you have to call the setVolumeGroup method on the physical volume object
        VolumeGroup vg = new VolumeGroup("VG1", "PV1");
        v.addVolumeGroup(vg);
        v.findPhysicalVolume("PV1").setVolumeGroup(vg);
 */