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
                System.out.println(v.installDrive(info.substring(0, info.indexOf(" ")), info.substring(info.indexOf(" ") + 1)));
            } else if (choice.equals("list-drives")){
                System.out.println(v.listDrives());
            } else if (choice.equals("pvcreate")){
                System.out.println(v.pvCreate(info.substring(0, info.indexOf(" ")), info.substring(info.indexOf(" ") + 1)));
            } else if (choice.equals("pvlist")){
                System.out.println(v.pvlist());
            } else if (choice.equals("vgcreate")){
                System.out.println(v.vgCreate(info.substring(0, info.indexOf(" ")), info.substring(info.indexOf(" ") + 1)));
            } else if (choice.equals("vgextend")){
                System.out.println(v.vgextend(info.substring(0, info.indexOf(" ")), info.substring(info.indexOf(" ") + 1)));
            } else if (choice.equals("vglist")){
                System.out.println(v.vglist());
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